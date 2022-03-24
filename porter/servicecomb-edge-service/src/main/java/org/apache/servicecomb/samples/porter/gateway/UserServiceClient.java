package org.apache.servicecomb.samples.porter.gateway;

import java.util.concurrent.CompletableFuture;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.samples.porter.user.api.SessionInfo;
import org.springframework.stereotype.Component;

interface GetSessionOperation {
  CompletableFuture<SessionInfo> getSession(String sessionId);
}


@Component("UserServiceClient")
public class UserServiceClient {
  @RpcReference(microserviceName = "user-service", schemaId = "user")
  private GetSessionOperation getSessionOperation;

  public GetSessionOperation getGetSessionOperation() {
    return getSessionOperation;
  }
}

package org.apache.servicecomb.samples.porter.gateway;

import org.apache.servicecomb.core.Handler;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.swagger.invocation.AsyncResponse;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;

public class InternalAccessHandler implements Handler {

  @Override
  public void handle(Invocation invocation, AsyncResponse asyncReponse) throws Exception {
    if (invocation.getOperationMeta().getSwaggerOperation().getTags() != null
        && invocation.getOperationMeta().getSwaggerOperation().getTags().contains("INTERNAL")) {
      asyncReponse.consumerFail(new InvocationException(403, "", "not allowed"));
      return;
    }
    invocation.next(asyncReponse);
  }

}

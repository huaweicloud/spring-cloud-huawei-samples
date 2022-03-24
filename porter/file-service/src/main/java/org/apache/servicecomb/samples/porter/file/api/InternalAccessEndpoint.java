package org.apache.servicecomb.samples.porter.file.api;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@RestSchema(schemaId = "InternalAccessEndpoint")
@RequestMapping(path = "/")
@Api(tags = {"INTERNAL"})
public class InternalAccessEndpoint {
  @Autowired
  private InternalAccessService internalAccessService;
  
  @GetMapping(path = "localAccess")
  public String localAccess(String name) {
    return internalAccessService.localAccess(name);
  }
}

package org.apache.servicecomb.samples.porter.file.service;

import org.apache.servicecomb.samples.porter.file.api.InternalAccessService;
import org.springframework.stereotype.Service;

@Service
public class InternalAccessServiceImpl implements InternalAccessService {
  public String localAccess(String name) {
    return "Hello, " + name;
  }
}

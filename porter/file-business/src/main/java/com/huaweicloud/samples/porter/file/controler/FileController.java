package com.huaweicloud.samples.porter.file.controler;

import org.apache.servicecomb.foundation.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.huaweicloud.common.context.InvocationContextHolder;
import com.huaweicloud.samples.porter.file.service.FileService;
import com.huaweicloud.samples.porter.file.service.impl.SessionInfo;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/v1/file")
public class FileController {
  private FileService fileService;

  @Autowired
  public FileController(FileService fileService) {
    this.fileService = fileService;
  }

  @PostMapping(path = "/upload", produces = MediaType.TEXT_PLAIN_VALUE)
  public String uploadFile(@RequestPart(name = "fileName") MultipartFile file) {
    return fileService.uploadFile(file);
  }

  @DeleteMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
  public boolean deleteFile(@RequestParam(name = "id") String id, HttpServletResponse response) {
    String session = InvocationContextHolder.getOrCreateInvocationContext().getContext("session-info");
    if (session == null) {
      response.setStatus(503);
      return false;
    } else {
      SessionInfo sessionInfo = null;
      try {
        sessionInfo = JsonUtils.readValue(session.getBytes("UTF-8"), SessionInfo.class);
      } catch (Exception e) {
        response.setStatus(503);
        return false;
      }
      if (sessionInfo == null || !sessionInfo.getRoleName().equals("admin")) {
        response.setStatus(503);
        return false;
      }
    }

    return fileService.deleteFile(id);
  }
}

package com.huaweicloud.samples.porter.file.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/")
public class FileEndpoint {
  @Autowired
  private FileService fileService;
  
  /**
   * upload and return file id
   */
  @PostMapping(path = "/upload", produces = MediaType.TEXT_PLAIN_VALUE)
  public String uploadFile(@RequestPart(name = "fileName") MultipartFile file) {
      return fileService.uploadFile(file);
  }

  /**
   * delete file by id
   */
  @DeleteMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
  public boolean deleteFile(@RequestParam(name = "id") String id) {
      return fileService.deleteFile(id);
  }
}

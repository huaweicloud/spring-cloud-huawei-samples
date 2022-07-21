package com.huaweicloud.samples.porter.file.service;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
  /**
   * upload and return file id
   */
  @PostMapping(path = "/upload", produces = MediaType.TEXT_PLAIN_VALUE)
  String uploadFile(@RequestPart(name = "fileName") MultipartFile file);

  /**
   * delete file by id
   */
  @DeleteMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
  boolean deleteFile(@RequestParam(name = "id") String id);
}

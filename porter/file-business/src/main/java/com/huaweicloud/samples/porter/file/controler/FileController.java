package com.huaweicloud.samples.porter.file.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.huaweicloud.samples.porter.file.api.FileService;

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
  public boolean deleteFile(@RequestParam(name = "id") String id) {
    return fileService.deleteFile(id);
  }
}

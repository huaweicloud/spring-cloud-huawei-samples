package com.huaweicloud.samples.porter.file.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.huaweicloud.samples.porter.file.api.FileService;

@RestController
@RequestMapping(path = "/")
public class FileEndpoint implements FileService {
  private FileService fileService;

  @Autowired
  public FileEndpoint(FileService fileService) {
    this.fileService = fileService;
  }

  public String uploadFile(MultipartFile file) {
    return fileService.uploadFile(file);
  }

  public boolean deleteFile(String id) {
    return fileService.deleteFile(id);
  }
}

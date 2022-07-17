package com.huaweicloud.samples.porter.file.api;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
  String uploadFile(MultipartFile file);

  boolean deleteFile(String id);
}

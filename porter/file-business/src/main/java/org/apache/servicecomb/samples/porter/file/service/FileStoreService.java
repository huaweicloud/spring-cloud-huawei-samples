package org.apache.servicecomb.samples.porter.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * file storage service, can implement using file system, OBS, etc.
 */
public interface FileStoreService {

    String uploadFile(MultipartFile file);

    boolean deleteFile(String id);

}

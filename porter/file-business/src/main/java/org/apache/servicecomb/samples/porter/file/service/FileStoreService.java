package org.apache.servicecomb.samples.porter.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * file storage service, can implement using file system, OBS, etc.
 */
public interface FileStoreService {

    public String uploadFile(MultipartFile file);

    public boolean deleteFile(String id);

}

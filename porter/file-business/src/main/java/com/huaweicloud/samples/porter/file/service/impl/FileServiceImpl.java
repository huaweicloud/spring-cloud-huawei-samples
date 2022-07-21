package com.huaweicloud.samples.porter.file.service.impl;

import com.huaweicloud.samples.porter.file.service.FileService;
import com.huaweicloud.samples.porter.file.service.FileStoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileStoreService fileService;

    public String uploadFile(MultipartFile file) {
        return fileService.uploadFile(file);
    }

    public boolean deleteFile(String id) {
        return fileService.deleteFile(id);
    }
}

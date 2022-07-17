package com.huaweicloud.samples.porter.file.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.huaweicloud.samples.porter.file.api.FileStoreService;

/**
 *  Simple file storage implementation.
 *  Caution: file check and other security constraints not implemented. 
 */
@Component
public class LocalFileStoreService implements FileStoreService {
    // maxmum BUFFER_SIZE * BUFFER_NUM
    private static final int BUFFER_SIZE = 10240;

    private static final File BASE_FILE = new File(".");

    @Override
    public String uploadFile(MultipartFile file) {
        byte[] buffer = new byte[BUFFER_SIZE];
        String fileId = UUID.randomUUID().toString();

        File outFile = new File(BASE_FILE, fileId);
        int len;
        try (InputStream is = file.getInputStream(); OutputStream os = new FileOutputStream(outFile)) {
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
        } catch (IOException e) {
            return null;
        }
        return fileId;
    }

    @Override
    public boolean deleteFile(String id) {
        File outFile = new File(BASE_FILE, id);
        return outFile.delete();
    }

}

package org.apache.servicecomb.samples.porter.file.service;

import org.apache.servicecomb.foundation.common.utils.JsonUtils;
import org.apache.servicecomb.swagger.invocation.context.ContextUtils;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements org.apache.servicecomb.samples.porter.file.api.FileService {
    @Autowired
    private FileStoreService fileService;

    public String uploadFile(MultipartFile file) {
        return fileService.uploadFile(file);
    }

    public boolean deleteFile(String id) {
        String session = ContextUtils.getInvocationContext().getContext("session-info");
        if (session == null) {
            throw new InvocationException(403, "", "not allowed");
        } else {
            SessionInfo sessionInfo = null;
            try {
                sessionInfo = JsonUtils.readValue(session.getBytes("UTF-8"), SessionInfo.class);
            } catch (Exception e) {
                throw new InvocationException(403, "", "session not allowed");
            }
            if (sessionInfo == null || !sessionInfo.getRoleName().equals("admin")) {
                throw new InvocationException(403, "", "not allowed");
            }
        }
        return fileService.deleteFile(id);
    }
}

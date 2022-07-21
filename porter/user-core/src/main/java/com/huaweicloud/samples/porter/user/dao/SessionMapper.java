package com.huaweicloud.samples.porter.user.dao;

import com.huaweicloud.samples.porter.user.service.SessionInfo;

public interface SessionMapper {
    void createSession(SessionInfo sessionInfo);

    SessionInfoModel getSessioinInfo(String sessionId);
    
    void updateSessionInfo(String sessionId);
}

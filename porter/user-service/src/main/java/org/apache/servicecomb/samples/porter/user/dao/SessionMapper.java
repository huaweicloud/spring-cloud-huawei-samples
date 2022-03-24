package org.apache.servicecomb.samples.porter.user.dao;

import org.apache.servicecomb.samples.porter.user.api.SessionInfo;

public interface SessionMapper {
    void createSession(SessionInfo sessionInfo);

    SessionInfoModel getSessioinInfo(String sessionId);
    
    void updateSessionInfo(String sessionId);
}

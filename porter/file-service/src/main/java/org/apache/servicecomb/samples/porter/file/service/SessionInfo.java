package org.apache.servicecomb.samples.porter.file.service;

public class SessionInfo {
    private int id;

    private String sessiondId;

    private String userName;

    private String roleName;

    private java.sql.Timestamp creationTime;

    private java.sql.Timestamp activeTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSessiondId() {
        return sessiondId;
    }

    public void setSessiondId(String sessiondId) {
        this.sessiondId = sessiondId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public java.sql.Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(java.sql.Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public java.sql.Timestamp getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(java.sql.Timestamp activeTime) {
        this.activeTime = activeTime;
    }

}

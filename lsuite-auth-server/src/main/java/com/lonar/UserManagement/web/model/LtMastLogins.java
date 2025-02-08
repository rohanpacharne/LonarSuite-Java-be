package com.lonar.UserManagement.web.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LT_MAST_LOGINS")  // Replace with the actual table name if different
public class LtMastLogins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // For auto_increment in the database
    @Column(name = "login_id")
    private int loginId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "device_token")
    private String deviceToken;

    @Column(name = "device_type")
    private String deviceType;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "login_date")
    private Date loginDate;

    @Column(name = "logout_date")
    private Date logoutDate;

    @Column(name = "status")
    private String status;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name = "last_update_login")
    private Integer lastUpdateLogin;

    @Column(name = "last_updated_by")
    private Integer lastUpdatedBy;

    // Getters and Setters

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Date getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(Date logoutDate) {
        this.logoutDate = logoutDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

	@Override
	public String toString() {
		return "LtMastLogins [loginId=" + loginId + ", userId=" + userId + ", deviceToken=" + deviceToken
				+ ", deviceType=" + deviceType + ", ipAddress=" + ipAddress + ", loginDate=" + loginDate
				+ ", logoutDate=" + logoutDate + ", status=" + status + ", creationDate=" + creationDate
				+ ", createdBy=" + createdBy + ", lastUpdateDate=" + lastUpdateDate + ", lastUpdateLogin="
				+ lastUpdateLogin + ", lastUpdatedBy=" + lastUpdatedBy + "]";
	}

    // Override toString, equals, hashCode if necessary
}

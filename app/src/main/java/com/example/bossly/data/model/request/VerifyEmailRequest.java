package com.example.bossly.data.model.request;

public class VerifyEmailRequest {
    private String userId;
    private String tenantSlug;
    private String code;

    public VerifyEmailRequest(String userId, String tenantSlug, String code) {
        this.userId = userId;
        this.tenantSlug = tenantSlug;
        this.code = code;
    }

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getTenantSlug() { return tenantSlug; }
    public void setTenantSlug(String tenantSlug) { this.tenantSlug = tenantSlug; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
}

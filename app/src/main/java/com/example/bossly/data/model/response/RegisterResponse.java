package com.example.bossly.data.model.response;

public class RegisterResponse {
    private String userId;
    private String tenantId;
    private String tenantSlug;
    private boolean requiresEmailVerification;
    private String message;

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getTenantSlug() { return tenantSlug; }
    public void setTenantSlug(String tenantSlug) { this.tenantSlug = tenantSlug; }
    public boolean isRequiresEmailVerification() { return requiresEmailVerification; }
    public void setRequiresEmailVerification(boolean requiresEmailVerification) { this.requiresEmailVerification = requiresEmailVerification; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}

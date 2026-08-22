package com.example.bossly.data.model.response;

import java.util.List;

public class UserModel {
    private String userId;
    private String tenantId;
    private String tenantSlug;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private boolean emailConfirmed;
    private boolean twoFactorEnabled;
    private List<String> roles;
    private PlanModel plan;
    private int businessCount;
    private String preferredLanguage;
    private boolean mustChangePassword;

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    public String getTenantSlug() { return tenantSlug; }
    public void setTenantSlug(String tenantSlug) { this.tenantSlug = tenantSlug; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public boolean isEmailConfirmed() { return emailConfirmed; }
    public void setEmailConfirmed(boolean emailConfirmed) { this.emailConfirmed = emailConfirmed; }
    public boolean isTwoFactorEnabled() { return twoFactorEnabled; }
    public void setTwoFactorEnabled(boolean twoFactorEnabled) { this.twoFactorEnabled = twoFactorEnabled; }
    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }
    public PlanModel getPlan() { return plan; }
    public void setPlan(PlanModel plan) { this.plan = plan; }
    public int getBusinessCount() { return businessCount; }
    public void setBusinessCount(int businessCount) { this.businessCount = businessCount; }
    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }
    public boolean isMustChangePassword() { return mustChangePassword; }
    public void setMustChangePassword(boolean mustChangePassword) { this.mustChangePassword = mustChangePassword; }
}

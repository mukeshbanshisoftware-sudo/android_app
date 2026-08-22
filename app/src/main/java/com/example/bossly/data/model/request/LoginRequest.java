package com.example.bossly.data.model.request;

public class LoginRequest {
    private String email;
    private String password;
    private String tenantSlug;
    private boolean remember;

    public LoginRequest(String email, String password, String tenantSlug, boolean remember) {
        this.email = email;
        this.password = password;
        this.tenantSlug = tenantSlug;
        this.remember = remember;
    }

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getTenantSlug() { return tenantSlug; }
    public void setTenantSlug(String tenantSlug) { this.tenantSlug = tenantSlug; }
    public boolean isRemember() { return remember; }
    public void setRemember(boolean remember) { this.remember = remember; }
}

package com.example.bossly.data.model.response;

public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private String accessTokenExpiresUtc;
    private String refreshTokenExpiresUtc;
    private String tokenType;
    private UserModel user;

    // Getters and Setters
    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }
    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }
    public String getAccessTokenExpiresUtc() { return accessTokenExpiresUtc; }
    public void setAccessTokenExpiresUtc(String accessTokenExpiresUtc) { this.accessTokenExpiresUtc = accessTokenExpiresUtc; }
    public String getRefreshTokenExpiresUtc() { return refreshTokenExpiresUtc; }
    public void setRefreshTokenExpiresUtc(String refreshTokenExpiresUtc) { this.refreshTokenExpiresUtc = refreshTokenExpiresUtc; }
    public String getTokenType() { return tokenType; }
    public void setTokenType(String tokenType) { this.tokenType = tokenType; }
    public UserModel getUser() { return user; }
    public void setUser(UserModel user) { this.user = user; }
}

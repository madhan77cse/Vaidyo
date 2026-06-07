package com.vaidyo.vaidyo_backend.dto;

import com.vaidyo.vaidyo_backend.entity.User;

public class AuthResponse {

    private String token;
    private String tokenType;
    private Long userId;
    private String fullName;
    private String mobileNumber;
    private User.Role role;
    private String message;

    // ── Getters ────────────────────────────────────────────────
    public String getToken() { return token; }
    public String getTokenType() { return tokenType; }
    public Long getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public String getMobileNumber() { return mobileNumber; }
    public User.Role getRole() { return role; }
    public String getMessage() { return message; }

    // ── Setters ────────────────────────────────────────────────
    public void setToken(String token) { this.token = token; }
    public void setTokenType(String tokenType) { this.tokenType = tokenType; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    public void setRole(User.Role role) { this.role = role; }
    public void setMessage(String message) { this.message = message; }
}
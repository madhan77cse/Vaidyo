package com.vaidyo.vaidyo_backend.dto;

public class LoginRequest {

    private String mobileNumber;
    private String password;

    // ── Getters ────────────────────────────────────────────────
    public String getMobileNumber() { return mobileNumber; }
    public String getPassword() { return password; }

    // ── Setters ────────────────────────────────────────────────
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    public void setPassword(String password) { this.password = password; }
}
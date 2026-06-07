package com.vaidyo.vaidyo_backend.dto;

import java.time.LocalDateTime;

public class HealthLogResponse {

    private Long id;
    private Integer bpSystolic;
    private Integer bpDiastolic;
    private Double sugarLevel;
    private Double weight;
    private Integer pulseRate;
    private Double temperature;
    private String notes;
    private String alertStatus;
    private LocalDateTime loggedAt;

    // ── Getters ────────────────────────────────────────────────
    public Long getId() { return id; }
    public Integer getBpSystolic() { return bpSystolic; }
    public Integer getBpDiastolic() { return bpDiastolic; }
    public Double getSugarLevel() { return sugarLevel; }
    public Double getWeight() { return weight; }
    public Integer getPulseRate() { return pulseRate; }
    public Double getTemperature() { return temperature; }
    public String getNotes() { return notes; }
    public String getAlertStatus() { return alertStatus; }
    public LocalDateTime getLoggedAt() { return loggedAt; }

    // ── Setters ────────────────────────────────────────────────
    public void setId(Long id) { this.id = id; }
    public void setBpSystolic(Integer bpSystolic) { this.bpSystolic = bpSystolic; }
    public void setBpDiastolic(Integer bpDiastolic) { this.bpDiastolic = bpDiastolic; }
    public void setSugarLevel(Double sugarLevel) { this.sugarLevel = sugarLevel; }
    public void setWeight(Double weight) { this.weight = weight; }
    public void setPulseRate(Integer pulseRate) { this.pulseRate = pulseRate; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setAlertStatus(String alertStatus) { this.alertStatus = alertStatus; }
    public void setLoggedAt(LocalDateTime loggedAt) { this.loggedAt = loggedAt; }
}
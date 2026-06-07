package com.vaidyo.vaidyo_backend.dto;

import java.time.LocalTime;
import java.time.LocalDateTime;

public class MedicineResponse {

    private Long id;
    private String medicineName;
    private String dosage;
    private String frequency;
    private LocalTime reminderTime;
    private String photoUrl;
    private String status;
    private String notes;
    private LocalDateTime createdAt;

    // ── Getters ────────────────────────────────────────────────
    public Long getId() { return id; }
    public String getMedicineName() { return medicineName; }
    public String getDosage() { return dosage; }
    public String getFrequency() { return frequency; }
    public LocalTime getReminderTime() { return reminderTime; }
    public String getPhotoUrl() { return photoUrl; }
    public String getStatus() { return status; }
    public String getNotes() { return notes; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // ── Setters ────────────────────────────────────────────────
    public void setId(Long id) { this.id = id; }
    public void setMedicineName(String medicineName) { this.medicineName = medicineName; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public void setReminderTime(LocalTime reminderTime) { this.reminderTime = reminderTime; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
    public void setStatus(String status) { this.status = status; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
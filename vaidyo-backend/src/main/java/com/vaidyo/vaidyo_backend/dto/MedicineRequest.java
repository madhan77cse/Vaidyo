package com.vaidyo.vaidyo_backend.dto;

public class MedicineRequest {

    private String medicineName;
    private String dosage;
    private String frequency;
    private String reminderTime;
    private String notes;

    // ── Getters ────────────────────────────────────────────────
    public String getMedicineName() { return medicineName; }
    public String getDosage() { return dosage; }
    public String getFrequency() { return frequency; }
    public String getReminderTime() { return reminderTime; }
    public String getNotes() { return notes; }

    // ── Setters ────────────────────────────────────────────────
    public void setMedicineName(String medicineName) { this.medicineName = medicineName; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public void setReminderTime(String reminderTime) { this.reminderTime = reminderTime; }
    public void setNotes(String notes) { this.notes = notes; }
}
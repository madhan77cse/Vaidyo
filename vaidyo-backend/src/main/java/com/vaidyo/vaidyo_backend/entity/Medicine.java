package com.vaidyo.vaidyo_backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "medicines")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private User patient;

    @Column(name = "medicine_name", nullable = false, length = 100)
    private String medicineName;

    @Column(name = "dosage", length = 50)
    private String dosage;

    @Column(name = "frequency", length = 50)
    private String frequency;

    @Column(name = "reminder_time")
    private LocalTime reminderTime;

    @Column(name = "photo_url", length = 255)
    private String photoUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private MedicineStatus status = MedicineStatus.ACTIVE;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // ── Constructors ───────────────────────────────────────────
    public Medicine() {}

    // ── Getters ────────────────────────────────────────────────
    public Long getId() { return id; }
    public User getPatient() { return patient; }
    public String getMedicineName() { return medicineName; }
    public String getDosage() { return dosage; }
    public String getFrequency() { return frequency; }
    public LocalTime getReminderTime() { return reminderTime; }
    public String getPhotoUrl() { return photoUrl; }
    public MedicineStatus getStatus() { return status; }
    public String getNotes() { return notes; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // ── Setters ────────────────────────────────────────────────
    public void setId(Long id) { this.id = id; }
    public void setPatient(User patient) { this.patient = patient; }
    public void setMedicineName(String medicineName) { this.medicineName = medicineName; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public void setReminderTime(LocalTime reminderTime) { this.reminderTime = reminderTime; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
    public void setStatus(MedicineStatus status) { this.status = status; }
    public void setNotes(String notes) { this.notes = notes; }

    // ── Enum ───────────────────────────────────────────────────
    public enum MedicineStatus {
        ACTIVE, INACTIVE
    }
}
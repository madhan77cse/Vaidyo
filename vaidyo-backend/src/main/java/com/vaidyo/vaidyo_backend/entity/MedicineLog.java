package com.vaidyo.vaidyo_backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "medicine_logs")
public class MedicineLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_id", nullable = false)
    private Medicine medicine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private User patient;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private LogStatus status;

    @Column(name = "scheduled_time")
    private LocalDateTime scheduledTime;

    @Column(name = "taken_at")
    private LocalDateTime takenAt;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // ── Constructors ───────────────────────────────────────────
    public MedicineLog() {}

    // ── Getters ────────────────────────────────────────────────
    public Long getId() { return id; }
    public Medicine getMedicine() { return medicine; }
    public User getPatient() { return patient; }
    public LogStatus getStatus() { return status; }
    public LocalDateTime getScheduledTime() { return scheduledTime; }
    public LocalDateTime getTakenAt() { return takenAt; }
    public String getNotes() { return notes; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // ── Setters ────────────────────────────────────────────────
    public void setId(Long id) { this.id = id; }
    public void setMedicine(Medicine medicine) { this.medicine = medicine; }
    public void setPatient(User patient) { this.patient = patient; }
    public void setStatus(LogStatus status) { this.status = status; }
    public void setScheduledTime(LocalDateTime scheduledTime) { this.scheduledTime = scheduledTime; }
    public void setTakenAt(LocalDateTime takenAt) { this.takenAt = takenAt; }
    public void setNotes(String notes) { this.notes = notes; }

    // ── Enum ───────────────────────────────────────────────────
    public enum LogStatus {
        TAKEN, MISSED, PENDING
    }
}
package com.vaidyo.vaidyo_backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "health_logs")
public class HealthLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private User patient;

    @Column(name = "bp_systolic")
    private Integer bpSystolic;

    @Column(name = "bp_diastolic")
    private Integer bpDiastolic;

    @Column(name = "sugar_level")
    private Double sugarLevel;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "pulse_rate")
    private Integer pulseRate;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Enumerated(EnumType.STRING)
    @Column(name = "alert_status", length = 20)
    private AlertStatus alertStatus = AlertStatus.NORMAL;

    @CreationTimestamp
    @Column(name = "logged_at", updatable = false)
    private LocalDateTime loggedAt;

    // ── Constructors ───────────────────────────────────────────
    public HealthLog() {}

    // ── Getters ────────────────────────────────────────────────
    public Long getId() { return id; }
    public User getPatient() { return patient; }
    public Integer getBpSystolic() { return bpSystolic; }
    public Integer getBpDiastolic() { return bpDiastolic; }
    public Double getSugarLevel() { return sugarLevel; }
    public Double getWeight() { return weight; }
    public Integer getPulseRate() { return pulseRate; }
    public Double getTemperature() { return temperature; }
    public String getNotes() { return notes; }
    public AlertStatus getAlertStatus() { return alertStatus; }
    public LocalDateTime getLoggedAt() { return loggedAt; }

    // ── Setters ────────────────────────────────────────────────
    public void setId(Long id) { this.id = id; }
    public void setPatient(User patient) { this.patient = patient; }
    public void setBpSystolic(Integer bpSystolic) { this.bpSystolic = bpSystolic; }
    public void setBpDiastolic(Integer bpDiastolic) { this.bpDiastolic = bpDiastolic; }
    public void setSugarLevel(Double sugarLevel) { this.sugarLevel = sugarLevel; }
    public void setWeight(Double weight) { this.weight = weight; }
    public void setPulseRate(Integer pulseRate) { this.pulseRate = pulseRate; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setAlertStatus(AlertStatus alertStatus) { this.alertStatus = alertStatus; }

    // ── Enum ───────────────────────────────────────────────────
    public enum AlertStatus {
        NORMAL, WARNING, CRITICAL
    }
}
package com.vaidyo.vaidyo_backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "caretaker_patient",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"caretaker_id", "patient_id"})
        }
)
public class CaretakerPatient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caretaker_id", nullable = false)
    private User caretaker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private User patient;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private LinkStatus status = LinkStatus.ACTIVE;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // ── Constructors ───────────────────────────────────────────
    public CaretakerPatient() {}

    // ── Getters ────────────────────────────────────────────────
    public Long getId() { return id; }
    public User getCaretaker() { return caretaker; }
    public User getPatient() { return patient; }
    public LinkStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // ── Setters ────────────────────────────────────────────────
    public void setId(Long id) { this.id = id; }
    public void setCaretaker(User caretaker) { this.caretaker = caretaker; }
    public void setPatient(User patient) { this.patient = patient; }
    public void setStatus(LinkStatus status) { this.status = status; }

    // ── Enum ───────────────────────────────────────────────────
    public enum LinkStatus {
        ACTIVE, INACTIVE
    }
}
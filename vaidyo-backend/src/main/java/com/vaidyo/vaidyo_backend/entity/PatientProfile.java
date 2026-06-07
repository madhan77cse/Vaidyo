package com.vaidyo.vaidyo_backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "patient_profiles")
public class PatientProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "age")
    private Integer age;

    @Column(name = "blood_group", length = 5)
    private String bloodGroup;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "emergency_contact", length = 15)
    private String emergencyContact;

    @Column(name = "medical_notes", columnDefinition = "TEXT")
    private String medicalNotes;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // ── Constructors ───────────────────────────────────────────
    public PatientProfile() {}

    // ── Getters ────────────────────────────────────────────────
    public Long getId() { return id; }
    public User getUser() { return user; }
    public Integer getAge() { return age; }
    public String getBloodGroup() { return bloodGroup; }
    public String getAddress() { return address; }
    public String getEmergencyContact() { return emergencyContact; }
    public String getMedicalNotes() { return medicalNotes; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // ── Setters ────────────────────────────────────────────────
    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setAge(Integer age) { this.age = age; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
    public void setAddress(String address) { this.address = address; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }
    public void setMedicalNotes(String medicalNotes) { this.medicalNotes = medicalNotes; }
}
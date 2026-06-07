package com.vaidyo.vaidyo_backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "doctor_profiles")
public class DoctorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "speciality", nullable = false, length = 50)
    private Speciality speciality;

    @Column(name = "license_number", nullable = false, unique = true, length = 50)
    private String licenseNumber;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "consultation_fee")
    private Double consultationFee;

    @Column(name = "clinic_address", columnDefinition = "TEXT")
    private String clinicAddress;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Enumerated(EnumType.STRING)
    @Column(name = "verification_status", length = 20)
    private VerificationStatus verificationStatus = VerificationStatus.PENDING;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // ── Constructors ───────────────────────────────────────────
    public DoctorProfile() {}

    // ── Getters ────────────────────────────────────────────────
    public Long getId() { return id; }
    public User getUser() { return user; }
    public Speciality getSpeciality() { return speciality; }
    public String getLicenseNumber() { return licenseNumber; }
    public Integer getExperienceYears() { return experienceYears; }
    public Double getConsultationFee() { return consultationFee; }
    public String getClinicAddress() { return clinicAddress; }
    public String getBio() { return bio; }
    public VerificationStatus getVerificationStatus() { return verificationStatus; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // ── Setters ────────────────────────────────────────────────
    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setSpeciality(Speciality speciality) { this.speciality = speciality; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
    public void setExperienceYears(Integer experienceYears) { this.experienceYears = experienceYears; }
    public void setConsultationFee(Double consultationFee) { this.consultationFee = consultationFee; }
    public void setClinicAddress(String clinicAddress) { this.clinicAddress = clinicAddress; }
    public void setBio(String bio) { this.bio = bio; }
    public void setVerificationStatus(VerificationStatus verificationStatus) { this.verificationStatus = verificationStatus; }

    // ── Enums ──────────────────────────────────────────────────
    public enum Speciality {
        GENERAL, CARDIOLOGY, ORTHOPEDICS, DIABETOLOGY,
        NEUROLOGY, DERMATOLOGY, ENT, OPHTHALMOLOGY,
        GYNECOLOGY, PEDIATRICS, PSYCHIATRY, UROLOGY
    }

    public enum VerificationStatus {
        PENDING, VERIFIED, REJECTED
    }
}
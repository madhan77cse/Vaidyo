package com.vaidyo.vaidyo_backend.dto;

import com.vaidyo.vaidyo_backend.entity.DoctorProfile;
import com.vaidyo.vaidyo_backend.entity.User;

public class RegisterRequest {

    private String mobileNumber;
    private String password;
    private String fullName;
    private User.Role role;

    // Patient fields
    private Integer age;
    private String bloodGroup;
    private String address;
    private String emergencyContact;

    // Doctor fields
    private String licenseNumber;
    private DoctorProfile.Speciality speciality;
    private Integer experienceYears;
    private Double consultationFee;

    // Caretaker fields
    private String patientMobileNumber;

    // ── Getters ────────────────────────────────────────────────
    public String getMobileNumber() { return mobileNumber; }
    public String getPassword() { return password; }
    public String getFullName() { return fullName; }
    public User.Role getRole() { return role; }
    public Integer getAge() { return age; }
    public String getBloodGroup() { return bloodGroup; }
    public String getAddress() { return address; }
    public String getEmergencyContact() { return emergencyContact; }
    public String getLicenseNumber() { return licenseNumber; }
    public DoctorProfile.Speciality getSpeciality() { return speciality; }
    public Integer getExperienceYears() { return experienceYears; }
    public Double getConsultationFee() { return consultationFee; }
    public String getPatientMobileNumber() { return patientMobileNumber; }

    // ── Setters ────────────────────────────────────────────────
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    public void setPassword(String password) { this.password = password; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setRole(User.Role role) { this.role = role; }
    public void setAge(Integer age) { this.age = age; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
    public void setAddress(String address) { this.address = address; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
    public void setSpeciality(DoctorProfile.Speciality speciality) { this.speciality = speciality; }
    public void setExperienceYears(Integer experienceYears) { this.experienceYears = experienceYears; }
    public void setConsultationFee(Double consultationFee) { this.consultationFee = consultationFee; }
    public void setPatientMobileNumber(String patientMobileNumber) { this.patientMobileNumber = patientMobileNumber; }
}
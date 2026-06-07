package com.vaidyo.vaidyo_backend.service;

import com.vaidyo.vaidyo_backend.dto.MedicineRequest;
import com.vaidyo.vaidyo_backend.dto.MedicineResponse;
import com.vaidyo.vaidyo_backend.entity.Medicine;
import com.vaidyo.vaidyo_backend.entity.MedicineLog;
import com.vaidyo.vaidyo_backend.entity.User;
import com.vaidyo.vaidyo_backend.repository.MedicineLogRepository;
import com.vaidyo.vaidyo_backend.repository.MedicineRepository;
import com.vaidyo.vaidyo_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;
    private final MedicineLogRepository medicineLogRepository;
    private final UserRepository userRepository;

    public MedicineService(MedicineRepository medicineRepository,
                           MedicineLogRepository medicineLogRepository,
                           UserRepository userRepository) {
        this.medicineRepository = medicineRepository;
        this.medicineLogRepository = medicineLogRepository;
        this.userRepository = userRepository;
    }

    // ── Add Medicine ───────────────────────────────────────────
    public MedicineResponse addMedicine(Long patientId,
                                        MedicineRequest request) {
        User patient = userRepository.findById(patientId)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found"));

        Medicine medicine = new Medicine();
        medicine.setPatient(patient);
        medicine.setMedicineName(request.getMedicineName());
        medicine.setDosage(request.getDosage());
        medicine.setFrequency(request.getFrequency());
        medicine.setNotes(request.getNotes());

        if (request.getReminderTime() != null
                && !request.getReminderTime().isEmpty()) {
            medicine.setReminderTime(
                    LocalTime.parse(request.getReminderTime()));
        }

        medicineRepository.save(medicine);
        return mapToResponse(medicine);
    }

    // ── Get All Medicines for Patient ──────────────────────────
    public List<MedicineResponse> getMyMedicines(Long patientId) {
        return medicineRepository
                .findByPatientIdAndStatus(
                        patientId,
                        Medicine.MedicineStatus.ACTIVE)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ── Mark Medicine as Taken ─────────────────────────────────
    public String markAsTaken(Long medicineId, Long patientId) {
        Medicine medicine = medicineRepository
                .findById(medicineId)
                .orElseThrow(() ->
                        new RuntimeException("Medicine not found"));

        User patient = userRepository.findById(patientId)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found"));

        MedicineLog log = new MedicineLog();
        log.setMedicine(medicine);
        log.setPatient(patient);
        log.setStatus(MedicineLog.LogStatus.TAKEN);
        log.setTakenAt(LocalDateTime.now());
        log.setScheduledTime(LocalDateTime.now());

        medicineLogRepository.save(log);
        return "Medicine marked as taken!";
    }

    // ── Get Medicine Logs ──────────────────────────────────────
    public List<MedicineLog> getMedicineLogs(Long patientId) {
        return medicineLogRepository.findByPatientId(patientId);
    }

    // ── Delete Medicine ────────────────────────────────────────
    public String deleteMedicine(Long medicineId) {
        Medicine medicine = medicineRepository
                .findById(medicineId)
                .orElseThrow(() ->
                        new RuntimeException("Medicine not found"));

        medicine.setStatus(Medicine.MedicineStatus.INACTIVE);
        medicineRepository.save(medicine);
        return "Medicine removed successfully!";
    }

    // ── Update Photo URL ───────────────────────────────────────
    public void updatePhotoUrl(Long medicineId, String photoUrl) {
        Medicine medicine = medicineRepository
                .findById(medicineId)
                .orElseThrow(() ->
                        new RuntimeException("Medicine not found"));
        medicine.setPhotoUrl(photoUrl);
        medicineRepository.save(medicine);
    }

    // ── Helper: Map entity to response ────────────────────────
    public MedicineResponse mapToResponse(Medicine medicine) {
        MedicineResponse response = new MedicineResponse();
        response.setId(medicine.getId());
        response.setMedicineName(medicine.getMedicineName());
        response.setDosage(medicine.getDosage());
        response.setFrequency(medicine.getFrequency());
        response.setReminderTime(medicine.getReminderTime());
        response.setPhotoUrl(medicine.getPhotoUrl());
        response.setStatus(medicine.getStatus().name());
        response.setNotes(medicine.getNotes());
        response.setCreatedAt(medicine.getCreatedAt());
        return response;
    }
}
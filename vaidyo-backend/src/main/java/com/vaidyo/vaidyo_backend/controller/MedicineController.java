package com.vaidyo.vaidyo_backend.controller;

import com.vaidyo.vaidyo_backend.dto.MedicineRequest;
import com.vaidyo.vaidyo_backend.dto.MedicineResponse;
import com.vaidyo.vaidyo_backend.entity.MedicineLog;
import com.vaidyo.vaidyo_backend.service.MedicineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patient")
@CrossOrigin(origins = "*")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    // ── POST /api/patient/medicines ────────────────────────────
    @PostMapping("/medicines")
    public ResponseEntity<?> addMedicine(
            @RequestParam Long patientId,
            @RequestBody MedicineRequest request) {
        try {
            MedicineResponse response =
                    medicineService.addMedicine(patientId, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ── GET /api/patient/medicines ─────────────────────────────
    @GetMapping("/medicines")
    public ResponseEntity<?> getMyMedicines(
            @RequestParam Long patientId) {
        try {
            List<MedicineResponse> medicines =
                    medicineService.getMyMedicines(patientId);
            return ResponseEntity.ok(medicines);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ── POST /api/patient/medicines/{id}/taken ─────────────────
    @PostMapping("/medicines/{id}/taken")
    public ResponseEntity<?> markAsTaken(
            @PathVariable Long id,
            @RequestParam Long patientId) {
        try {
            String result =
                    medicineService.markAsTaken(id, patientId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ── GET /api/patient/medicines/logs ────────────────────────
    @GetMapping("/medicines/logs")
    public ResponseEntity<?> getMedicineLogs(
            @RequestParam Long patientId) {
        try {
            List<MedicineLog> logs =
                    medicineService.getMedicineLogs(patientId);
            return ResponseEntity.ok(logs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ── DELETE /api/patient/medicines/{id} ─────────────────────
    @DeleteMapping("/medicines/{id}")
    public ResponseEntity<?> deleteMedicine(
            @PathVariable Long id) {
        try {
            String result = medicineService.deleteMedicine(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ── POST /api/patient/medicines/{id}/photo ─────────────────
    @PostMapping("/medicines/{id}/photo")
    public ResponseEntity<?> uploadPhoto(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        try {
            String uploadDir = "uploads/medicines/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String fileName = UUID.randomUUID()
                    + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);
            Files.write(filePath, file.getBytes());

            String photoUrl = "/uploads/medicines/" + fileName;
            medicineService.updatePhotoUrl(id, photoUrl);

            return ResponseEntity.ok("Photo uploaded: " + photoUrl);
        } catch (IOException e) {
            return ResponseEntity.badRequest()
                    .body("Upload failed: " + e.getMessage());
        }
    }
}
package com.vaidyo.vaidyo_backend.controller;

import com.vaidyo.vaidyo_backend.dto.HealthLogRequest;
import com.vaidyo.vaidyo_backend.dto.HealthLogResponse;
import com.vaidyo.vaidyo_backend.service.HealthLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class HealthLogController {

    private final HealthLogService healthLogService;

    public HealthLogController(HealthLogService healthLogService) {
        this.healthLogService = healthLogService;
    }

    // ── POST /api/patient/health ───────────────────────────────
    @PostMapping("/api/patient/health")
    public ResponseEntity<?> addHealthLog(
            @RequestParam Long patientId,
            @RequestBody HealthLogRequest request) {
        try {
            HealthLogResponse response =
                    healthLogService.addHealthLog(
                            patientId, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    // ── GET /api/patient/health ────────────────────────────────
    @GetMapping("/api/patient/health")
    public ResponseEntity<?> getHealthHistory(
            @RequestParam Long patientId) {
        try {
            List<HealthLogResponse> logs =
                    healthLogService.getHealthHistory(patientId);
            return ResponseEntity.ok(logs);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    // ── GET /api/patient/health/latest ─────────────────────────
    @GetMapping("/api/patient/health/latest")
    public ResponseEntity<?> getLatestReading(
            @RequestParam Long patientId) {
        try {
            HealthLogResponse response =
                    healthLogService.getLatestReading(patientId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    // ── GET /api/caretaker/patients/{id}/health ────────────────
    @GetMapping("/api/caretaker/patients/{patientId}/health")
    public ResponseEntity<?> getPatientHealth(
            @PathVariable Long patientId) {
        try {
            List<HealthLogResponse> logs =
                    healthLogService.getHealthHistory(patientId);
            return ResponseEntity.ok(logs);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }
}
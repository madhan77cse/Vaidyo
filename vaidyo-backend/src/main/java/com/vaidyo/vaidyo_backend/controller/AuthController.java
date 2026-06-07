package com.vaidyo.vaidyo_backend.controller;

import com.vaidyo.vaidyo_backend.dto.AuthResponse;
import com.vaidyo.vaidyo_backend.dto.LoginRequest;
import com.vaidyo.vaidyo_backend.dto.RegisterRequest;
import com.vaidyo.vaidyo_backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // ── POST /api/auth/register ────────────────────────────────
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request) {
        try {
            AuthResponse response = authService.register(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ── POST /api/auth/login ───────────────────────────────────
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(401)
                    .body("Invalid mobile number or password");
        }
    }

    // ── GET /api/auth/health ───────────────────────────────────
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Vaidyo API is running!");
    }
}
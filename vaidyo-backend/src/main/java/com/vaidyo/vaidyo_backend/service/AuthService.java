package com.vaidyo.vaidyo_backend.service;

import com.vaidyo.vaidyo_backend.dto.AuthResponse;
import com.vaidyo.vaidyo_backend.dto.LoginRequest;
import com.vaidyo.vaidyo_backend.dto.RegisterRequest;
import com.vaidyo.vaidyo_backend.entity.CaretakerPatient;
import com.vaidyo.vaidyo_backend.entity.DoctorProfile;
import com.vaidyo.vaidyo_backend.entity.PatientProfile;
import com.vaidyo.vaidyo_backend.entity.User;
import com.vaidyo.vaidyo_backend.repository.CaretakerPatientRepository;
import com.vaidyo.vaidyo_backend.repository.DoctorProfileRepository;
import com.vaidyo.vaidyo_backend.repository.PatientProfileRepository;
import com.vaidyo.vaidyo_backend.repository.UserRepository;
import com.vaidyo.vaidyo_backend.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PatientProfileRepository patientProfileRepository;
    private final DoctorProfileRepository doctorProfileRepository;
    private final CaretakerPatientRepository caretakerPatientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthService(UserRepository userRepository,
                       PatientProfileRepository patientProfileRepository,
                       DoctorProfileRepository doctorProfileRepository,
                       CaretakerPatientRepository caretakerPatientRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil,
                       AuthenticationManager authenticationManager,
                       UserDetailsServiceImpl userDetailsService) {
        this.userRepository = userRepository;
        this.patientProfileRepository = patientProfileRepository;
        this.doctorProfileRepository = doctorProfileRepository;
        this.caretakerPatientRepository = caretakerPatientRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    // ── REGISTER ───────────────────────────────────────────────
    public AuthResponse register(RegisterRequest request) {

        // 1. Check if mobile already exists
        if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
            throw new RuntimeException("Mobile number already registered");
        }

        // 2. Create and save User
        User user = new User(
                request.getMobileNumber(),
                passwordEncoder.encode(request.getPassword()),
                request.getFullName(),
                request.getRole()
        );
        userRepository.save(user);

        // 3. Create role-specific profile
        if (request.getRole() == User.Role.PATIENT) {
            PatientProfile profile = new PatientProfile();
            profile.setUser(user);
            profile.setAge(request.getAge());
            profile.setBloodGroup(request.getBloodGroup());
            profile.setAddress(request.getAddress());
            profile.setEmergencyContact(request.getEmergencyContact());
            patientProfileRepository.save(profile);

        } else if (request.getRole() == User.Role.DOCTOR) {
            DoctorProfile profile = new DoctorProfile();
            profile.setUser(user);
            profile.setSpeciality(request.getSpeciality());
            profile.setLicenseNumber(request.getLicenseNumber());
            profile.setExperienceYears(request.getExperienceYears());
            profile.setConsultationFee(request.getConsultationFee());
            doctorProfileRepository.save(profile);

        } else if (request.getRole() == User.Role.CARETAKER) {
            // Link caretaker to patient if mobile provided
            if (request.getPatientMobileNumber() != null) {
                userRepository.findByMobileNumber(
                                request.getPatientMobileNumber())
                        .ifPresent(patient -> {
                            CaretakerPatient link = new CaretakerPatient();
                            link.setCaretaker(user);
                            link.setPatient(patient);
                            caretakerPatientRepository.save(link);
                        });
            }
        }

        // 4. Generate token and return
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(
                        user.getMobileNumber());
        String token = jwtUtil.generateToken(
                userDetails,
                user.getRole().name(),
                user.getId()
        );

        return buildAuthResponse(token, user, "Registered successfully");
    }

    // ── LOGIN ──────────────────────────────────────────────────
    public AuthResponse login(LoginRequest request) {

        // 1. Authenticate credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getMobileNumber(),
                        request.getPassword()
                )
        );

        // 2. Load user
        User user = userRepository
                .findByMobileNumber(request.getMobileNumber())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // 3. Generate token
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(
                        user.getMobileNumber());
        String token = jwtUtil.generateToken(
                userDetails,
                user.getRole().name(),
                user.getId()
        );

        return buildAuthResponse(token, user, "Login successful");
    }

    // ── Helper ─────────────────────────────────────────────────
    private AuthResponse buildAuthResponse(String token,
                                           User user,
                                           String message) {
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setTokenType("Bearer");
        response.setUserId(user.getId());
        response.setFullName(user.getFullName());
        response.setMobileNumber(user.getMobileNumber());
        response.setRole(user.getRole());
        response.setMessage(message);
        return response;
    }
}
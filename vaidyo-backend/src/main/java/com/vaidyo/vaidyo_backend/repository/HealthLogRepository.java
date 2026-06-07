package com.vaidyo.vaidyo_backend.repository;

import com.vaidyo.vaidyo_backend.entity.HealthLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HealthLogRepository
        extends JpaRepository<HealthLog, Long> {

    List<HealthLog> findByPatientIdOrderByLoggedAtDesc(
            Long patientId);

    Optional<HealthLog> findFirstByPatientIdOrderByLoggedAtDesc(
            Long patientId);

    List<HealthLog> findByPatientIdAndAlertStatus(
            Long patientId,
            HealthLog.AlertStatus alertStatus);
}
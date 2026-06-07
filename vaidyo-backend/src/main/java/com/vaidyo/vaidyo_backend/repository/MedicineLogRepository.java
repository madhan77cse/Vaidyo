package com.vaidyo.vaidyo_backend.repository;

import com.vaidyo.vaidyo_backend.entity.MedicineLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineLogRepository extends JpaRepository<MedicineLog, Long> {

    List<MedicineLog> findByPatientId(Long patientId);

    List<MedicineLog> findByMedicineId(Long medicineId);

    List<MedicineLog> findByPatientIdAndStatus(
            Long patientId, MedicineLog.LogStatus status);

    Optional<MedicineLog> findByMedicineIdAndScheduledTimeBetween(
            Long medicineId,
            LocalDateTime start,
            LocalDateTime end);
}
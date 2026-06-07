package com.vaidyo.vaidyo_backend.repository;

import com.vaidyo.vaidyo_backend.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    List<Medicine> findByPatientId(Long patientId);

    List<Medicine> findByPatientIdAndStatus(
            Long patientId, Medicine.MedicineStatus status);
}
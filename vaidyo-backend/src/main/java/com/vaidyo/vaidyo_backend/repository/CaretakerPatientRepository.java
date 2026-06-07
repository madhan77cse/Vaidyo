package com.vaidyo.vaidyo_backend.repository;

import com.vaidyo.vaidyo_backend.entity.CaretakerPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaretakerPatientRepository extends JpaRepository<CaretakerPatient, Long> {

    List<CaretakerPatient> findByCaretakerId(Long caretakerId);

    List<CaretakerPatient> findByPatientId(Long patientId);

    boolean existsByCaretakerIdAndPatientId(Long caretakerId, Long patientId);
}
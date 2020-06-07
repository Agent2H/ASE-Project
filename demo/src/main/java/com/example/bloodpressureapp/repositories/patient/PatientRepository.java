package com.example.bloodpressureapp.repositories.patient;

import com.example.bloodpressureapp.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {

    Page<Patient> findPage(Pageable pageable);

    List<Patient> findAll();

    Optional<Patient> findById(Long id);

    Patient findPatientByUsername(String username);

    Patient save( Patient patient);

    void delete( Patient patient);

    Patient update(Patient patient);

}

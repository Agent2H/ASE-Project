package com.example.bloodpressureapp.repositories.patient;

import com.example.bloodpressureapp.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepositoryJpa extends JpaRepository<Patient, Long> {
}

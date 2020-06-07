package com.example.bloodpressureapp.repositories.physician;

import com.example.bloodpressureapp.entity.Physician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhysicianRepositoryJpa extends JpaRepository<Physician, Long> {
}

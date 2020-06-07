package com.example.bloodpressureapp.repositories.physician;

import com.example.bloodpressureapp.entity.Physician;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PhysicianRepository {

    Page<Physician> findPage(Pageable pageable);

    List<Physician> findAll();

    Optional<Physician> findById(Long id);

    Physician findPhysicianByUsername(String username);

    Physician save(Physician physician);

    void delete(Physician physician);

    Physician update(Physician physician);
}

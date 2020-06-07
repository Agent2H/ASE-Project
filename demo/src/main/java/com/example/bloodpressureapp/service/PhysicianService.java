package com.example.bloodpressureapp.service;

import com.example.bloodpressureapp.DTO.Entities.PhysicianDTO;
import com.example.bloodpressureapp.DTO.Entities.PhysicianUpdateDTO;
import com.example.bloodpressureapp.DTO.Entities.UserCredentials;
import com.example.bloodpressureapp.entity.Physician;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PhysicianService {

    public Page<PhysicianDTO> findPage(Pageable pageable);

    public List<PhysicianDTO> getAllPhysicians();

    public Optional<Physician> getPhysicianById(Long id);

    public PhysicianDTO findUserByName(String username);

    public String login(String username, String password);

    public PhysicianDTO register(UserCredentials credentials);

    public void delete(Long id);

    public PhysicianDTO update(PhysicianUpdateDTO physician);
}

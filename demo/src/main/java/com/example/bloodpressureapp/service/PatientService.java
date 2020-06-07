package com.example.bloodpressureapp.service;

import com.example.bloodpressureapp.DTO.Entities.PatientDTO;
import com.example.bloodpressureapp.DTO.Entities.PatientUpdateDTO;
import com.example.bloodpressureapp.DTO.Entities.UserCredentials;
import com.example.bloodpressureapp.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PatientService {
    public Page<PatientDTO> findPage(Pageable pageable);

    public List<PatientDTO> getAllPatients();

    public Optional<Patient> getPatientById(Long id);

    public PatientDTO findUserByName(String username);

    public String login(String username, String password);

    public PatientDTO register(UserCredentials credentials);

    public void delete(Long id);

    public PatientDTO update(PatientUpdateDTO patient);
}

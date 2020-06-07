package com.example.bloodpressureapp.service;

import com.example.bloodpressureapp.DTO.Entities.PatientDTO;
import com.example.bloodpressureapp.entity.BP_Parameters;

import java.util.List;
import java.util.Optional;

public interface BPParametersService {

    public List<BP_Parameters> getAllBPParameters();

    public List<PatientDTO> getAllPatientId(Long id);

    public Optional<BP_Parameters> getBPParametersById(Long id);

    public void delete(Long id);

    public BP_Parameters createBPParameter(BP_Parameters bpParameters);

    public void update(BP_Parameters bpParameters);
}

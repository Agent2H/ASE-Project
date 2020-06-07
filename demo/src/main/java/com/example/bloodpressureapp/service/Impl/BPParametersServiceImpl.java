package com.example.bloodpressureapp.service.Impl;

import com.example.bloodpressureapp.DTO.DTOBuilder;
import com.example.bloodpressureapp.DTO.Entities.PatientDTO;
import com.example.bloodpressureapp.entity.BP_Parameters;
import com.example.bloodpressureapp.exceptions.CustomException;
import com.example.bloodpressureapp.repositories.BPParametersRepository;
import com.example.bloodpressureapp.service.BPParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public class BPParametersServiceImpl implements BPParametersService {

    @Autowired
    BPParametersRepository bpParametersRepository;

    @Override
    public List<BP_Parameters> getAllBPParameters(){
        return bpParametersRepository.findAll();
    }

    @Override
    public Optional<BP_Parameters> getBPParametersById(Long id){
        return bpParametersRepository.findById(id);
    }

    @Override
    public BP_Parameters createBPParameter(BP_Parameters bpParameters) {
        if (bpParametersRepository.isExisted(bpParameters.getUserName())) {
            throw new CustomException("Patient already existed", HttpStatus.CONFLICT);
        }
        return bpParametersRepository.save(bpParameters);
    }

    @Override
    public void delete(Long id) {
        Optional<BP_Parameters> bpParameters = bpParametersRepository.findById(id);
        if (!bpParameters.isPresent()) {
            throw new CustomException("Patient not found", HttpStatus.NOT_FOUND);
        }
        bpParametersRepository.delete(bpParameters.get());
    }

    @Override
    public void update(BP_Parameters bpParameters) {
        Optional<BP_Parameters> entity = bpParametersRepository.findById(bpParameters.getId());
        if (!entity.isPresent()) {
            throw new CustomException("Patient not found", HttpStatus.NOT_FOUND);
        }
        bpParametersRepository.update(bpParameters);
    }

    @Override
    public List<PatientDTO> getAllPatientId(Long id) {
        Optional<BP_Parameters> bp_parameters = bpParametersRepository.findById(id);
        if (!bp_parameters.isPresent()) {
            throw new CustomException("Patient not found", HttpStatus.NOT_FOUND);
        }
        return DTOBuilder.mapList(bp_parameters.get().getPatients(), PatientDTO.class);
    }



}

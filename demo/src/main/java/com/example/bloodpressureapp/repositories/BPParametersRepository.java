package com.example.bloodpressureapp.repositories;

import com.example.bloodpressureapp.entity.BP_Parameters;

import java.util.List;
import java.util.Optional;

public interface BPParametersRepository {

    public List<BP_Parameters> findAll();

    public Optional<BP_Parameters> findById(Long id);

    BP_Parameters save(BP_Parameters bpParameters);

    public void delete(BP_Parameters bpParameters);

    public void update(BP_Parameters bpParameters);

    public boolean isExisted(String bpParameters);


}

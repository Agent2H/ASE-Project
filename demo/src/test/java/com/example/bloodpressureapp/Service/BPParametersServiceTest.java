package com.example.bloodpressureapp.Service;

import com.example.bloodpressureapp.entity.BP_Parameters;
import com.example.bloodpressureapp.service.BPParametersService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class BPParametersServiceTest {

    public List<BP_Parameters> getAllBPParameters() {

        List<BP_Parameters> listofBPParameters = new ArrayList<>();
        listofBPParameters.add(new BP_Parameters((long) 1,"huylequang",2,3,4));
        listofBPParameters.add(new BP_Parameters((long) 1,"lequanghuy",2,6,7));

        return listofBPParameters;
    }



}

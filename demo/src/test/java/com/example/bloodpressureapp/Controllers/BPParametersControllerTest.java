package com.example.bloodpressureapp.Controllers;

import com.example.bloodpressureapp.Service.BPParametersServiceTest;
import com.example.bloodpressureapp.entity.BP_Parameters;
import com.example.bloodpressureapp.entity.Patient;
import com.example.bloodpressureapp.service.BPParametersService;
import com.example.bloodpressureapp.service.Impl.BPParametersServiceImpl;
import com.example.bloodpressureapp.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RestController
public class BPParametersControllerTest {

    @Autowired
    BPParametersServiceTest bpParametersServiceTest;

    @GetMapping("/bpparameters")
    public List<BP_Parameters> getAllBPParameters(){
        return bpParametersServiceTest.getAllBPParameters();
    }




}

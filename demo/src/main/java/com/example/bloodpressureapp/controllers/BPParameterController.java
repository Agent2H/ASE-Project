package com.example.bloodpressureapp.controllers;

import com.example.bloodpressureapp.entity.BP_Parameters;
import com.example.bloodpressureapp.service.BPParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bpparameters")
public class BPParameterController {

    @Autowired
    BPParametersService service;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<BP_Parameters>> getAllBpParameters() {
        List<BP_Parameters> bpParameters = service.getAllBPParameters();
        return new ResponseEntity<>(bpParameters, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<BP_Parameters> createBPParameters(@RequestBody BP_Parameters bpParameters) {
        bpParameters = service.createBPParameter(bpParameters);
        return new ResponseEntity<>(bpParameters, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<BP_Parameters> deleteBPParameters(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<BP_Parameters> updateBPParameters(@RequestBody BP_Parameters newBPParameters) {
        service.update(newBPParameters);
        return new ResponseEntity<>(newBPParameters, HttpStatus.OK);
    }

}

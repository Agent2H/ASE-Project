package com.example.bloodpressureapp.controllers;

import com.example.bloodpressureapp.DTO.Entities.LoginResponseDTO;
import com.example.bloodpressureapp.DTO.Entities.UserCredentials;
import com.example.bloodpressureapp.entity.Patient;
import com.example.bloodpressureapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Page<com.example.bloodpressureapp.DTO.Entities.PatientDTO>> getPatients(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by("id").ascending();
        } else if (sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }
        assert sortable != null;
        Pageable pageable = PageRequest.of(page, size, sortable);
        return new ResponseEntity<>(patientService.findPage(pageable), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Patient> deletePatient(@PathVariable Long id) {
        patientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // TODO: Refactor later
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserCredentials userLogin) {
        String token = patientService.login(userLogin.getUsername(), userLogin.getPassword());
        return new ResponseEntity<>(new LoginResponseDTO(userLogin.getUsername(), token), HttpStatus.OK);
    }

}

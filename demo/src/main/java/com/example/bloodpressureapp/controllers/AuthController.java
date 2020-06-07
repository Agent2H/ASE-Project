package com.example.bloodpressureapp.controllers;


import com.example.bloodpressureapp.DTO.DTOBuilder;
import com.example.bloodpressureapp.DTO.Entities.LoginResponseDTO;
import com.example.bloodpressureapp.DTO.Entities.PatientDTO;
import com.example.bloodpressureapp.DTO.Entities.PhysicianDTO;
import com.example.bloodpressureapp.DTO.Entities.UserCredentials;
import com.example.bloodpressureapp.service.PatientService;
import com.example.bloodpressureapp.service.PhysicianService;
import com.example.bloodpressureapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PhysicianService physicianService;

    // TODO: Refactor later
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserCredentials userLogin) {
        // Check user/admin
        String token = "";
        try {
            return new ResponseEntity<>(DTOBuilder.loginResponseDTO(new LoginResponseDTO(userLogin.getUsername(),
                    userService.login(userLogin.getUsername(), userLogin.getPassword()))), HttpStatus.OK);
        } catch (Exception ex) {
            token = patientService.login(userLogin.getUsername(), userLogin.getPassword());
        }
        return new ResponseEntity<>(DTOBuilder.loginResponseDTO(
                new LoginResponseDTO(
                        userLogin.getUsername(), token)), HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<PatientDTO> register(@RequestBody UserCredentials userCredentials) {
        return new ResponseEntity<>(patientService.register(userCredentials), HttpStatus.CREATED);
    }







}

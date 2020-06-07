package com.example.bloodpressureapp.service.Impl;

import com.example.bloodpressureapp.DTO.DTOBuilder;
import com.example.bloodpressureapp.DTO.Entities.PatientDTO;
import com.example.bloodpressureapp.DTO.Entities.PatientUpdateDTO;
import com.example.bloodpressureapp.DTO.Entities.UserCredentials;
import com.example.bloodpressureapp.entity.Patient;
import com.example.bloodpressureapp.exceptions.CustomException;
import com.example.bloodpressureapp.repositories.patient.PatientRepository;
import com.example.bloodpressureapp.security.JwtTokenProvider;
import com.example.bloodpressureapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public Page<PatientDTO> findPage(Pageable pageable) {
        Page<Patient> entityPage = patientRepository.findPage(pageable);
        return DTOBuilder.mapPage(entityPage, com.example.bloodpressureapp.DTO.Entities.PatientDTO.class);
    }

    @Override
    public List<com.example.bloodpressureapp.DTO.Entities.PatientDTO> getAllPatients() {
        List<Patient> entities = patientRepository.findAll();
        return DTOBuilder.mapList(entities, com.example.bloodpressureapp.DTO.Entities.PatientDTO.class);
    }

    @Override
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public com.example.bloodpressureapp.DTO.Entities.PatientDTO findUserByName(String username) {
        Patient patient = patientRepository.findPatientByUsername(username);
        if (patient == null) {
            throw new CustomException("Customer not found", HttpStatus.NOT_FOUND);
        }
        return DTOBuilder.mapObject(patient, com.example.bloodpressureapp.DTO.Entities.PatientDTO.class);
    }

    @Override
    public String login(String username, String password) {
        try {
            Authentication authentication =
                    authenticationManager
                            .authenticate(
                                    new UsernamePasswordAuthenticationToken(
                                            username,
                                            password));
            return jwtTokenProvider.generateToken(authentication);
        } catch (AuthenticationException ex) {
            throw new CustomException("Invalid username or password", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public  com.example.bloodpressureapp.DTO.Entities.PatientDTO register(UserCredentials credentials) {

        Patient tempPatient = patientRepository.findPatientByUsername(credentials.getUsername());
        if (tempPatient != null) {
            if (tempPatient.getEmail().equals(credentials.getEmail())) {
                throw new CustomException("Username & Email already existed", HttpStatus.CONFLICT);
            }
            throw new CustomException("Username already existed", HttpStatus.CONFLICT);
        }
        Patient patient = new Patient();
        patient.setUsername(credentials.getUsername());
        patient.setEmail(credentials.getEmail());
        patient.setPassword(passwordEncoder.encode(credentials.getPassword()));
        LocalDate ld = LocalDate.now();
        ld = ld.plusYears(2);
        Date expiredDate = Date.valueOf(ld);
        return DTOBuilder.mapObject(patientRepository.save(patient),  com.example.bloodpressureapp.DTO.Entities.PatientDTO.class);
    }

    @Override
    public void delete(Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (!optionalPatient.isPresent()) {
            throw new CustomException("Patient not found", HttpStatus.NOT_FOUND);
        }
        patientRepository.delete(optionalPatient.get());
    }

    @Override
    public com.example.bloodpressureapp.DTO.Entities.PatientDTO update(PatientUpdateDTO patient) {
        Patient currentPatient = patientRepository.findPatientByUsername(patient.getPatient().getUsername());
        if (currentPatient == null) {
            throw new CustomException("Patient " + patient.getPatient().getUsername() + " not found", HttpStatus.NOT_FOUND);
        }
        String currentPass = patient.getCurrentPassword();
        if (!currentPass.equals("")) {
            if (passwordEncoder.matches(currentPass, currentPatient.getPassword())) {
                currentPatient.setPassword(passwordEncoder.encode(patient.getNewPassword()));
            } else {
                throw new CustomException("Invalid password", HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }
        currentPatient.setFirstName(patient.getPatient().getFirstName());
        currentPatient.setLastName(patient.getPatient().getLastName());
        currentPatient.setBirthday(patient.getPatient().getBirthday());
        currentPatient.setGender(patient.getPatient().getGender());
        currentPatient.setAddress(patient.getPatient().getAddress());
        currentPatient.setPhoneNumber(patient.getPatient().getPhoneNumber());
        currentPatient.setEmail(patient.getPatient().getEmail());

        return DTOBuilder.mapObject(patientRepository.update(currentPatient), com.example.bloodpressureapp.DTO.Entities.PatientDTO.class);
    }
}

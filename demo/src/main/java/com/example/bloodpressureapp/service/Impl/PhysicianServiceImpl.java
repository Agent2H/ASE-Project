package com.example.bloodpressureapp.service.Impl;

import com.example.bloodpressureapp.DTO.DTOBuilder;
import com.example.bloodpressureapp.DTO.Entities.PhysicianDTO;
import com.example.bloodpressureapp.DTO.Entities.PhysicianUpdateDTO;
import com.example.bloodpressureapp.DTO.Entities.UserCredentials;
import com.example.bloodpressureapp.entity.Physician;
import com.example.bloodpressureapp.exceptions.CustomException;
import com.example.bloodpressureapp.repositories.physician.PhysicianRepository;
import com.example.bloodpressureapp.security.JwtTokenProvider;
import com.example.bloodpressureapp.service.PhysicianService;
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

public class PhysicianServiceImpl implements PhysicianService {

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public Page<PhysicianDTO> findPage(Pageable pageable) {
        Page<Physician> entityPage = physicianRepository.findPage(pageable);
        return DTOBuilder.mapPage(entityPage, com.example.bloodpressureapp.DTO.Entities.PhysicianDTO.class);
    }

    @Override
    public List<com.example.bloodpressureapp.DTO.Entities.PhysicianDTO> getAllPhysicians() {
        List<Physician> entities = physicianRepository.findAll();
        return DTOBuilder.mapList(entities, com.example.bloodpressureapp.DTO.Entities.PhysicianDTO.class);
    }

    @Override
    public Optional<Physician> getPhysicianById(Long id) {
        return physicianRepository.findById(id);
    }

    @Override
    public com.example.bloodpressureapp.DTO.Entities.PhysicianDTO findUserByName(String username) {
        Physician physician = physicianRepository.findPhysicianByUsername(username);
        if (physician == null) {
            throw new CustomException("Physician not found", HttpStatus.NOT_FOUND);
        }
        return DTOBuilder.mapObject(physician, com.example.bloodpressureapp.DTO.Entities.PhysicianDTO.class);
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
    public  com.example.bloodpressureapp.DTO.Entities.PhysicianDTO register(UserCredentials credentials) {

        Physician tempPhysician = physicianRepository.findPhysicianByUsername(credentials.getUsername());
        if (tempPhysician != null) {
            if (tempPhysician.getEmail().equals(credentials.getEmail())) {
                throw new CustomException("Username & Email already existed", HttpStatus.CONFLICT);
            }
            throw new CustomException("Username already existed", HttpStatus.CONFLICT);
        }
        Physician physician = new Physician();
        physician.setUserName(credentials.getUsername());
        physician.setEmail(credentials.getEmail());
        physician.setPassword(passwordEncoder.encode(credentials.getPassword()));
        LocalDate ld = LocalDate.now();
        ld = ld.plusYears(2);
        Date expiredDate = Date.valueOf(ld);
        return DTOBuilder.mapObject(physicianRepository.save(physician),  com.example.bloodpressureapp.DTO.Entities.PhysicianDTO.class);
    }

    @Override
    public void delete(Long id) {
        Optional<Physician> optionalPhysician = physicianRepository.findById(id);
        if (!optionalPhysician.isPresent()) {
            throw new CustomException("Physician not found", HttpStatus.NOT_FOUND);
        }
        physicianRepository.delete(optionalPhysician.get());
    }

    @Override
    public com.example.bloodpressureapp.DTO.Entities.PhysicianDTO update(PhysicianUpdateDTO physician) {
        Physician currentPhysician = physicianRepository.findPhysicianByUsername(physician.getPhysician().getUsername());
        if (currentPhysician == null) {
            throw new CustomException("Physician " + physician.getPhysician().getUsername() + " not found", HttpStatus.NOT_FOUND);
        }
        String currentPass = physician.getCurrentPassword();
        if (!currentPass.equals("")) {
            if (passwordEncoder.matches(currentPass, currentPhysician.getPassword())) {
                currentPhysician.setPassword(passwordEncoder.encode(physician.getNewPassword()));
            } else {
                throw new CustomException("Invalid password", HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }
        currentPhysician.setFirstName(physician.getPhysician().getFirstName());
        currentPhysician.setLastName(physician.getPhysician().getLastName());
        currentPhysician.setPhoneNumber(physician.getPhysician().getPhoneNumber());
        currentPhysician.setEmail(physician.getPhysician().getEmail());

        return DTOBuilder.mapObject(physicianRepository.update(currentPhysician), com.example.bloodpressureapp.DTO.Entities.PhysicianDTO.class);
    }



}

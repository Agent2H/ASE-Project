package com.example.bloodpressureapp.repositories.imp;

import com.example.bloodpressureapp.entity.Patient;
import com.example.bloodpressureapp.repositories.patient.PatientRepository;
import com.example.bloodpressureapp.repositories.patient.PatientRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class PatientRepositoryImpl implements PatientRepository {

    @Autowired
    @Lazy
    PatientRepositoryJpa patientRepositoryJpa;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Page<Patient> findPage(Pageable pageable) {
        return patientRepositoryJpa.findAll(pageable);
    }

    @Override
    @Transactional
    public List<Patient> findAll() {
        return entityManager.createQuery("FROM Patient", Patient.class).getResultList();
    }

    @Override
    @Transactional
    public Optional<Patient> findById(Long id) {
        return Optional.of(entityManager.find(Patient.class, id));
    }

    @Override
    @Transactional
    public Patient findPatientByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT c FROM Patient c WHERE c.userName = '" + username + "'", Patient.class).getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Patient save(Patient patient) {
        entityManager.persist(patient);
        return patient;
    }

    @Override
    @Transactional
    public void delete(Patient patient) {
        entityManager.remove(patient);
    }

    @Override
    @Transactional
    public Patient update(Patient patient) {
        entityManager.merge(patient);
        return patient;
    }




}

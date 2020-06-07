package com.example.bloodpressureapp.repositories.imp;

import com.example.bloodpressureapp.entity.Physician;
import com.example.bloodpressureapp.repositories.physician.PhysicianRepository;
import com.example.bloodpressureapp.repositories.physician.PhysicianRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class PhysicianRepositoryImpl implements PhysicianRepository {

    @Autowired
    @Lazy
    PhysicianRepositoryJpa physicianRepositoryJpa;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Page<Physician> findPage(Pageable pageable) {
        return physicianRepositoryJpa.findAll(pageable);
    }

    @Override
    @Transactional
    public List<Physician> findAll() {
        return entityManager.createQuery("FROM Physician", Physician.class).getResultList();
    }

    @Override
    @Transactional
    public Optional<Physician> findById(Long id) {
        return Optional.of(entityManager.find(Physician.class, id));
    }

    @Override
    @Transactional
    public Physician findPhysicianByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT c FROM Physician c WHERE c.userName = '" + username + "'", Physician.class).getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Physician save(Physician physician) {
        entityManager.persist(physician);
        return physician;
    }

    @Override
    @Transactional
    public void delete(Physician physician) {
        entityManager.remove(physician);
    }

    @Override
    @Transactional
    public Physician update(Physician physician) {
        entityManager.merge(physician);
        return physician;
    }






}

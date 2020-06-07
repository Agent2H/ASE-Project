package com.example.bloodpressureapp.repositories.imp;

import com.example.bloodpressureapp.entity.BP_Parameters;
import com.example.bloodpressureapp.repositories.BPParametersRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class BPParametersRepositoryImpl implements BPParametersRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<BP_Parameters> findAll() {
        return entityManager.createQuery("FROM BP_Parameters", BP_Parameters.class).getResultList();
    }


    @Override
    @Transactional
    public Optional<BP_Parameters> findById(Long id) {
        return Optional.of(entityManager.find(BP_Parameters.class, id));
    }

    @Override
    @Transactional
    public BP_Parameters save(BP_Parameters bpParameters) {
        entityManager.persist(bpParameters);
        return bpParameters;
    }

    @Override
    @Transactional
    public void delete(BP_Parameters bpParameters) {
        entityManager.remove(bpParameters);
    }

    @Override
    @Transactional
    public void update(BP_Parameters bpParameters) {
        entityManager.merge(bpParameters);
    }

    @Override
    @Transactional
    public boolean isExisted(String patientName) {
        try {
            return entityManager.createQuery("SELECT c FROM Patient c WHERE c.userName = '" + patientName + "'", BP_Parameters.class).getSingleResult() != null;
        } catch (Exception ex) {
            return false;
        }
    }



}

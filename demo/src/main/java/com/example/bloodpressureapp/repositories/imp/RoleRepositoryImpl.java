package com.example.bloodpressureapp.repositories.imp;

import com.example.bloodpressureapp.entity.Role;
import com.example.bloodpressureapp.repositories.RoleRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<com.example.bloodpressureapp.entity.Role> findAll() {
        return entityManager.createQuery("FROM Role", Role.class).getResultList();
    }

    @Override
    @Transactional
    public Optional<Role> findById(Long id) {
        return Optional.of(entityManager.find(Role.class, id));
    }

    @Override
    @Transactional
    public List<Role> findRolesByNames(List<String> roleNames) {
        StringBuilder query = new StringBuilder("SELECT c FROM Role c WHERE ");
        boolean firstElement = true;
        for (String roleName : roleNames) {
            if(firstElement){
                firstElement = false;
                query.append("c.role = '").append(roleName).append("'");
            } else {
                query.append(" OR ").append("c.role = '").append(roleName).append("'");
            }
        }
        return entityManager.createQuery(query.toString(), Role.class).getResultList();
    }

    @Override
    @Transactional
    public Role save(Role role) {
        entityManager.persist(role);
        return role;
    }

    @Override
    @Transactional
    public void delete(Role role) {
        entityManager.remove(role);
    }

    @Override
    @Transactional
    public void update(Role role) {
        entityManager.merge(role);
    }
}

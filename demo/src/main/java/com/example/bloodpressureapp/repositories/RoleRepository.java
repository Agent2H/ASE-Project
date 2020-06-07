package com.example.bloodpressureapp.repositories;

import com.example.bloodpressureapp.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    public List<Role> findAll();

    public Optional<Role> findById(Long id);

    List<Role> findRolesByNames(List<String> roleNames);

    public Role save(Role role);

    public void delete(Role role);

    public void update(Role role);
}

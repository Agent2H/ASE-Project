package com.example.bloodpressureapp.service;

import com.example.bloodpressureapp.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    public List<Role> getAllRoles();

    public Optional<Role> getRoleById(Long id);

    public Role save(Role role);

    public void delete(Role role);

    public void update(Role role);
}

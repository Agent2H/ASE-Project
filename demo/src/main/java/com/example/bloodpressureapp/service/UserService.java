package com.example.bloodpressureapp.service;

import com.example.bloodpressureapp.DTO.Entities.UserDTO;
import com.example.bloodpressureapp.DTO.Entities.UserUpdateDTO;
import com.example.bloodpressureapp.entity.Role;
import com.example.bloodpressureapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<UserDTO> findPage(Pageable pageable);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO findUserByUsername(String username);

    public String login(String username, String password);

    public UserDTO register(User user);

    public void delete(Long id);

    UserDTO update(UserUpdateDTO user);

    List<Role> getUserRoles(Long id);

    void updateUserRoles(Long id, List<Long> roleIds);

    boolean isExist(String username);
}

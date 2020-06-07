package com.example.bloodpressureapp.service.Impl;

import com.example.bloodpressureapp.DTO.DTOBuilder;
import com.example.bloodpressureapp.DTO.Entities.UserDTO;
import com.example.bloodpressureapp.DTO.Entities.UserUpdateDTO;
import com.example.bloodpressureapp.entity.Role;
import com.example.bloodpressureapp.entity.User;
import com.example.bloodpressureapp.exceptions.CustomException;
import com.example.bloodpressureapp.repositories.RoleRepository;
import com.example.bloodpressureapp.repositories.user.UserRepository;
import com.example.bloodpressureapp.security.JwtTokenProvider;
import com.example.bloodpressureapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Page<UserDTO> findPage(Pageable pageable) {
        Page<User> entityPage = userRepository.findPage(pageable);
        return DTOBuilder.mapPage(entityPage, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return DTOBuilder.mapList( userRepository.findAll(), UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return DTOBuilder.mapObject(userRepository.findById(id), UserDTO.class);
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new CustomException("User not found", HttpStatus.NOT_FOUND);
        }
        return DTOBuilder.mapObject(user, UserDTO.class);
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

    public UserDTO register(User user) {
        if (userRepository.isExist(user.getUserName())) {
            throw new CustomException("User already existed", HttpStatus.CONFLICT);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2L).get());
        user.setRoles(roles);
        return DTOBuilder.mapObject(userRepository.save(user), UserDTO.class);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new CustomException("User not found", HttpStatus.NOT_FOUND);
        }
        userRepository.delete(user);
    }

    @Override
    public UserDTO update(UserUpdateDTO user) {
        User currentUser = userRepository.findUserByUsername(user.getUser().getUsername());
        if (currentUser == null) {
            throw new CustomException("User " + user.getUser().getUsername() + " not found",
                    HttpStatus.NOT_FOUND);
        }
        String currentPass = user.getCurrentPassword();
        if (!currentPass.equals("")) {
            if (passwordEncoder.matches(currentPass, currentUser.getPassword())) {
                currentUser.setPassword(passwordEncoder.encode(user.getNewPassword()));
            } else {
                throw new CustomException("Invalid password", HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }
        List<Role> roles = new ArrayList<>();
        for (Role r: user.getUser().getRoles()) {
            roles.add(roleRepository.findById(r.getId()).get());
        }
        // Update roles
        currentUser.setRoles(roles);
        currentUser.setFullname(user.getUser().getFullname());

        try {
            currentUser = userRepository.update(currentUser);
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return DTOBuilder.mapObject(currentUser, UserDTO.class);
    }

    @Override
    public List<Role> getUserRoles(Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new CustomException("User not existed", HttpStatus.NOT_FOUND);
        }
        return user.getRoles();
    }

    @Override
    public void updateUserRoles(Long id, List<Long> roleIds) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new CustomException("User with Id: " + "not found", HttpStatus.NOT_FOUND);
        }
        List<Role> roles = new ArrayList<>();
        for (Long i : roleIds) {
            Optional<Role> temp = roleRepository.findById(i);
            if (!temp.isPresent()) {
                throw new CustomException("Role not exist", HttpStatus.NOT_FOUND);

            }
            roles.add(temp.get());
        }
        user.modifyRoles(roles);
        userRepository.save(user);
    }

    @Override
    public boolean isExist(String username) {
        return userRepository.isExist(username);
    }


}


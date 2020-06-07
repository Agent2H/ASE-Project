package com.example.bloodpressureapp.repositories.user;

import com.example.bloodpressureapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJpa extends JpaRepository<User, Long> {
}

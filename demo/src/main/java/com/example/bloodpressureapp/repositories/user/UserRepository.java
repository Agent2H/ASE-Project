package com.example.bloodpressureapp.repositories.user;

import com.example.bloodpressureapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepository {
    Page<User> findPage(Pageable pageable);

    List<User> findAll();

    User findById(Long id);

    boolean isExist(String userName);

    User findUserByUsername(String userName);

    User save(User user);

    void delete(User user);

    User update(User user);
}

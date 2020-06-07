package com.example.bloodpressureapp.repositories.event;

import com.example.bloodpressureapp.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepositoryJpa extends JpaRepository<Events, Long> {
}

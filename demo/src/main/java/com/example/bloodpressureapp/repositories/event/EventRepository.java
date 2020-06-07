package com.example.bloodpressureapp.repositories.event;

import com.example.bloodpressureapp.entity.Events;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EventRepository {

    public Page<Events> findPage(Pageable pageable);

    public List<Events> findAll();

    Optional<Events> findById(Long id);

    public Events save(Events events);

    public void delete(Events events);

    public void update(Events events);
}

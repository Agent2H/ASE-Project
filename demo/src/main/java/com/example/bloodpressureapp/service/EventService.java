package com.example.bloodpressureapp.service;

import com.example.bloodpressureapp.DTO.Entities.EventsDTO;
import com.example.bloodpressureapp.entity.Events;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EventService {

    public Page<EventsDTO> findPage(Pageable pageable);

    public List<EventsDTO> getAllEvents();

    public Optional<Events> getOptionalEventById(Long id);

    public Events save(Events events);

    public void delete(Events events);

    public void update(Events events);

    public EventsDTO getEventById(Long id);
}

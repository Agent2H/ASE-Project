package com.example.bloodpressureapp.service.Impl;

import com.example.bloodpressureapp.DTO.DTOBuilder;
import com.example.bloodpressureapp.DTO.Entities.EventsDTO;
import com.example.bloodpressureapp.entity.Events;
import com.example.bloodpressureapp.exceptions.CustomException;
import com.example.bloodpressureapp.repositories.event.EventRepository;
import com.example.bloodpressureapp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Page<EventsDTO> findPage(Pageable pageable) {
        Page<Events> entityEvent = eventRepository.findPage(pageable);
        return DTOBuilder.mapPage(entityEvent, EventsDTO.class);
    }

    @Override
    public List<EventsDTO> getAllEvents() {
        List<Events> entities = eventRepository.findAll();
        return DTOBuilder.mapList(entities, EventsDTO.class);
    }

    @Override
    public Optional<Events> getOptionalEventById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public Events save(Events events) {
        return eventRepository.save(events);
    }

    @Override
    public void delete(Events events) {
        eventRepository.delete(events);
    }

    @Override
    public void update(Events events) {
        Optional<Events> currentOrder = eventRepository.findById(events.getId());
        if (!currentOrder.isPresent()) {
            throw new CustomException("Event not found", HttpStatus.NOT_FOUND);
        }
        currentOrder.get().setEventType(events.getEventType());
        eventRepository.update(events);
    }

    @Override
    public EventsDTO getEventById(Long id) {
        Optional<Events> entity = eventRepository.findById(id);
        if (entity.isPresent()) {
            return DTOBuilder.mapObject(entity.get(), EventsDTO.class);
        }
        throw new CustomException("Event not found", HttpStatus.NOT_FOUND);
    }
}

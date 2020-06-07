package com.example.bloodpressureapp.service.Impl;

import com.example.bloodpressureapp.entity.EventDetail;
import com.example.bloodpressureapp.repositories.EventDetailRepository;
import com.example.bloodpressureapp.service.EventDetailService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class EventDetailServiceImpl implements EventDetailService {

    @Autowired
    EventDetailRepository eventDetailRepository;

    @Override
    public List<EventDetail> getAllEventDetails() {
        return eventDetailRepository.findAll();
    }

    @Override
    public Optional<EventDetail> getEventDetailById(Long id) {
        return eventDetailRepository.findById(id);
    }

    @Override
    public EventDetail save(EventDetail eventDetail) {
        return eventDetailRepository.save(eventDetail);
    }

    @Override
    public void delete(EventDetail eventDetail) {
        eventDetailRepository.delete(eventDetail);
    }

    @Override
    public void update(EventDetail eventDetail) {
        eventDetailRepository.update(eventDetail);
    }
}

package com.example.bloodpressureapp.service;

import com.example.bloodpressureapp.entity.EventDetail;

import java.util.List;
import java.util.Optional;

public interface EventDetailService {

    public List<EventDetail> getAllEventDetails();

    public Optional<EventDetail> getEventDetailById(Long id);

    public EventDetail save(EventDetail eventDetail);

    public void delete(EventDetail eventDetail);

    public void update(EventDetail eventDetail);

}

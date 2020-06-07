package com.example.bloodpressureapp.repositories;

import com.example.bloodpressureapp.entity.EventDetail;

import java.util.List;
import java.util.Optional;

public interface EventDetailRepository {
    public List<EventDetail> findAll();

    Optional<EventDetail> findById(Long id);

    public EventDetail save(EventDetail eventDetail);

    public void delete(EventDetail eventDetail);

    public void update(EventDetail eventDetail);
}

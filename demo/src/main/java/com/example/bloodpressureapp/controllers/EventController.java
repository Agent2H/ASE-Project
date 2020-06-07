package com.example.bloodpressureapp.controllers;

import com.example.bloodpressureapp.DTO.Entities.EventsDTO;
import com.example.bloodpressureapp.entity.Events;
import com.example.bloodpressureapp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Page<EventsDTO>> getAllEvents(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by("id").ascending();
        } else if (sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }
        assert sortable != null;
        Pageable pageable = PageRequest.of(page, size, sortable);
        return new ResponseEntity<>(eventService.findPage(pageable), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Events> createFAQ(@RequestBody Events events) {
        events = eventService.save(events);
        return new ResponseEntity<>(events, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Events> deleteEvent(@PathVariable Long id) {
        Optional<Events> feedback = eventService.getOptionalEventById(id);
        if (!feedback.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        eventService.delete(feedback.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Events> update(@RequestBody Events newEvent) {
        eventService.update(newEvent);
        return new ResponseEntity<>(newEvent, HttpStatus.OK);
    }


}

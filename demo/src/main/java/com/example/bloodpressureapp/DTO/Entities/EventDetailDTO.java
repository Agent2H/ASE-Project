package com.example.bloodpressureapp.DTO.Entities;



public class EventDetailDTO {

    private Long id;

    private EventsDTO events;

    private String TypesOfEvents;

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id=id;
    }

    public EventsDTO getEvents() {
        return events;
    }

    public void setEvents(EventsDTO events) {
        this.events=events;
    }

    public String getTypesOfEvents() {
        return TypesOfEvents;
    }

    public void setTypesOfEvents(String typesOfEvents) {
        this.TypesOfEvents=typesOfEvents;
    }

    public EventDetailDTO(){

    }

    public EventDetailDTO(Long id, EventsDTO events, String TypesOfEvents) {
        this.id=id;
        this.events=events;
        this.TypesOfEvents=TypesOfEvents;
    }

}

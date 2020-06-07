package com.example.bloodpressureapp.DTO.Entities;

import java.sql.Timestamp;
import java.util.List;

public class EventsDTO {

    private Long id;

    private PatientDTO patient;

    private List<EventDetailDTO> eventDetails;

    private String eventType;

    private Timestamp createdDate;

    private String notes;

    private String messages;

    public EventsDTO() {

    }

    public List<EventDetailDTO> getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(List<EventDetailDTO> eventDetails) {
        this.eventDetails=eventDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId() {
        this.id=id;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient=patient;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType() {
        this.eventType=eventType;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate=createdDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes=notes;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages=messages;
    }
}

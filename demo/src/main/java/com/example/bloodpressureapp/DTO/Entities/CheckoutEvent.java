package com.example.bloodpressureapp.DTO.Entities;

import com.example.bloodpressureapp.entity.BP_Parameters;
import com.example.bloodpressureapp.entity.Patient;

import java.util.Date;

public class CheckoutEvent {
    private Long id;
    private Patient patient;
    private BP_Parameters bpParameters;
    private String eventType;
    private Date createdDate;
    private String notes;
    private String messages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {this.id = id;}

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) { this.patient = patient;}

    public BP_Parameters getBpParameters() {
        return bpParameters;
    }

    public void setBpParameters(BP_Parameters bpParameters) {
        this.bpParameters = bpParameters;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType=eventType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes){
        this.notes=notes;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages=messages;
    }

    public CheckoutEvent(Long id, Patient patient, BP_Parameters bpParameters,String eventType, Date createdDate, String notes, String messages){
        this.id=id;
        this.patient=patient;
        this.bpParameters=bpParameters;
        this.eventType=eventType;
        this.createdDate=createdDate;
        this.notes=notes;
        this.messages=messages;
    }
}

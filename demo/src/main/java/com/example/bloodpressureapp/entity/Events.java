package com.example.bloodpressureapp.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "events")
public class Events {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(
            cascade = {CascadeType.REMOVE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "BPParameter_id")
    private BP_Parameters bpParameters;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "event",
            cascade = {CascadeType.REMOVE, CascadeType.DETACH, CascadeType.REFRESH})

    private List<EventDetail> eventDetails;


    @Column(name = "event_type")
    private String eventType;

    @Column(name = "created_Date")
    private Timestamp createdDate;

    @Column(name = "notes")
    private String notes;

    @Column(name = "messages")
    private String messages;

    public Events(){
        
    }

    public Events(Long id, String eventType, Timestamp createdDate, String notes, String messages){
        super();
        this.id = id;
        this.eventType = eventType;
        this.createdDate = createdDate;
        this.notes = notes;
        this.messages = messages;
    }

    public String getMessages(){
        return messages;
    }

    public void setMessages(String messages){
        this.messages = messages;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Patient getPatient(){
        return patient;
    }

    public void setPatient(Patient patient){
        this.patient = patient;
    }

    public BP_Parameters getBpParameters() {
        return bpParameters;
    }

    public void setBpParameters(BP_Parameters bpParameters) {
        this.bpParameters = bpParameters;
    }

    public String getEventType(){
        return eventType;
    }

    public void setEventType(String eventType){
        this.eventType = eventType;
    }

    public String getNotes(){
        return notes;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }

    public Timestamp getCreatedDate(){
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate){
        this.createdDate = createdDate;
    }

    public List<EventDetail> getEventDetails() { return eventDetails;}

    public void setEventDetails(List<EventDetail> eventDetails) {
        this.eventDetails=eventDetails;
    }
}

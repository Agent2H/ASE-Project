package com.example.bloodpressureapp.entity;

import javax.persistence.*;

@Entity
@Table(name="event_detail")
public class EventDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "event_id")
    private Events events;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "TypeOfEvents")
    private String TypeOfEvents;

    public EventDetail() {

    }

    public EventDetail(Long id, Events events, Patient patient, String TypeOfEvents){
        this.id=id;
        this.events=events;
        this.patient=patient;
        this.TypeOfEvents=TypeOfEvents;
    }

    public Events getEvents() {return events;}

    public void setEvents(Events events) {
        this.events = events;
    }

    public Patient getPatient() {return patient;}

    public void setPatient(Patient patient) {
        this.patient=patient;
    }

    public String getTypeOfEvents() {return TypeOfEvents; }

    public void setTypeOfEvents(String TypeofEvents) { this.TypeOfEvents=TypeofEvents;}

    public Long getId() { return id;}

    public void setId(Long id) {this.id=id;}


}

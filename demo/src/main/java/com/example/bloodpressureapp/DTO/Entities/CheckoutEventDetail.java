package com.example.bloodpressureapp.DTO.Entities;

public class CheckoutEventDetail {
    private Long patientId;
    private String TypeOfEvents;

    public Long getPatientId() {
        return  patientId;
    }

    public void setPatientId(Long patientId){
        this.patientId=patientId;
    }

    public String getTypeOfEvents() {
        return TypeOfEvents;
    }

    public void setTypeOfEvents(String TypeOfEvents) {
        this.TypeOfEvents=TypeOfEvents;
    }

    public CheckoutEventDetail(Long patientId, String TypeofEvents ){
        this.patientId=patientId;
        this.TypeOfEvents=TypeofEvents;
    }







}

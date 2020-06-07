package com.example.bloodpressureapp.DTO.Entities;

import java.util.List;

public class Checkout {
    private CheckoutEvent event;

    private List<CheckoutEventDetail> eventDetails;

    public CheckoutEvent getEvent() {
        return event;
    }

    public void setEvent(CheckoutEvent event) {
        this.event=event;
    }
    public List<CheckoutEventDetail> getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(List<CheckoutEventDetail> eventDetails) {
        this.eventDetails=eventDetails;
    }

    public Checkout(CheckoutEvent event, List<CheckoutEventDetail> eventDetails) {
        this.event=event;
        this.eventDetails=eventDetails;
    }



}

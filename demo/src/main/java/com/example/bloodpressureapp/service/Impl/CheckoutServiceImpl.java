package com.example.bloodpressureapp.service.Impl;

import com.example.bloodpressureapp.DTO.Entities.Checkout;
import com.example.bloodpressureapp.DTO.Entities.CheckoutEventDetail;
import com.example.bloodpressureapp.entity.BP_Parameters;
import com.example.bloodpressureapp.entity.EventDetail;
import com.example.bloodpressureapp.entity.Events;
import com.example.bloodpressureapp.entity.Patient;
import com.example.bloodpressureapp.exceptions.CustomException;
import com.example.bloodpressureapp.repositories.event.EventRepository;
import com.example.bloodpressureapp.repositories.patient.PatientRepository;
import com.example.bloodpressureapp.repositories.physician.PhysicianRepository;
import com.example.bloodpressureapp.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createEvent(Checkout checkoutRequest) {
        Events events = new Events();
        Patient patient = patientRepository.findPatientByUsername(checkoutRequest.getEvent().getPatient().getUsername());
        if (patient==null) {
            throw new CustomException("Patient not found", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        List<EventDetail> eventDetails = new ArrayList<>();
        for (CheckoutEventDetail eventDetail: checkoutRequest.getEventDetails()) {
            Optional<Patient> patient1 = patientRepository.findById(eventDetail.getPatientId());
            if (patient1.isPresent()) {
                EventDetail tempDetail = new EventDetail();
                tempDetail.setPatient(patient1.get());
                tempDetail.setTypeOfEvents(eventDetail.getTypeOfEvents());
                tempDetail.setEvents(events);
                eventDetails.add(tempDetail);
            } else {
                throw new CustomException("Request not found", HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }

        events.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        events.setPatient(patient);
        events.setNotes(checkoutRequest.getEvent().getNotes());
        events.setMessages(checkoutRequest.getEvent().getMessages());
        events.setEventType(checkoutRequest.getEvent().getEventType());
        events.setEventDetails(eventDetails);
        if (checkoutRequest.getEvent().getBpParameters() != null) {
            BP_Parameters bp_parameters = new BP_Parameters();
            bp_parameters.setUserName(checkoutRequest.getEvent().getBpParameters().getUserName());
            bp_parameters.setSystolic(checkoutRequest.getEvent().getBpParameters().getSystolic());
            bp_parameters.setDiastolic(checkoutRequest.getEvent().getBpParameters().getDiastolic());
            bp_parameters.setHeart_rate(checkoutRequest.getEvent().getBpParameters().getHeart_rate());
            events.setBpParameters(bp_parameters);
        }
        eventRepository.save(events);
    }
}

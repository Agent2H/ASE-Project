package com.example.bloodpressureapp.controllers;

import com.example.bloodpressureapp.DTO.Entities.Checkout;
import com.example.bloodpressureapp.entity.EventDetail;
import com.example.bloodpressureapp.service.CheckoutService;
import com.example.bloodpressureapp.service.EventDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private EventDetailService eventDetailService;
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> registerPatient(@RequestBody Checkout checkout) {
        checkoutService.createEvent(checkout);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public List<EventDetail> get() {
        System.out.println("here");
        return eventDetailService.getAllEventDetails();
    }
}

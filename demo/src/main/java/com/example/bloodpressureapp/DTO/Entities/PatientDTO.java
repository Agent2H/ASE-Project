package com.example.bloodpressureapp.DTO.Entities;

import java.sql.Date;

public class PatientDTO {
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String address;

    private String phoneNumber;

    private String email;

    private Short gender;

    private Date birthday;

    public PatientDTO() {

    }

    public PatientDTO(Long id, String username, String firstName, String lastName, String address, String phoneNumber,
                      String email, Short gender, Date birthday) {
        super();
        this.id=id;
        this.username=username;
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.email=email;
        this.gender=gender;
        this.birthday=birthday;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Short getGender() {
        return gender;
    }

    public void setGender(Short gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }





}

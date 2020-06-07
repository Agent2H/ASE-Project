package com.example.bloodpressureapp.DTO.Entities;

public class PhysicianDTO {
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    public PhysicianDTO(){

    }

    public PhysicianDTO(Long id, String username, String firstName, String lastName, String phoneNumber, String email){
        super();
        this.id=id;
        this.username=username;
        this.firstName=firstName;
        this.lastName=lastName;
        this.phoneNumber=phoneNumber;
        this.email=email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail(){return email;}

    public void setEmail(String email) {this.email=email;}

}

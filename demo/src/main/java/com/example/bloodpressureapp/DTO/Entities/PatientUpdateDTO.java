package com.example.bloodpressureapp.DTO.Entities;

public class PatientUpdateDTO {

    private PatientDTO patient;

    private String currentPassword;

    private String newPassword;

    public PatientDTO getPatient() { return patient; }

    public void setPatient(PatientDTO patient) {this.patient=patient;}

    public String getCurrentPassword() {return currentPassword; }

    public void setCurrentPassword(String currentPassword ) {this.currentPassword=currentPassword;}

    public PatientUpdateDTO(PatientDTO patient, String currentPassword, String newPassword) {
        this.patient=patient;
        this.currentPassword=currentPassword;
        this.newPassword=newPassword;
    }

    public String getNewPassword() {return newPassword;}

    public void setNewPassword(String newPassword) {this.newPassword=newPassword;}
}

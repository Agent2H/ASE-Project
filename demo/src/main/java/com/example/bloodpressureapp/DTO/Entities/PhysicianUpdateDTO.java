package com.example.bloodpressureapp.DTO.Entities;

public class PhysicianUpdateDTO {
    private PhysicianDTO physician;

    private String currentPassword;

    private String newPassword;

    public PhysicianDTO getPhysician() {return physician;}

    public void setPhysician(PhysicianDTO physician) {this.physician=physician;}

    public String getCurrentPassword() {return currentPassword;}

    public void setCurrentPassword(String currentPassword) {this.currentPassword = currentPassword;}

    public PhysicianUpdateDTO(PhysicianDTO physician, String currentPassword, String newPassword) {
        this.physician=physician;
        this.currentPassword=currentPassword;
        this.newPassword=newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {this.newPassword=newPassword;}
}

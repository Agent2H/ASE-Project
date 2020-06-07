package com.example.bloodpressureapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="BPParameters")
public class BP_Parameters {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "systolic")
    private int systolic;

    @Column(name = "diastolic")
    private int diastolic;

    @Column(name = "Heart rate")
    private int heart_rate;

    @JsonIgnore
    private List<Patient> patients;

    public BP_Parameters(){

    }

    public BP_Parameters(Long id, String userName, int systolic, int diastolic, int heart_rate){
        super();
        this.id=id;
        this.userName=userName;
        this.systolic=systolic;
        this.diastolic=diastolic;
        this.heart_rate=heart_rate;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName=userName;
    }

    public int getSystolic(){
        return systolic;
    }

    public void setSystolic(int systolic){
        this.systolic=systolic;
    }

    public int getDiastolic(){
        return diastolic;
    }

    public void setDiastolic(int diastolic){
        this.diastolic=diastolic;
    }

    public int getHeart_rate(){
        return heart_rate;
    }

    public void setHeart_rate(int heart_rate){
        this.heart_rate=heart_rate;
    }

    public List<Patient> getPatients(){
        return patients;
    }

    public void setPatients(List<Patient> patients){
        this.patients=patients;
    }

    @Override
    public String toString(){
        return "BP_Parameters[id="+ id +", username="+ userName +",systolic="+ systolic +",diastolic="+ diastolic +", heart_rate="+ heart_rate+"]";
    }


}

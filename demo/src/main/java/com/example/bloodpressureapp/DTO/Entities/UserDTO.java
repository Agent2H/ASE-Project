package com.example.bloodpressureapp.DTO.Entities;

import com.example.bloodpressureapp.entity.Role;

import java.util.List;

public class UserDTO {

    private Long id;

    private String username;

    private String fullname;

    private List<Role> roles;

    public UserDTO(){

    }

    public UserDTO(Long id, String username, String fullname, List<Role> roles){
        this.id=id;
        this.username=username;
        this.fullname=fullname;
        this.roles=roles;
    }
    public List<Role> getRoles(){return roles;}

    public void setRoles(List<Role> roles){this.roles=roles;}

    public Long getId() {return id;}

    public void setId(Long id) {this.id=id;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public String getFullname() {return fullname;}

    public void setFullname(String fullname){this.fullname=fullname;}


}
package com.example.bloodpressureapp.DTO.Entities;

import java.util.List;

public class AuthInfo {
    private boolean isLoggedIn;
    private List<String> roles;
    private String username;

    public boolean isLoggedIn() {return isLoggedIn;}

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn= loggedIn;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles=roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username=username;
    }

    public AuthInfo(boolean isLoggedIn, List<String> roles, String username) {
        this.isLoggedIn = isLoggedIn;
        this.roles = roles;
        this.username = username;
    }
}

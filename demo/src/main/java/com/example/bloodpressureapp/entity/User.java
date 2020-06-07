package com.example.bloodpressureapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "User model")
@Entity
@Table(name="user")
public class User {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_name",nullable = false, unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonIgnore
    private List<Role> roles;

    public User() {

    }

    public User(Long id, String username, String password, String fullname, String email, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email=email;
        this.roles = roles;

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullName) {
        this.fullname = fullName;
    }
    public List<Role> getRoles(){
        return roles;
    }
    public void setRoles(List<Role> roles){
        this.roles = roles;
    }
    @JsonIgnore
    public List<String> getAllRoles() {
        List<String> listRoles = new ArrayList<>();
        for (Role role: roles) {
            listRoles.add(role.getRole());
        }
        return listRoles;
    }

    @JsonIgnore
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
        }
        return authorities;
    }

    public void modifyRoles(List<Role> roles) {
        if (this.roles == null) {
            this.roles = new ArrayList<>();
        }
        this.roles.clear();
        this.roles.addAll(roles);
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + username + ", password=" + password + ", fullName=" + fullname+"]";
    }
}

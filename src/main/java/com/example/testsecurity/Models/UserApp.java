package com.example.testsecurity.Models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;


import java.util.List;

@Entity
public class UserApp {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role; // "USER" lub "ADMIN"
    @OneToMany(mappedBy = "userApp", cascade = CascadeType.ALL)
    @Nullable
    private List<Visit> visits;

    public UserApp(String firstName, String lastName, String email, String encode, String user) {
    }

    public UserApp(String id, String firstName, String lastName, String email, String password, @Nullable List<Visit> visits, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.visits = visits;
        this.role = role;
    }

    public UserApp() {

    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Nullable
    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(@Nullable List<Visit> visits) {
        this.visits = visits;
    }
}

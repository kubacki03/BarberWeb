package com.example.testsecurity.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Visit {
    @Id @GeneratedValue
    private Long id;

    private LocalDateTime dateStart;
    private LocalDateTime createdAt = LocalDateTime.now();
    private int timeOfVisit;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private UserApp userApp;

    public Visit(Long id, LocalDateTime dateStart, LocalDateTime createdAt, int timeOfVisit, UserApp userApp) {
        this.id = id;
        this.dateStart = dateStart;
        this.createdAt = createdAt;
        this.timeOfVisit = timeOfVisit;
        this.userApp = userApp;
    }

    public Visit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getTimeOfVisit() {
        return timeOfVisit;
    }

    public void setTimeOfVisit(int timeOfVisit) {
        this.timeOfVisit = timeOfVisit;
    }

    public UserApp getUser() {
        return userApp;
    }

    public void setUser(UserApp userApp) {
        this.userApp = userApp;
    }
}

package com.example.testsecurity.DTO;

import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Setter
public class VisitWithDateAndTimeDTO {
    LocalDateTime visitDate;
    int visitTime;

    public VisitWithDateAndTimeDTO(LocalDateTime visitDate, int visitTime) {
        this.visitDate = visitDate;
        this.visitTime = visitTime;
    }

    public VisitWithDateAndTimeDTO() {
    }

    public LocalDateTime getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDateTime visitDate) {
        this.visitDate = visitDate;
    }

    public int getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(int visitTime) {
        this.visitTime = visitTime;
    }
}

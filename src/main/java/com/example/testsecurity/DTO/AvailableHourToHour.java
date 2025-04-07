package com.example.testsecurity.DTO;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
public class AvailableHourToHour {

    public LocalDateTime hourStart;
    public LocalDateTime hourEnd;

    public AvailableHourToHour(LocalDateTime hourStart, LocalDateTime hourEnd) {
        this.hourEnd = hourEnd;
        this.hourStart = hourStart;
    }

    public AvailableHourToHour(){

    }
    public LocalDateTime getHourStart() {
        return hourStart;
    }

    public void setHourStart(LocalDateTime hourStart) {
        this.hourStart = hourStart;
    }

    public LocalDateTime getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(LocalDateTime hourEnd) {
        this.hourEnd = hourEnd;
    }
}

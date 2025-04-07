package com.example.testsecurity.Service;


import com.example.testsecurity.DTO.AvailableHourToHour;
import com.example.testsecurity.DTO.VisitWithDateAndTimeDTO;
import com.example.testsecurity.Enums.BarberServicesEnum;
import com.example.testsecurity.Models.Visit;
import com.example.testsecurity.VisitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
public class VisitService {

    @Autowired
    VisitRepository visitRepository;



    public List<Visit> getUserVisits(String email){
        return visitRepository.findAllByUserApp_Email(email);
    }






    public List<AvailableHourToHour> getAvailableHours(LocalDateTime date){

        List<VisitWithDateAndTimeDTO> list =visitRepository.findVisitsWithinDateRange(date, date.plusDays(1));
        List<AvailableHourToHour> list1 = new ArrayList<>();

        System.out.println("Jestem w getAvailableHours i "+list1);

        if(list.isEmpty()){
            list1.add(new AvailableHourToHour(date.withHour(8).withMinute(0).withNano(0).withSecond(0),date.withHour(16).withMinute(0).withSecond(0).withNano(0)));
            return list1;
        }
        list1.add(new AvailableHourToHour(LocalDateTime.of(date.toLocalDate(), LocalTime.of(7,59)), LocalDateTime.of(date.toLocalDate(), LocalTime.of(7,59)) ));


      list.sort(Comparator.comparing(VisitWithDateAndTimeDTO::getVisitDate));



        list.forEach(n -> list1.add(new AvailableHourToHour(n.getVisitDate().plusMinutes(n.getVisitTime()),n.getVisitDate())));
        list1.add(new AvailableHourToHour(LocalDateTime.of(date.toLocalDate(), LocalTime.of(16,0)), LocalDateTime.of(date.toLocalDate(), LocalTime.of(16,0)) ));


        List<AvailableHourToHour> gaps = findGaps(list1);



      return gaps;
    }

    public static List<AvailableHourToHour> findGaps(List<AvailableHourToHour> sortedList) {

        List<AvailableHourToHour> lista= new ArrayList<>();
        for(int i = 0; i < sortedList.size()-1; i++){
           if( sortedList.get(i).getHourEnd().isBefore(sortedList.get(i+1).getHourStart())){
            lista.add(new AvailableHourToHour(sortedList.get(i).getHourStart().plusMinutes(1),sortedList.get(i+1).getHourEnd().minusMinutes(1)));}
        }


       return lista;



    }

    public boolean MatchTheGaps(LocalDate date, AvailableHourToHour visit){
        var list = findGaps(getAvailableHours(date.atStartOfDay()));

        for(var gap : list){
            if(visit.hourStart.isAfter(gap.hourStart) || visit.hourEnd.isBefore(gap.hourEnd)){
                return false;
            }
        }

        return  true;
    }

    public int getTimeForService(String service) {
        System.out.println(service);
        try {
            BarberServicesEnum barberService = BarberServicesEnum.valueOf(service.toUpperCase());
            return switch (barberService) {
                case STYLING -> 60;
                case COLORING -> 80;
                case PREMIUM_HAIRCUT_FEMALE -> 120;
                case PREMIUM_HAIRCUT_MALE -> 50;
                case STANDARD_HAIRCUT_FEMALE -> 90;
                case STANDARD_HAIRCUT_MALE -> 30;
            };
        } catch (IllegalArgumentException e) {
            System.err.println("Nieznana usługa: " + service);
            return 0;
        }
    }


    public void saveVisit(Visit visit){
        visitRepository.save(visit);
    }
}

package com.example.testsecurity.Controllers;

import com.example.testsecurity.DTO.AvailableHourToHour;
import com.example.testsecurity.Enums.BarberServicesEnum;
import com.example.testsecurity.Models.Visit;
import com.example.testsecurity.Service.EmailSenderService;
import com.example.testsecurity.Service.UserAppService;
import com.example.testsecurity.Service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    VisitService visitService;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    UserAppService userAppService;

    @GetMapping("/services")
     List<BarberServicesEnum> getServices(){

        return Arrays.asList(BarberServicesEnum.values());

    }

    @GetMapping("/timeOfService")
    ResponseEntity<Integer> getTimeOfService(@RequestParam String service){

        Integer time = visitService.getTimeForService(service);
        if (time == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(time);
    }

    @PostMapping("/saveVisit")
    String saveVisit(@RequestParam("service") String service,
                     @RequestParam("selectedHour") LocalTime selectedHour,
                     @RequestParam("selectedDate") LocalDate selectedDate,
                     RedirectAttributes redirectAttributes) {

        var hourToHour = new AvailableHourToHour();
        hourToHour.setHourStart(LocalDateTime.of(selectedDate, selectedHour));
        hourToHour.setHourEnd(LocalDateTime.of(selectedDate, selectedHour.plusMinutes(visitService.getTimeForService(service))));

        if(selectedHour.isBefore(LocalTime.of(8,0)) || visitService.MatchTheGaps(selectedDate,hourToHour)  ||  selectedHour.isAfter(LocalTime.of(16,0))){
            return "redirect:/user/availableHours?date=" + selectedDate + "&error";


        }


        Visit visit = new Visit();
        visit.setTimeOfVisit(visitService.getTimeForService(service));
        visit.setDateStart(LocalDateTime.of(selectedDate, selectedHour));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            var user = userAppService.findByEmail(userDetails.getUsername());
            visit.setUser(user);
            emailSenderService.sendSimpleEmail(user.getEmail(),"Potwierdzenie wizyty w Barbershop","Zapisano nowa wizyte z usługą "+service+" o godzinie "+selectedHour+" dnia "+selectedDate);
        }

        visitService.saveVisit(visit);


        redirectAttributes.addAttribute("service", service);
        redirectAttributes.addAttribute("selectedHour", selectedHour);
        redirectAttributes.addAttribute("selectedDate", selectedDate);

        return "redirect:/VisitConfirmed";
    }



    @GetMapping("/VisitConfirmed")
    String VisitConfirmed(@RequestParam("service") String service,
                          @RequestParam("selectedHour") LocalTime selectedHour,
                          @RequestParam("selectedDate") LocalDate selectedDate,
                          Model model) {

        model.addAttribute("service", service);
        model.addAttribute("selectedHour", selectedHour);
        model.addAttribute("selectedDate", selectedDate);

        return "VisitConfirmed";
    }





}

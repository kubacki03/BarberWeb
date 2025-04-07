package com.example.testsecurity.Controllers;

import com.example.testsecurity.DTO.AvailableHourToHour;
import com.example.testsecurity.Enums.BarberServicesEnum;
import com.example.testsecurity.Service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class HomeController {

    @Autowired
    VisitService visitService;

    @GetMapping("/home")
   public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            model.addAttribute("user", userDetails.getUsername());
            model.addAttribute("visits",visitService.getUserVisits(userDetails.getUsername()));

        }



       return "home";
   }

    @GetMapping("/availableHours")
    public String availableHours(Model model, @RequestParam LocalDate date) {
        List<AvailableHourToHour> listAvailableHours = visitService.getAvailableHours(LocalDateTime.of(date, LocalTime.now()));
        model.addAttribute("availableHours", listAvailableHours);

        model.addAttribute("services", BarberServicesEnum.values());
        return "availableHours";
    }




}

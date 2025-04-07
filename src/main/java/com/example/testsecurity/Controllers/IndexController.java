package com.example.testsecurity.Controllers;

import com.example.testsecurity.Enums.BarberServicesEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller

public class IndexController {



    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("enum", Arrays.stream(BarberServicesEnum.values()).toArray());

        return "index";
    }
}

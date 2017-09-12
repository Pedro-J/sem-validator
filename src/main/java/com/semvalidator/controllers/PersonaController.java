package com.semvalidator.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonaController {

    @GetMapping("/persona")
    public String redirectPersonaPage(){
        return "redirect:/persona";
    }

}

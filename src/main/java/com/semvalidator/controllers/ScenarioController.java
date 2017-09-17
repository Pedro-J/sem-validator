package com.semvalidator.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScenarioController {

    @GetMapping("/scenario")
    public String redirectScenarioPage(){
        return "scenario";
    }
}

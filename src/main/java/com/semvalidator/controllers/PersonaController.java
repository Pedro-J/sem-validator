package com.semvalidator.controllers;


import com.semvalidator.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PersonaController {

    private PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/personas/list")
    public String redirectPersonaList(Model model){
        model.addAttribute("personas", personaService.findAll());
        return "personas/list";
    }

    @GetMapping("/personas/{id}/tips")
    public String showPersonaTips(@PathVariable("id") Integer id, Model model){
        model.addAttribute("tips", personaService.findAllTips(id));
        return "personas/tips";
    }

}

package com.semvalidator.controllers;


import com.semvalidator.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ScenarioController {

    private ScenarioService scenarioService;

    @Autowired
    public ScenarioController(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

    @GetMapping("/scenarios/list")
    public String redirectScenarioPage(Model model){
        model.addAttribute("scenarios", scenarioService.findAll());
        return "scenarios/list";
    }

    @GetMapping("/scenarios/{id}/tips")
    public String showScenarioTips(@PathVariable("id") Integer id, Model model){
        model.addAttribute("tips", scenarioService.findAllTips(id));
        return "scenarios/tips";
    }
}

package com.semvalidator.controllers;

import com.semvalidator.model.Evaluation;
import com.semvalidator.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EvaluationController {

    private EvaluationService evaluationService;

    @Autowired
    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @GetMapping("/evaluations/list")
    public String showAllEvaluations(Model model){
        model.addAttribute("evaluations", evaluationService.findAll());
        return "evaluation/list";
    }

    @GetMapping("/evaluations/add")
    public String showAddForm(Model model){
        model.addAttribute("evaluation", new Evaluation());

        return "evaluation/form";
    }

    @GetMapping("/evaluations/{id}/update")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model){
        model.addAttribute("evaluation", evaluationService.findById(id));
        return "evaluation/form";
    }

    @PostMapping("/evaluations")
    public String saveOrUpdate(@ModelAttribute("evaluation") @Validated Evaluation evaluation,
                               BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "evaluation/form";
        }else{
            redirectAttributes.addFlashAttribute("msgCSS","success");
            redirectAttributes.addFlashAttribute("msgTitle","general.msg.title.info");

            if( evaluation.isNew() ){
                redirectAttributes.addFlashAttribute("msgContent","general.msg.save");
            }else{
                redirectAttributes.addFlashAttribute("msgContent","general.msg.update");
            }
            evaluationService.save(evaluation);
            return "redirect:/evaluation/list";
        }
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String deleteEvaluation(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        evaluationService.delete(id);

        redirectAttributes.addFlashAttribute("msgCSS","success");
        redirectAttributes.addFlashAttribute("msgTitle","general.msg.title.info");
        redirectAttributes.addFlashAttribute("msgContent","general.msg.delete");

        return "redirect:/evaluation/list";
    }
}

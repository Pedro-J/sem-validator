package com.semvalidator.controllers;

import com.semvalidator.enums.ChecklistType;
import com.semvalidator.model.Checklist;
import com.semvalidator.service.CheckListService;
import com.semvalidator.validation.ChecklistFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @Author Created by Pedro-J on 6/18/17.
 */

@Controller
public class ChecklistController {

    private final Logger logger = LoggerFactory.getLogger(Checklist.class);

    @Autowired
    private CheckListService checkListService;

    @Autowired
    private ChecklistFormValidator checklistFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(checklistFormValidator);
    }

    @RequestMapping(value = "/checklists/list", method = RequestMethod.GET)
    public ModelAndView showAllChecklists(){
        ModelAndView modelAndView = new ModelAndView("checklists/list");
        modelAndView.addObject("checklists", checkListService.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/checklists/add", method = RequestMethod.GET)
    public String showAddForm(Model model){
        model.addAttribute("checklist", new Checklist());
        model.addAttribute("checklistTypes", ChecklistType.values());
        return "checklists/form";
    }

    @RequestMapping(value = "/checklists/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Integer id, Model model){
        model.addAttribute("checklist", checkListService.findById(id));
        model.addAttribute("checklistTypes", ChecklistType.values());
        return "checklists/form";
    }

    @RequestMapping(value = "/checklists", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("checklist") @Validated Checklist checklist,
                               BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "checklists/form";
        }else{
            redirectAttributes.addFlashAttribute("msgCSS","success");
            redirectAttributes.addFlashAttribute("msgTitle","general.msg.title.info");

            if( checklist.isNew() ){
                redirectAttributes.addFlashAttribute("msgContent","general.msg.save");
            }else{
                redirectAttributes.addFlashAttribute("msgContent","general.msg.update");
            }

            checkListService.save(checklist);

            return "redirect:/checklists/list";
        }
    }

    @RequestMapping(value = "/checklists/{id}", method = RequestMethod.GET)
    public String showChecklistDetails(@PathVariable("id") Integer id, Model model){
        model.addAttribute("checklist", checkListService.findById(id));
        return "checklists/detail";
    }

    public String deleteChecklist(){
        return null;
    }
}

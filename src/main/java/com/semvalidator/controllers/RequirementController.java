package com.semvalidator.controllers;

import com.semvalidator.editor.CriterionPropertyEditor;
import com.semvalidator.model.Criterion;
import com.semvalidator.model.Requirement;
import com.semvalidator.service.ChecklistService;
import com.semvalidator.service.CriterionService;
import com.semvalidator.service.RequirementService;
import com.semvalidator.util.OptionHTML;
import com.semvalidator.validation.RequirementFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
@Controller
public class RequirementController {

    private final Logger logger = LoggerFactory.getLogger(RequirementController.class);

    @Autowired
    private RequirementService requirementService;

    @Autowired
    private ChecklistService checkListService;

    @Autowired
    private CriterionService criterionService;

    @Autowired
    private RequirementFormValidator requirementFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Criterion.class, new CriterionPropertyEditor(criterionService));
        binder.setValidator(requirementFormValidator);
    }

    @RequestMapping(value = "/requirements/list")
    public ModelAndView showAllRequirements(){
        ModelAndView modelAndView = new ModelAndView("requirements/list");
        modelAndView.addObject("requirements",requirementService.findAll());
        return modelAndView;
    }

    @RequestMapping("/requirements/add")
    public String showAddForm(Model model){
        model.addAttribute("requirement", new Requirement());
        model.addAttribute("availableCriterions", criterionService.findAll());
        return "requirements/form";
    }

    @RequestMapping("/requirements/{id}/update")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model){
        model.addAttribute("requirement", requirementService.findById(id));
        model.addAttribute("availableCriterions", criterionService.findAll());
        return "requirements/form";
    }

    @RequestMapping(value = "/requirements", method = RequestMethod.POST)
    public String saveOrUpdateRequirement(
                @ModelAttribute("requirement") @Validated Requirement requirement,
                BindingResult result, Model model, RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            return showAddForm(model);
        }else{
            redirectAttributes.addFlashAttribute("msgCSS","success");
            redirectAttributes.addFlashAttribute("msgTitle","general.msg.title.info");

            if( requirement.getId() == null){
                redirectAttributes.addFlashAttribute("msgContent","general.msg.save");
            }else{
                redirectAttributes.addFlashAttribute("msgContent","general.msg.update");
            }
            requirementService.save(requirement);
            return "redirect:/requirements/list";
        }
    }

    @RequestMapping(value = "/requirements/{id}", method = RequestMethod.GET)
    public String showRequirementDetails(@PathVariable("id") Integer id, Model model){
        Requirement requirement = requirementService.findByIdWithCriterions(id);
        model.addAttribute("requirement", requirement);
        model.addAttribute("criterions", requirement.getCriterions());

        return "requirements/detail";
    }

    @RequestMapping(value = "/requirements/{id}/delete", method = RequestMethod.POST)
    public String deleteRequerement(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        requirementService.delete(id);

        redirectAttributes.addFlashAttribute("msgCSS","success");
        redirectAttributes.addFlashAttribute("msgTitle","general.msg.title.info");
        redirectAttributes.addFlashAttribute("msgContent","general.msg.delete");

        return "redirect:/requirements/list";
    }

    @RequestMapping(value = "/requirements/{id}/criterions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<OptionHTML> getCriterionsByRequirementId(@PathVariable("id") Integer id){
        Requirement requirement = requirementService.findByIdWithCriterions(id);
        if( CollectionUtils.isEmpty(requirement.getCriterions()) ){
            return null;
        }else{
            List<OptionHTML> options = new ArrayList<>();

            for( Criterion criterion : requirement.getCriterions() ) {
                OptionHTML option =
                        new OptionHTML(criterion.getId(), criterion.getDescription());
                options.add(option);
            }
            return options;
        }
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {

        logger.debug("handleEmptyData()");
        logger.error("Request: {}, error ", req.getRequestURL(), ex);

        ModelAndView model = new ModelAndView();
        model.setViewName("requirements/detail");
        model.addObject("msg", "question not found");

        return model;
    }
}

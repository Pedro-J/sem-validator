package com.semvalidator.controllers;

import com.semvalidator.model.Criterion;
import com.semvalidator.model.Question;
import com.semvalidator.service.CriterionService;
import com.semvalidator.service.QuestionService;
import com.semvalidator.service.RequirementService;
import com.semvalidator.validation.CriterionFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */

@Controller
@RequestMapping("/criterions")
public class CriterionController {

    private final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private CriterionService criterionService;

    @Autowired
    private RequirementService requirementService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CriterionFormValidator criterionFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(criterionFormValidator);
    }

    @RequestMapping("/list")
    public String showAllCriterions(Model model){
        model.addAttribute("criterions", criterionService.findAll());
        return "criterions/list";
    }

    @RequestMapping("/add")
    public String showAddForm(Model model){
        Criterion criterion = new Criterion();
        model.addAttribute("criterion", criterion);
        model.addAttribute("requirements", requirementService.findAll());
        return "criterions/form";
    }

    @RequestMapping("/{id}/update")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model){
        model.addAttribute("criterion", criterionService.findById(id));
        model.addAttribute("requirements", requirementService.findAll());
        return "criterions/form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("criterion") @Validated Criterion criterion,
                               BindingResult result, Model model, RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            return "criterions/form";
        }else{
            redirectAttributes.addFlashAttribute("msgCSS","success");
            redirectAttributes.addFlashAttribute("msgTitle","general.msg.title.info");

            if( criterion.getId() == null){
                redirectAttributes.addFlashAttribute("msgContent","general.msg.save");
            }else{
                redirectAttributes.addFlashAttribute("msgContent","general.msg.update");
            }
            criterionService.save(criterion);
            return "redirect:/criterions/list";
        }
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showUserDetails(@PathVariable("id") Integer id, Model model){
        Criterion criterion = criterionService.findById(id);
        List<Question> questions = questionService.findByCriterion(criterion);
        model.addAttribute("criterion", criterion);
        model.addAttribute("questions", questions);
        return "criterions/detail";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        criterionService.delete(id);

        redirectAttributes.addFlashAttribute("msgCSS","success");
        redirectAttributes.addFlashAttribute("msgTitle","general.msg.title.info");
        redirectAttributes.addFlashAttribute("msgContent","general.msg.delete");

        return "redirect:/criterions/list";
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {

        logger.debug("handleEmptyData()");
        logger.error("Request: {}, error ", req.getRequestURL(), ex);

        ModelAndView model = new ModelAndView();
        model.setViewName("criterions/detail");
        model.addObject("msg", "question not found");

        return model;
    }

}

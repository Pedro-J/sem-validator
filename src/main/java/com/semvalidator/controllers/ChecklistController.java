package com.semvalidator.controllers;

import com.semvalidator.enums.ChecklistType;
import com.semvalidator.model.Answer;
import com.semvalidator.model.Checklist;
import com.semvalidator.model.Question;
import com.semvalidator.service.*;
import com.semvalidator.validation.ChecklistFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Created by Pedro-J on 6/18/17.
 */

@Controller
public class ChecklistController {

    private final Logger logger = LoggerFactory.getLogger(Checklist.class);

    @Autowired
    private ChecklistService checkListService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CriterionService criterionService;

    @Autowired
    private RequirementService requirementService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private ChecklistFormValidator checklistFormValidator;

    @RequestMapping(value = "/checklists/list", method = RequestMethod.GET)
    public ModelAndView showAllChecklists(
            @RequestParam(value = "success", required = false) boolean success){

        ModelAndView modelAndView = new ModelAndView("checklists/list");
        modelAndView.addObject("checklists", checkListService.findAll());

        if( success ){
            modelAndView.addObject("msgCSS","success");
            modelAndView.addObject("msgTitle","general.msg.title.info");
            modelAndView.addObject("msgContent","general.msg.save");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/checklists/add", method = RequestMethod.GET)
    public String showAddForm(Model model){
        model.addAttribute("checklistTypes", ChecklistType.values());
        model.addAttribute("criterions", criterionService.findAll());
        model.addAttribute("questions", questionService.findAll());
        model.addAttribute("requirements", requirementService.findAll());
        model.addAttribute("models", modelService.findAll());
        model.addAttribute("newChecklist", true);
        return "checklists/form";
    }

    @RequestMapping(value = "/checklists/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Integer id, Model model){
        model.addAttribute("checklist", checkListService.findById(id));
        model.addAttribute("checklistTypes", ChecklistType.values());
        model.addAttribute("newChecklist", false);
        return "checklists/form";
    }

    @RequestMapping(value = "/checklists", method = RequestMethod.POST)
    public ResponseEntity<?> saveOrUpdate(@RequestBody Checklist checklist){
        try {
            checkListService.save(checklist);
            return new ResponseEntity<>("Checklist saved successfully", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Save operation fails", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/checklists/{id}", method = RequestMethod.GET)
    public String showChecklistDetails(@PathVariable("id") Integer id, Model model){
        Checklist checklist = checkListService.findById(id);
        List<Answer> answers = answerService.findByChecklist(checklist);
        model.addAttribute("checklist", checklist);
        model.addAttribute("answers", answers);
        return "checklists/detail";
    }

    @RequestMapping(value = "/checklists/{id}/delete", method = RequestMethod.POST)
    public String deleteChecklist(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        checkListService.delete(id);
        redirectAttributes.addFlashAttribute("msgCSS","success");
        redirectAttributes.addFlashAttribute("msgTitle","general.msg.title.info");
        redirectAttributes.addFlashAttribute("msgContent","general.msg.delete");
        return "redirect:/checklists/list";
    }

    @RequestMapping(value = "/checklists/{id}/answers", method = RequestMethod.GET)
    public String showAnswerValidationQuestionsForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        Checklist checklist = checkListService.findById(id);

        if( !CollectionUtils.isEmpty(checklist.getQuestions()) ) {
            model.addAttribute("checklist", checklist);
            return "answers/form";
        }else{
            redirectAttributes.addFlashAttribute("msgCSS","warning");
            redirectAttributes.addFlashAttribute("msgTitle","general.msg.title.warn");
            redirectAttributes.addFlashAttribute("msgContent","model.checklist.empty");
            return "redirect:/models/list";
        }
    }


    @RequestMapping(value = "/checklists/{id}/questions", method = RequestMethod.GET)
    public @ResponseBody Page<Question> getCriterionQuestions(
            @PathVariable("id") Integer id, @RequestParam("page") Integer page, @RequestParam("size") Integer size){
        Page<Question> questions = questionService.findByChecklist(id, new PageRequest(page, size));
        return questions;
    }


    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {

        logger.debug("handleEmptyData()");
        logger.error("Request: {}, error ", req.getRequestURL(), ex);

        ModelAndView model = new ModelAndView();
        model.setViewName("checklists/detail");
        model.addObject("msg", "checklist not found");

        return model;
    }
}

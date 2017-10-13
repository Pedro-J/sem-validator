package com.semvalidator.controllers;

import com.semvalidator.enums.ChecklistType;
import com.semvalidator.model.Answer;
import com.semvalidator.model.Checklist;
import com.semvalidator.model.Question;
import com.semvalidator.service.*;
import com.semvalidator.util.ChecklistDTO;
import com.semvalidator.validation.ChecklistFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    private ChecklistService checkListService;

    private QuestionService questionService;

    private CriterionService criterionService;

    private RequirementService requirementService;

    private ModelService modelService;

    private AnswerService answerService;

    private ChecklistFormValidator checklistFormValidator;

    @Autowired
    public ChecklistController(ChecklistService checkListService, QuestionService questionService, CriterionService criterionService,
                               RequirementService requirementService, ModelService modelService, AnswerService answerService,
                               ChecklistFormValidator checklistFormValidator) {

        this.checkListService = checkListService;
        this.questionService = questionService;
        this.criterionService = criterionService;
        this.requirementService = requirementService;
        this.modelService = modelService;
        this.answerService = answerService;
        this.checklistFormValidator = checklistFormValidator;
    }

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
        model.addAttribute("requirements", requirementService.findAll());
        model.addAttribute("newChecklist", true);
        return "checklists/form";
    }

    @RequestMapping(value = "/checklists/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Integer id, Model model){
        model.addAttribute("checklist", checkListService.findById(id));
        model.addAttribute("checklistTypes", ChecklistType.values());
        model.addAttribute("criterions", criterionService.findAll());
        model.addAttribute("requirements", requirementService.findAll());
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

    @RequestMapping(value = "/checklists/{id}/details", method = RequestMethod.GET)
    public String showChecklistDetails(@PathVariable("id") Integer id, Model model){
        Checklist checklist = checkListService.findById(id);
        List<Question> questions = questionService.findByChecklist(id);
        model.addAttribute("checklist", checklist);
        model.addAttribute("questions", questions);
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
    public List<Answer> getAnswers(@PathVariable("id") Integer id){
        return answerService.findByEvaluation(id);
    }

    @GetMapping("/checklists/{id}")
    @ResponseBody
    public ChecklistDTO getSelectedQuestionsIds(@PathVariable Integer id){
        return checkListService.findDTOById(id);
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

package com.semvalidator.controllers;

import com.semvalidator.editor.CriterionPropertyEditor;
import com.semvalidator.editor.RequirementPropertyEditor;
import com.semvalidator.model.Criterion;
import com.semvalidator.model.Question;
import com.semvalidator.model.Requirement;
import com.semvalidator.service.CriterionService;
import com.semvalidator.service.QuestionService;
import com.semvalidator.service.RequirementService;
import com.semvalidator.exception.RequestErrorException;
import com.semvalidator.util.SearchQuestionParamsDTO;
import com.semvalidator.validation.QuestionFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class QuestionController {

    private final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CriterionService criterionService;

    @Autowired
    private RequirementService requirementService;

    @Autowired
    private QuestionFormValidator questionFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Criterion.class, new CriterionPropertyEditor(criterionService));
        binder.registerCustomEditor(Requirement.class, new RequirementPropertyEditor(requirementService));
        //binder.setValidator(questionFormValidator);
    }


    @RequestMapping(value = "/questions/list", method = RequestMethod.GET)
    public ModelAndView showAllUsers(){
        List<Question> questions = questionService.findAll();
        ModelAndView modelAndView = new ModelAndView("questions/list");
        modelAndView.addObject("questions",questions);
        return modelAndView;
    }

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public @ResponseBody Page<Question> getAllUsers(
            @RequestParam("page") Integer page, @RequestParam("size") Integer size){
        Pageable pageable = new PageRequest(page, size);
        Page<Question> questions =  questionService.findAllPageable(pageable);
        return questions;
    }

    @RequestMapping(value = "/questions/search", method = RequestMethod.POST)
    public @ResponseBody Page<Question> search(@RequestBody SearchQuestionParamsDTO params) throws RequestErrorException {
        try{
            return questionService.search(params);
        }catch(Exception e){
            throw new RequestErrorException(e.getMessage());
        }
    }

    @RequestMapping(value = "/questions/add", method = RequestMethod.GET)
    public String showAddForm(Model model){
        Question question = new Question();
        model.addAttribute("question", question);
        model.addAttribute("criterions", criterionService.findAll());
        model.addAttribute("requirements", requirementService.findAll());
        return "questions/form";
    }

    @RequestMapping(value= "/questions/{id}/update", method = RequestMethod.GET)
    public ModelAndView showUpdateForm(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("questions/form");

        modelAndView.addObject("question", questionService.findById(id));
        modelAndView.addObject("criterions", criterionService.findAll());
        modelAndView.addObject("requirements", requirementService.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/questions", method = RequestMethod.POST)
    public String saveOrUpdateQuestion(@ModelAttribute("question") @Validated Question question,
                     BindingResult result, Model model, RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            return "questions/form";
        }else{

            redirectAttributes.addFlashAttribute("msgCSS","success");
            redirectAttributes.addFlashAttribute("msgTitle","general.msg.title.info");
            if( question.getId() == null){
                redirectAttributes.addFlashAttribute("msgContent","general.msg.save");
            }else{
                redirectAttributes.addFlashAttribute("msgContent","general.msg.update");
            }
            questionService.save(question);
            return "redirect:/questions/list";
        }
    }

    @RequestMapping(value = "/questions/{id}", method = RequestMethod.GET)
    public String showQuestionDetails(@PathVariable("id") Integer id, Model model){
        Question question = questionService.findById(id);
        model.addAttribute("question", question);

        return "questions/detail";
    }

    @RequestMapping(value = "/questions/{id}/delete", method = RequestMethod.POST)
    public String deleteQuestion(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        questionService.delete(id);

        redirectAttributes.addFlashAttribute("msgCSS","success");
        redirectAttributes.addFlashAttribute("msgTitle","general.msg.title.info");
        redirectAttributes.addFlashAttribute("msgContent","general.msg.delete");

        return "redirect:/questions/list";
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {

        logger.debug("handleEmptyData()");
        logger.error("Request: {}, error ", req.getRequestURL(), ex);

        ModelAndView model = new ModelAndView();
        model.setViewName("questions/detail");
        model.addObject("msg", "question not found");

        return model;
    }

    @ExceptionHandler(RequestErrorException.class)
    public ModelAndView handleRequestError(HttpServletRequest req, Exception ex) {

        ModelAndView model = new ModelAndView();
        model.setViewName("questions/detail");
        model.addObject("msg", "question not found");

        return model;
    }

}

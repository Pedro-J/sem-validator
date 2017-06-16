package com.semvalidator.controllers;

import com.semvalidator.model.Question;
import com.semvalidator.service.CriterionService;
import com.semvalidator.service.QuestionService;
import com.semvalidator.validation.QuestionFormValidator;
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
public class QuestionController {

    private final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionFormValidator questionFormValidator;

    @Autowired
    private CriterionService criterionService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(questionFormValidator);
    }

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/questions/list", method = RequestMethod.GET)
    public ModelAndView showAllUsers(){
        List<Question> questions = questionService.findAll();
        ModelAndView modelAndView = new ModelAndView("questions/list");
        modelAndView.addObject("questions",questions);
        return modelAndView;
    }

    @RequestMapping(value = "/questions/add", method = RequestMethod.GET)
    public String showAddForm(Model model){
        model.addAttribute("question", new Question());
        model.addAttribute("criterions",criterionService.findAll());
        return "questions/form";
    }

    @RequestMapping(value= "/questions/{id}/update", method = RequestMethod.GET)
    public ModelAndView showUpdateForm(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("questions/form");

        modelAndView.addObject("question", questionService.findById(id));
        modelAndView.addObject("criterions",criterionService.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/questions", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("question") @Validated Question question,
                     BindingResult result, Model model, RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            return "questions/form";
        }else{
            questionService.save(question);

            redirectAttributes.addFlashAttribute("msgCSS","success");
            redirectAttributes.addFlashAttribute("msgTitle","general.msg.title.info");
            if( question.getId() == null){
                redirectAttributes.addFlashAttribute("msgContent","general.msg.save");
            }else{
                redirectAttributes.addFlashAttribute("msgContent","general.msg.update");
            }

            return "redirect:questions/"+ question.getId();
        }
    }

    @RequestMapping(value = "/questions/{id}", method = RequestMethod.GET)
    public String showUserDetails(@PathVariable("id") Integer id, Model model){
        Question question = questionService.findById(id);
        model.addAttribute("question", question);

        return "questions/detail";
    }

    @RequestMapping(value = "/questions/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
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

}

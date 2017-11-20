package com.semvalidator.controllers;

import com.semvalidator.enums.ChecklistType;
import com.semvalidator.exception.ResourceNotFoundException;
import com.semvalidator.model.Checklist;
import com.semvalidator.model.ModelSE;
import com.semvalidator.service.AnswerService;
import com.semvalidator.service.ChecklistService;
import com.semvalidator.service.ModelService;
import com.semvalidator.validation.ModelFormValidator;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Created by Pedro-J on 6/18/17.
 */

@Controller
public class ModelController {

    private final Logger logger = LoggerFactory.getLogger(ModelController.class);

    private ModelService modelService;

    private ChecklistService checkListService;

    private AnswerService answerService;

    private ModelFormValidator modelFormValidator;

    @Autowired
    public ModelController(ModelService modelService, ChecklistService checkListService, AnswerService answerService, ModelFormValidator modelFormValidator) {
        this.modelService = modelService;
        this.checkListService = checkListService;
        this.answerService = answerService;
        this.modelFormValidator = modelFormValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(modelFormValidator);
    }

    @RequestMapping(value = "/models/list", method = RequestMethod.GET)
    public String showAllModels(Model model){
        model.addAttribute("models", modelService.findAll());
        return "models/list";
    }

    @RequestMapping(value = "/models/add", method = RequestMethod.GET)
    public String showAddForm(Model model){
        loadForm(model);
        model.addAttribute("model", new ModelSE());
        return "models/form";
    }

    @RequestMapping(value = "/models/{id}/update", method = RequestMethod.GET)
    public String showAddForm(@PathVariable("id") Integer id, Model model){
        ModelSE modelSE = modelService.findById(id);
        if( modelSE == null ) throw new ResourceNotFoundException();
        loadForm(model);
        model.addAttribute("model", modelSE);
        return "models/form";
    }

    @RequestMapping(value = "/models", method= RequestMethod.POST)
    public String saveOrUpdateModel(MultipartFile modelFile, @ModelAttribute("model") @Validated ModelSE model, BindingResult result,
                                    RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "models/form";
        }else {

            redirectAttributes.addFlashAttribute("msgCSS","success");
            redirectAttributes.addFlashAttribute("msgTitle","general.msg.title.info");
            if( model.getId() == null){
                redirectAttributes.addFlashAttribute("msgContent","general.msg.save");
            }else{
                redirectAttributes.addFlashAttribute("msgContent","general.msg.update");
            }
            modelService.save(model, modelFile);
            return "redirect:/models/list";

        }
    }

    @RequestMapping(value = "/models/{id}", method = RequestMethod.GET)
    public String showQuestionDetails(@PathVariable("id") Integer id, Model model){
        ModelSE modelSE = modelService.findById(id);
        if( modelSE == null ) throw new ResourceNotFoundException();
        model.addAttribute("model", modelSE);
        return "models/detail";
    }

    @RequestMapping(value = "/models/{id}/delete", method = RequestMethod.POST)
    public String deleteQuestion(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){

        if( modelService.isDeletable(id) ) {
            modelService.delete(id);

            redirectAttributes.addFlashAttribute("msgCSS", "success");
            redirectAttributes.addFlashAttribute("msgTitle", "general.msg.title.info");
            redirectAttributes.addFlashAttribute("msgContent", "general.msg.delete");
        }else{
            redirectAttributes.addFlashAttribute("msgCSS", "warning");
            redirectAttributes.addFlashAttribute("msgTitle", "general.msg.title.warn");
            redirectAttributes.addFlashAttribute("msgContent","general.msg.delete.model.error");

        }

        return "redirect:/models/list";
    }

    private void loadForm(Model model){
        List<Checklist> clValidation = checkListService.findByChecklistType(ChecklistType.VALIDATION);
        List<Checklist> clVerification = checkListService.findByChecklistType(ChecklistType.VERIFICATION);
        model.addAttribute("checklistsVerification", clVerification);
        model.addAttribute("checklistsValidation", clValidation);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {

        logger.debug("handleEmptyData()");
        logger.error("Request: {}, error ", req.getRequestURL(), ex);

        ModelAndView model = new ModelAndView();
        model.setViewName("models/detail");
        model.addObject("msg", "model not found");

        return model;
    }

}

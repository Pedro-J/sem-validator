package com.semvalidator.controllers;

import com.semvalidator.editor.ChecklistPropertyEditor;
import com.semvalidator.enums.ChecklistType;
import com.semvalidator.model.Checklist;
import com.semvalidator.model.ModelSE;
import com.semvalidator.service.ChecklistService;
import com.semvalidator.service.ModelService;
import com.semvalidator.validation.ModelFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
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

    @Autowired
    private ModelService modelService;

    @Autowired
    private ChecklistService checkListService;

    @Autowired
    private ModelFormValidator modelFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Checklist.class, new ChecklistPropertyEditor(checkListService));
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
        loadForm(model);
        model.addAttribute("model", modelService.findById(id));
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
        model.addAttribute("model", modelSE);

        return "models/detail";
    }

    @RequestMapping(value = "/models/{id}/delete", method = RequestMethod.POST)
    public String deleteQuestion(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        modelService.delete(id);

        redirectAttributes.addFlashAttribute("msgCSS","success");
        redirectAttributes.addFlashAttribute("msgTitle","general.msg.title.info");
        redirectAttributes.addFlashAttribute("msgContent","general.msg.delete");

        return "redirect:/models/list";
    }

    @RequestMapping(value = "/models/{id}/validation", method = RequestMethod.GET)
    public String showAnswerValidationQuestionsForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        ModelSE modelSE = modelService.findById(id);
        Checklist clValidation = checkListService.findByIdWithRequirements(
                modelSE.getChecklistValidation().getId());

        return validateAndRedirectAnswersForm(clValidation, modelSE, model, redirectAttributes);

    }

    @RequestMapping(value = "/models/{id}/verification", method = RequestMethod.GET)
    public String showAnswerVerificationQuestionsForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        ModelSE modelSE = modelService.findById(id);
        Checklist clVerification = checkListService.findByIdWithRequirements(
                modelSE.getChecklistVerification().getId());


        return validateAndRedirectAnswersForm(clVerification, modelSE, model, redirectAttributes);
    }

    private String validateAndRedirectAnswersForm(Checklist checklist, ModelSE modelSE,
                                                  Model model, RedirectAttributes redirectAttributes){
        if( !CollectionUtils.isEmpty(checklist.getRequirements()) ) {
            model.addAttribute("model", modelSE);
            model.addAttribute("checklistTitle", checklist.getTitle());
            model.addAttribute("checklistId", checklist.getId());
            model.addAttribute("requirements", checklist.getRequirements());
            return "answers/form";
        }else{
            redirectAttributes.addFlashAttribute("msgCSS","warning");
            redirectAttributes.addFlashAttribute("msgTitle","general.msg.title.warn");
            redirectAttributes.addFlashAttribute("msgContent","checklist.requirements.empty");
            return "redirect:/models/list";
        }
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

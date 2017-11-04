package com.semvalidator.controllers;

import com.semvalidator.editor.ChecklistPropertyEditor;
import com.semvalidator.editor.ModelPropertyEditor;
import com.semvalidator.exception.ResourceNotFoundException;
import com.semvalidator.model.Checklist;
import com.semvalidator.model.Evaluation;
import com.semvalidator.model.ModelSE;
import com.semvalidator.model.Question;
import com.semvalidator.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EvaluationController {

    private EvaluationService evaluationService;

    private AnswerService answerService;

    private ModelService modelService;

    private ChecklistService checklistService;

    private QuestionService questionService;

    @Autowired
    public EvaluationController(EvaluationService evaluationService, ModelService modelService, ChecklistService checklistService,
                                QuestionService questionService, AnswerService answerService) {
        this.evaluationService = evaluationService;
        this.modelService = modelService;
        this.checklistService = checklistService;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Checklist.class, new ChecklistPropertyEditor(checklistService));
        binder.registerCustomEditor(ModelSE.class, new ModelPropertyEditor(modelService));
    }

    @GetMapping("/evaluations/list")
    public String showAllEvaluations(Model model){
        model.addAttribute("evaluations", evaluationService.findAll());
        return "evaluations/list";
    }

    @GetMapping("/evaluations/add")
    public String showAddForm(Model model){
        model.addAttribute("evaluation", new Evaluation());
        model.addAttribute("models", modelService.findAll());
        model.addAttribute("checklists", checklistService.findAll());

        return "evaluations/form";
    }

    @GetMapping("/evaluations/{id}/update")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model){
        Evaluation evaluation = evaluationService.findById(id);
        if( evaluation == null ) throw new ResourceNotFoundException();

        model.addAttribute("evaluation", evaluation);
        return "evaluations/form";
    }

    @PostMapping("/evaluations")
    public String saveOrUpdate(@ModelAttribute("evaluation") @Validated Evaluation evaluation,
                               BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "evaluations/form";
        }else{
            redirectAttributes.addFlashAttribute("msgCSS","success");
            redirectAttributes.addFlashAttribute("msgTitle","general.msg.title.info");

            if( evaluation.isNew() ){
                redirectAttributes.addFlashAttribute("msgContent","general.msg.save");
            }else{
                redirectAttributes.addFlashAttribute("msgContent","general.msg.update");
            }
            evaluationService.save(evaluation);
            return "redirect:/evaluations/list";
        }
    }

    @PostMapping("/evaluations/{id}/delete")
    public String deleteEvaluation(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        evaluationService.delete(id);

        redirectAttributes.addFlashAttribute("msgCSS","success");
        redirectAttributes.addFlashAttribute("msgTitle","general.msg.title.info");
        redirectAttributes.addFlashAttribute("msgContent","general.msg.delete");

        return "redirect:/evaluations/list";
    }

    @GetMapping("/evaluations/{id}")
    public String showDetails(@PathVariable("id") Integer id, Model model){
        Evaluation evaluation = evaluationService.findById(id);
        if( evaluation == null ) throw new ResourceNotFoundException();

        model.addAttribute("evaluation", evaluation);
        model.addAttribute("generalSatisfaction", evaluationService.calculateGeneralSatisfaction(id));
        model.addAttribute("answers", answerService.findByEvaluation(id));

        model.addAttribute("requirements", evaluationService.calculateRequirementsSatisfaction(id));
        model.addAttribute("criterions", evaluationService.calculateCriterionsSatisfaction(id));
        return "evaluations/detail";
    }

    @GetMapping("/evaluations/{id}/answers/form")
    public String showAnswerValidationQuestionsForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        Evaluation evaluation = evaluationService.findById(id);

        if( evaluation == null ) throw new ResourceNotFoundException();

        List<Question> questions = questionService.findByChecklist(evaluation.getChecklist().getId());
        if( !CollectionUtils.isEmpty(questions) ) {
            model.addAttribute("evaluation", evaluation);
            return "answers/form";
        }else{
            redirectAttributes.addFlashAttribute("msgCSS","warning");
            redirectAttributes.addFlashAttribute("msgTitle","general.msg.title.warn");
            redirectAttributes.addFlashAttribute("msgContent","checklist.questions.empty");
            return "redirect:/evaluations/list";
        }
    }

    @GetMapping("/evaluations/{id}/questions")
    public @ResponseBody List<Question> getQuestions(@PathVariable("id") Integer id){
        Evaluation evaluation = evaluationService.findById(id);

        if( evaluation == null ) throw new ResourceNotFoundException();

        return questionService.findByChecklist(evaluation.getChecklist().getId());
    }

}

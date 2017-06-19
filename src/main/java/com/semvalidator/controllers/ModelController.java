package com.semvalidator.controllers;

import com.semvalidator.infra.FileSaver;
import com.semvalidator.model.ModelSE;
import com.semvalidator.service.CheckListService;
import com.semvalidator.service.ModelService;
import com.semvalidator.validation.ModelFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @Author Created by Pedro-J on 6/18/17.
 */

@Controller
public class ModelController {

    private final Logger logger = LoggerFactory.getLogger(ModelController.class);

    @Autowired
    private ModelService modelService;

    @Autowired
    private CheckListService checkListService;

    @Autowired
    private ModelFormValidator modelFormValidator;

    @Autowired
    private FileSaver fileSaver;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(modelFormValidator);
    }

    @RequestMapping(value = "/models", method= RequestMethod.POST)
    public ModelAndView saveOrUpdateModel(MultipartFile modelFile, @ModelAttribute("model") @Valid ModelSE model, BindingResult result,
                                     RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            //return form(produto);
        }

        if (modelFile != null && !modelFile.getOriginalFilename().isEmpty()) {
            String path = fileSaver.write("arquivos-sumario", modelFile);
            model.setModelFileUrl(path);
        }

        //produtoDao.gravar(produto);

        redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");

        return new ModelAndView("redirect:produtos");
    }
}

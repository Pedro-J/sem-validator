package com.semvalidator.controllers;

import com.semvalidator.service.AnswerService;
import com.semvalidator.util.AnswerJsonDTO;
import com.semvalidator.util.OptionHTML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author Created by Pedro-J on 6/22/17.
 */

@Controller
public class AnswerController {

    private AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService, ApplicationContext context) {
        this.answerService = answerService;
    }

    @RequestMapping(value = "/answers/types", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<OptionHTML> getAnswers(){
        return answerService.getTypes();
    }

    @RequestMapping(value = "/answers", method = RequestMethod.POST)
    public ResponseEntity<String> saveOrUpdate(@RequestBody List<AnswerJsonDTO> answersJson){
        try {
            answerService.saveAllDatas(answersJson);
            return new ResponseEntity<>("Answers saved successfully", HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Save operation fails", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

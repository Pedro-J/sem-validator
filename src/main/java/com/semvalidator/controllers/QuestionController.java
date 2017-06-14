package com.semvalidator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */

@Controller
@RequestMapping("/question")
public class QuestionController {

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String cadastro(){
        return "redirect:admin/";
    }
}

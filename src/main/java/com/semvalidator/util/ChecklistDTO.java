package com.semvalidator.util;

import com.semvalidator.enums.ChecklistType;
import com.semvalidator.model.Checklist;
import com.semvalidator.model.Question;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ChecklistDTO {

    private String title;

    private ChecklistType checklistType;

    private List<Integer> questions;

    public ChecklistDTO(Checklist checklist) {
        this.title = checklist.getTitle();
        this.checklistType = checklist.getChecklistType();

        if( !CollectionUtils.isEmpty(checklist.getQuestions()) ){
            questions = new ArrayList<>();
            for(Question question : checklist.getQuestions()){
                questions.add(question.getId());
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ChecklistType getChecklistType() {
        return checklistType;
    }

    public void setChecklistType(ChecklistType checklistType) {
        this.checklistType = checklistType;
    }

    public List<Integer> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Integer> questions) {
        this.questions = questions;
    }
}

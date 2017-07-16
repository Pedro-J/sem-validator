package com.semvalidator.util;

/**
 * @Author Created by Pedro-J on 7/15/17.
 */
public class SearchQuestionParamsDTO {

    private Integer page;
    private Integer size;
    private Integer criterion;
    private Integer requirement;
    private String questionDescription;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCriterion() {
        if( criterion == 0 )
            return null;
        return criterion;
    }

    public void setCriterion(Integer criterion) {
        this.criterion = criterion;
    }

    public Integer getRequirement() {
        if( requirement == 0 )
            return null;
        return requirement;
    }

    public void setRequirement(Integer requirement) {
        this.requirement = requirement;
    }

    public String getQuestionDescription() {
        if( questionDescription == "" )
            return null;
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }
}

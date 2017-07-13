package com.semvalidator.model;

import com.semvalidator.enums.ChecklistType;

import javax.persistence.*;
import java.util.List;

/**
 * @Author Created by comp-dev on 6/12/17.
 */

@Entity
@Table(name = "tb_checklist")
public class Checklist extends GenericEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_validator")
    private User userValidator;

    @Enumerated(EnumType.ORDINAL)
    private ChecklistType checklistType;

    @ManyToOne
    @JoinColumn(name = "id_model")
    private ModelSE model;

    @ManyToMany
    @JoinTable(name = "tb_checklist_question", joinColumns = {
            @JoinColumn(name = "id_checklist", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "id_question",
                    nullable = false) })
    private List<Question> questions;

    public Checklist(Integer id) {
        this.id = id;
    }

    public Checklist() {
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return (id == null ? true : false);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUserValidator() {
        return userValidator;
    }

    public void setUserValidator(User userValidator) {
        this.userValidator = userValidator;
    }

    public ChecklistType getChecklistType() {
        return checklistType;
    }

    public void setChecklistType(ChecklistType checklistType) {
        this.checklistType = checklistType;
    }

    public ModelSE getModel() {
        return model;
    }

    public void setModel(ModelSE model) {
        this.model = model;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}

package com.semvalidator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

/**
 * @Author Created by comp-dev on 6/12/17.
 */

@Entity
@Table(name = "tb_criterion")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NamedQuery(name = "Criterion.findAll", query="select c from Criterion c order by c.description asc")
public class Criterion extends GenericEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "criterion", cascade = CascadeType.REMOVE)
    private List<Question> questions;

    @JsonIgnore
    private transient float satisfactionValue;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return (id == null ? true : false);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public float getSatisfactionValue() {
        return satisfactionValue;
    }

    public void setSatisfactionValue(float satisfactionValue) {
        this.satisfactionValue = satisfactionValue;
    }
}

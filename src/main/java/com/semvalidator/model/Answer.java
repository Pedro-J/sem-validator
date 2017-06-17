package com.semvalidator.model;

import com.semvalidator.enums.AnswerValue;

import javax.persistence.*;

/**
 * @Author Created by comp-dev on 6/12/17.
 */
@Entity
@Table(name = "tb_asnwer")
public class Answer extends GenericEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_model")
    private ModelSE model;

    @ManyToOne
    @JoinColumn(name = "id_question")
    private Question question;

    @Enumerated(EnumType.ORDINAL)
    private AnswerValue value;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return (id == null ? true : false);
    }

    public ModelSE getModel() {
        return model;
    }

    public void setModel(ModelSE model) {
        this.model = model;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public AnswerValue getValue() {
        return value;
    }

    public void setValue(AnswerValue value) {
        this.value = value;
    }
}

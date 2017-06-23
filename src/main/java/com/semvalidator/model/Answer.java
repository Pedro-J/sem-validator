package com.semvalidator.model;

import com.semvalidator.enums.AnswerValue;
import com.semvalidator.util.AnswerJsonDTO;

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

    @ManyToOne
    @JoinColumn(name = "id_requirement")
    private Requirement requirement;

    @Enumerated(EnumType.ORDINAL)
    private AnswerValue value;

    public Answer(AnswerJsonDTO answerJsonDTO){
        convertFromDTO(answerJsonDTO);
    }

    public Answer(){

    }

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

    public Requirement getRequirement() {
        return requirement;
    }

    public void setRequirement(Requirement requirement) {
        this.requirement = requirement;
    }

    public void convertFromDTO(AnswerJsonDTO answer){
        this.model = new ModelSE(answer.getModel());
        this.requirement = new Requirement(answer.getRequirement());
        this.question = new Question(answer.getQuestion());
        this.value = AnswerValue.findByCode(answer.getValue());
    }
}

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
    @JoinColumn(name = "id_evaluation")
    private Evaluation evaluation;

    @ManyToOne
    @JoinColumn(name = "id_question")
    private Question question;

    @Enumerated(EnumType.ORDINAL)
    private AnswerValue value;

    public Answer(AnswerJsonDTO answerJsonDTO){
        convertFromDTO(answerJsonDTO);
    }

    public Answer(){}

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

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public void convertFromDTO(AnswerJsonDTO answer){
        this.evaluation = new Evaluation(answer.getEvaluation());
        this.question = new Question(answer.getQuestion());
        this.value = AnswerValue.findByCode(answer.getValue());
    }


}

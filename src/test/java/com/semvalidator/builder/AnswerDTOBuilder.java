package com.semvalidator.builder;

import com.semvalidator.util.AnswerJsonDTO;

public class AnswerDTOBuilder {

    private AnswerJsonDTO answerDTO = new AnswerJsonDTO();

    public AnswerDTOBuilder forQuestion(Integer questionId){
        answerDTO.setQuestion(questionId);
        return this;
    }

    public AnswerDTOBuilder inEvaluation(Integer evaluationId){
        answerDTO.setEvaluation(evaluationId);
        return this;
    }

    public AnswerDTOBuilder answeredWith(Integer evaluationId){
        answerDTO.setValue(evaluationId);
        return this;
    }

    public AnswerJsonDTO build(){
        AnswerJsonDTO builded = this.answerDTO;
        this.answerDTO = new AnswerJsonDTO();
        return builded;
    }
}

package com.semvalidator.service;

import com.semvalidator.model.Answer;
import com.semvalidator.util.AnswerJsonDTO;

import java.util.List;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
public interface AnswerService extends GenericService<Answer>{
    void saveAllDatas(List<AnswerJsonDTO> answersJson);

    List<Answer> findByEvaluation(Integer id);
}

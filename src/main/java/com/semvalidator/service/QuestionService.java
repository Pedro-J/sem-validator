package com.semvalidator.service;

import com.semvalidator.model.Criterion;
import com.semvalidator.model.Question;

import java.util.List;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
public interface QuestionService extends GenericService<Question>{
    List<Question> findByCriterion(Criterion criterion);
}

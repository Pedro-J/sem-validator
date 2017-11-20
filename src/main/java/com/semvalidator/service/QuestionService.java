package com.semvalidator.service;

import com.semvalidator.model.Criterion;
import com.semvalidator.model.Question;
import com.semvalidator.model.Requirement;
import com.semvalidator.util.SearchQuestionParamsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
public interface QuestionService extends GenericService<Question>{
    List<Question> findAllAvailable();
    List<Question> findAllOrderByRequirementAndCriterion();
    List<Question> findByCriterion(Criterion criterion);
    List<Question> findByRequirement(Requirement requirement);
    List<Question> findByRequirementAndCriterion(Requirement requirement, Criterion criterion);
    Page<Question> search(SearchQuestionParamsDTO search);
    Page<Question> findByChecklist(Integer id, Pageable pageable);
    List<Question> findByChecklist(Integer id);
    boolean isDeletable(Integer id);
}

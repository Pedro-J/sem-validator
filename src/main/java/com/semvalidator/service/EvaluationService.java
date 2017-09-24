package com.semvalidator.service;

import com.semvalidator.model.Criterion;
import com.semvalidator.model.Evaluation;
import com.semvalidator.model.Requirement;
import java.util.List;

public interface EvaluationService extends GenericService<Evaluation> {
    List<Requirement> calculateRequirementsSatisfaction(Integer evaluationId);
    List<Criterion> calculateCriterionsSatisfaction(Integer evaluationId);
    Double calculateGeneralSatisfaction(Integer evaluationId);
}

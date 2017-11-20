package com.semvalidator.repository;

import com.semvalidator.model.Answer;
import com.semvalidator.model.Evaluation;
import com.semvalidator.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer>{
    Answer findByEvaluationAndQuestion(Evaluation evaluation, Question question);

/*    @Query("select a from Answer a where a.checklist = :checklist order by a.requirement.description,a.question.criterion.description")
    List<Answer> findByChecklistOrderByRequirementAndCriterion(@Param("checklist") Checklist checklist);*/

    @Query("select a from Answer a where a.evaluation.id = :id")
    List<Answer> findByEvaluation(@Param("id") Integer evaluationId);

    @Query("select a from Answer a where a.evaluation.id = :evaluationId and a.question.requirement.id = :requirementId")
    List<Answer> findByEvaluationAndRequirement(@Param("evaluationId") Integer evaluationId, @Param("requirementId") Integer requirementId);

    @Query("select a from Answer a where a.evaluation.id = :evaluationId and a.question.criterion.id = :criterionId")
    List<Answer> findByEvaluationAndCriterion(@Param("evaluationId") Integer evaluationId, @Param("criterionId") Integer criterionId);

    @Query("select a from Answer a where a.question.id = :id")
    List<Answer> findByQuestion(@Param("id") Integer questionId);

    List<Answer> findByEvaluation(Evaluation evaluation);
}

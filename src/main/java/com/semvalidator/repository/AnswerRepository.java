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
    List<Answer> findByEvaluation(@Param("id") Integer id);
}

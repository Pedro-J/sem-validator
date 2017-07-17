package com.semvalidator.repository;

import com.semvalidator.model.Answer;
import com.semvalidator.model.Checklist;
import com.semvalidator.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer>{
    Answer findByChecklistAndQuestion(Checklist checklist, Question question);

/*    @Query("select a from Answer a where a.checklist = :checklist order by a.requirement.description,a.question.criterion.description")
    List<Answer> findByChecklistOrderByRequirementAndCriterion(@Param("checklist") Checklist checklist);*/

    List<Answer> findByChecklist(Checklist checklist);
}

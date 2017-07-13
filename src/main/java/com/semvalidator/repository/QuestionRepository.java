package com.semvalidator.repository;

import com.semvalidator.model.Criterion;
import com.semvalidator.model.Question;
import com.semvalidator.model.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Query("select q from Question q where q.criterion is null")
    List<Question> findAllAvailable();

    List<Question> findByCriterion(Criterion criterion);

    List<Question> findByRequirement(Requirement requirement);

    List<Question> findByRequirementAndCriterion(Requirement requirement, Criterion criterion);
}

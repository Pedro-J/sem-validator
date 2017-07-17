package com.semvalidator.repository;

import com.semvalidator.model.Criterion;
import com.semvalidator.model.Question;
import com.semvalidator.model.Requirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("select q from Question q inner join q.checklists c where c.id = :id")
    Page<Question> findByChecklist(@Param("id") Integer id, Pageable pageable);

    Page<Question> findByRequirementAndCriterionAndDescriptionLike(
            Requirement requirement, Criterion criterion, String description, Pageable pageable);
    Page<Question> findByRequirementAndCriterion(Requirement requirement, Criterion criterion, Pageable pageable);
    Page<Question> findByRequirementAndDescriptionLike(Requirement requirement, String description, Pageable pageable);
    Page<Question> findByCriterionAndDescriptionLike(Criterion criterion, String description, Pageable pageable);
    Page<Question> findByRequirement(Requirement requirement, Pageable pageable);
    Page<Question> findByCriterion(Criterion criterion, Pageable pageable);
    Page<Question> findByDescriptionLike(String description, Pageable pageable);

}

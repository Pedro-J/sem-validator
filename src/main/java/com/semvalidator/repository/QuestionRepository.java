package com.semvalidator.repository;

import com.semvalidator.model.Criterion;
import com.semvalidator.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByCriterion(Criterion criterion);
}

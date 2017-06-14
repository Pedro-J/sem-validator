package com.semvalidator.repository;

import com.semvalidator.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer>{
}

package com.semvalidator.repository;

import com.semvalidator.model.Criterion;
import com.semvalidator.model.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
@Repository
public interface CriterionRepository extends JpaRepository<Criterion, Integer> {
    List<Criterion> findByRequirement(Requirement requirement);
}

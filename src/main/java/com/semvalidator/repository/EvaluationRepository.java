package com.semvalidator.repository;

import com.semvalidator.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {

    @Query("select e from Evaluation e where e.model.id = :id")
    List<Evaluation> findByModel(@Param("id") Integer modelId);

    @Query("select e from Evaluation e where e.checklist.id = :id")
    List<Evaluation> findByChecklist(@Param("id") Integer checklistId);
}

package com.semvalidator.repository;

import com.semvalidator.model.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Integer> {
/*    @Query("select r from Requirement r left join r.criterions where r.id = :id")
    Requirement findByIdWithCriterions(@Param("id") Integer id);*/

    @Query
    @Override
    List<Requirement> findAll();
}

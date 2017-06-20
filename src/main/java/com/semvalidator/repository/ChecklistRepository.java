package com.semvalidator.repository;

import com.semvalidator.model.Checklist;
import com.semvalidator.model.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */

@Repository
public interface ChecklistRepository extends JpaRepository<Checklist, Integer> {
    @Query("select c from Checklist c left join c.requirements where c.id = :id")
    Checklist findByIdWithCriterions(@Param("id") Integer id);
}

package com.semvalidator.repository;

import com.semvalidator.enums.ChecklistType;
import com.semvalidator.model.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */

@Repository
public interface ChecklistRepository extends JpaRepository<Checklist, Integer> {
    @Query("select c from Checklist c left join c.requirements where c.id = :id")
    Checklist findByIdWithRequirements(@Param("id") Integer id);

    List<Checklist> findByChecklistType(ChecklistType type);
}

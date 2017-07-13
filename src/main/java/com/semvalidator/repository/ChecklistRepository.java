package com.semvalidator.repository;

import com.semvalidator.enums.ChecklistType;
import com.semvalidator.model.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */

@Repository
public interface ChecklistRepository extends JpaRepository<Checklist, Integer> {

    List<Checklist> findByChecklistType(ChecklistType type);
}

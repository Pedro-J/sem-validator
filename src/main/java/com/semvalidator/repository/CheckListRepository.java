package com.semvalidator.repository;

import com.semvalidator.model.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */

@Repository
public interface CheckListRepository extends JpaRepository<CheckList, Integer> {
}

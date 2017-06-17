package com.semvalidator.repository;

import com.semvalidator.model.ModelSE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
@Repository
public interface ModelRepository extends JpaRepository<ModelSE, Integer> {
}

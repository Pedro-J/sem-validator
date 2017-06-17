package com.semvalidator.service;

import com.semvalidator.model.Criterion;
import com.semvalidator.model.Requirement;

import java.util.List;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
public interface CriterionService extends GenericService<Criterion>{
    List<Criterion> findByRequirement(Requirement requirement);
}

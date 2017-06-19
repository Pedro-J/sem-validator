package com.semvalidator.service;

import com.semvalidator.model.Requirement;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
public interface RequirementService extends GenericService<Requirement>{
    Requirement findByIdWithCriterions(Integer id);
}

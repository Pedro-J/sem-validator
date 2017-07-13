package com.semvalidator.service;

import com.semvalidator.enums.ChecklistType;
import com.semvalidator.model.Checklist;

import java.util.List;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
public interface ChecklistService extends GenericService<Checklist>{
    List<Checklist> findByChecklistType(ChecklistType type);
}

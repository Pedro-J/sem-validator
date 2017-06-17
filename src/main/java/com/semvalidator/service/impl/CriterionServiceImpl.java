package com.semvalidator.service.impl;

import com.semvalidator.model.Criterion;
import com.semvalidator.model.Requirement;
import com.semvalidator.repository.CriterionRepository;
import com.semvalidator.repository.RequirementRepository;
import com.semvalidator.service.CriterionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
@Service
@Transactional
public class CriterionServiceImpl implements CriterionService{

    @Autowired
    private CriterionRepository criterionRepository;

    @Autowired
    private RequirementRepository requirementRepository;

    public Criterion save(Criterion entity) {
        Requirement requirement = entity.getRequirement();
        if( requirement != null ){
            if( requirement.getId() == 0 ){
                entity.setRequirement(null);
            }else{
                entity.setRequirement(requirementRepository.findOne(requirement.getId()));
            }

        }

        return criterionRepository.saveAndFlush(entity);
    }

    public Criterion update(Criterion entity) {
        return criterionRepository.saveAndFlush(entity);
    }

    public void delete(Integer id) {
        criterionRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public Criterion findById(Integer id) {
        return criterionRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<Criterion> findAll() {
        return criterionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Criterion> findAllPageable(Pageable pageable) {
        return criterionRepository.findAll(pageable);
    }

    @Override
    public List<Criterion> findByRequirement(Requirement requirement) {
        return criterionRepository.findByRequirement(requirement);
    }
}

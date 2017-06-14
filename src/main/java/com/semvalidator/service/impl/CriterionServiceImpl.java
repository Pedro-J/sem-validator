package com.semvalidator.service.impl;

import com.semvalidator.model.Criterion;
import com.semvalidator.repository.CriterionRepository;
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

    public Criterion save(Criterion entity) {
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
}

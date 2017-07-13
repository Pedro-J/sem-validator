package com.semvalidator.service.impl;

import com.semvalidator.model.Criterion;
import com.semvalidator.model.Question;
import com.semvalidator.model.Requirement;
import com.semvalidator.repository.CriterionRepository;
import com.semvalidator.repository.QuestionRepository;
import com.semvalidator.service.QuestionService;
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
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CriterionRepository criterionRepository;

    public Question save(Question entity) {
        return questionRepository.saveAndFlush(entity);
    }

    public Question update(Question entity) {
        return questionRepository.saveAndFlush(entity);
    }

    public void delete(Integer id) {
        questionRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public Question findById(Integer id) {
        return questionRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Question> findAllPageable(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @Override
    public List<Question> findAllAvailable() {
        return questionRepository.findAllAvailable();
    }

    @Override
    public List<Question> findByCriterion(Criterion criterion) {
        return questionRepository.findByCriterion(criterion);
    }

    @Override
    public List<Question> findByRequirement(Requirement requirement) {
        return questionRepository.findByRequirement(requirement);
    }

    @Override
    public List<Question> findByRequirementAndCriterion(Requirement requirement, Criterion criterion) {
        return questionRepository.findByRequirementAndCriterion(requirement, criterion);
    }

}

package com.semvalidator.service.impl;

import com.semvalidator.model.Criterion;
import com.semvalidator.model.Question;
import com.semvalidator.model.Requirement;
import com.semvalidator.repository.CriterionRepository;
import com.semvalidator.repository.QuestionRepository;
import com.semvalidator.repository.RequirementRepository;
import com.semvalidator.service.QuestionService;
import com.semvalidator.util.SearchQuestionParamsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    private QuestionRepository questionRepository;

    private CriterionRepository criterionRepository;

    private RequirementRepository requirementRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, CriterionRepository criterionRepository, RequirementRepository requirementRepository) {
        this.questionRepository = questionRepository;
        this.criterionRepository = criterionRepository;
        this.requirementRepository = requirementRepository;
    }

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

    public List<Question> findAllAvailable() {
        return questionRepository.findAllAvailable();
    }

    @Override
    public List<Question> findAllOrderByRequirementAndCriterion() {
        return questionRepository.findAllOrderByRequirementAndCriterion();
    }

    public List<Question> findByCriterion(Criterion criterion) {
        return questionRepository.findByCriterion(criterion);
    }

    public List<Question> findByRequirement(Requirement requirement) {
        return questionRepository.findByRequirement(requirement);
    }

    public List<Question> findByRequirementAndCriterion(Requirement requirement, Criterion criterion) {
        return questionRepository.findByRequirementAndCriterion(requirement, criterion);
    }

    public Page<Question> search(SearchQuestionParamsDTO params){
        Pageable pageable = new PageRequest(params.getPage(), params.getSize());

        Requirement requirement = null;
        Criterion criterion = null;
        String description = null;

        if( params.getRequirement() != null ){
            requirement = requirementRepository.findOne(params.getRequirement());
        }

        if( params.getCriterion() != null ){
            criterion = criterionRepository.findOne(params.getCriterion());
        }

        if( params.getQuestionDescription() != null ){
            description = "%"+ params.getQuestionDescription()+ "%";
        }

        return searchExecuter(requirement, criterion, description, pageable);
    }

    private Page<Question> searchExecuter(Requirement requirement, Criterion criterion, String description, Pageable pageable) {
        Page<Question> result = null;
        if( requirement != null && criterion != null && description != null ){
            result = questionRepository.findByRequirementAndCriterionAndDescriptionLike(requirement, criterion, description, pageable);
        } else if( requirement != null && criterion != null ){
            result = questionRepository.findByRequirementAndCriterion(requirement, criterion, pageable);
        }else if( requirement != null && description != null ){
            result = questionRepository.findByRequirementAndDescriptionLike(requirement, description, pageable);
        }else if( criterion != null && description != null ){
            result = questionRepository.findByCriterionAndDescriptionLike(criterion, description, pageable);
        }else if( requirement != null ){
            result = questionRepository.findByRequirement(requirement, pageable);
        }else if( criterion != null ){
            result = questionRepository.findByCriterion(criterion, pageable);
        }else if( description != null ){
            result = questionRepository.findByDescriptionLike(description, pageable);
        }else{
            result = questionRepository.findAll(pageable);
        }
        return result;
    }

    public Page<Question> findByChecklist(Integer id, Pageable pageable) {
        return questionRepository.findByChecklist(id, pageable);
    }

    @Override
    public List<Question> findByChecklist(Integer id) {
        return questionRepository.findByChecklist(id);
    }

}

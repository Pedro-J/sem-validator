package com.semvalidator.service.impl;

import com.semvalidator.model.Criterion;
import com.semvalidator.model.Question;
import com.semvalidator.repository.CriterionRepository;
import com.semvalidator.repository.QuestionRepository;
import com.semvalidator.repository.RequirementRepository;
import com.semvalidator.service.CriterionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
@Service
@Transactional
public class CriterionServiceImpl implements CriterionService{

    private CriterionRepository criterionRepository;

    private QuestionRepository questionRepository;

    private RequirementRepository requirementRepository;

    @Autowired
    public CriterionServiceImpl(CriterionRepository criterionRepository, QuestionRepository questionRepository, RequirementRepository requirementRepository) {
        this.criterionRepository = criterionRepository;
        this.questionRepository = questionRepository;
        this.requirementRepository = requirementRepository;
    }

    public Criterion save(Criterion entity) {
        List<Question> questions = entity.getQuestions();

        Criterion savedEntity = criterionRepository.saveAndFlush(entity);

        if( !CollectionUtils.isEmpty(questions) ){
            for(Question question : questions){
                question.setCriterion(entity);
                questionRepository.saveAndFlush(question);
            }
        }

        return savedEntity;
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

package com.semvalidator.service.impl;

import com.semvalidator.model.Evaluation;
import com.semvalidator.repository.EvaluationRepository;
import com.semvalidator.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Override
    public Evaluation save(Evaluation entity) {
        return evaluationRepository.saveAndFlush(entity);
    }

    @Override
    public Evaluation update(Evaluation entity) {
        return evaluationRepository.saveAndFlush(entity);
    }

    @Override
    public Evaluation findById(Integer id) {
        return evaluationRepository.findOne(id);
    }

    @Override
    public List<Evaluation> findAll() {
        return evaluationRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        evaluationRepository.delete(id);
    }

    @Override
    public Page<Evaluation> findAllPageable(Pageable pageable) {
        return evaluationRepository.findAll(pageable);
    }
}

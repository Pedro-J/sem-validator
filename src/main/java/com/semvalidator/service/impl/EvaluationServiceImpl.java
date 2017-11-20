package com.semvalidator.service.impl;

import com.semvalidator.model.Answer;
import com.semvalidator.model.Criterion;
import com.semvalidator.model.Evaluation;
import com.semvalidator.model.Requirement;
import com.semvalidator.repository.AnswerRepository;
import com.semvalidator.repository.EvaluationRepository;
import com.semvalidator.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class EvaluationServiceImpl implements EvaluationService {

    private EvaluationRepository evaluationRepository;

    private AnswerRepository answerRepository;

    @Autowired
    public EvaluationServiceImpl(EvaluationRepository evaluationRepository, AnswerRepository answerRepository) {
        this.evaluationRepository = evaluationRepository;
        this.answerRepository = answerRepository;
    }

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
        List<Answer> answers = answerRepository.findByEvaluation(id);
        answers.stream().forEach( answer -> answerRepository.delete(answer.getId()) );

        evaluationRepository.delete(id);
    }

    @Override
    public Page<Evaluation> findAllPageable(Pageable pageable) {
        return evaluationRepository.findAll(pageable);
    }

    @Override
    public List<Requirement> calculateRequirementsSatisfaction(Integer evaluationId) {
        List<Answer> answers = answerRepository.findByEvaluation(evaluationId);
        Set<Requirement> requirements = new HashSet<>();
        answers.forEach(answer -> requirements.add(answer.getQuestion().getRequirement()));

        requirements.forEach( requirement -> {
            List<Answer> reqAnswers = answerRepository.findByEvaluationAndRequirement(evaluationId, requirement.getId());
            requirement.setSatisfactionValue(0.0f);

            reqAnswers.forEach( reqAnswer -> {
                requirement.setSatisfactionValue( requirement.getSatisfactionValue() +
                        reqAnswer.getValue().getSatisfaction() );
            });

            requirement.setSatisfactionValue( requirement.getSatisfactionValue() / reqAnswers.size() );
        });
        List<Requirement> resultList = new ArrayList<>();
        resultList.addAll(requirements);

        return resultList;
    }

    @Override
    public List<Criterion> calculateCriterionsSatisfaction(Integer evaluationId) {
        List<Answer> answers = answerRepository.findByEvaluation(evaluationId);
        Set<Criterion> criterions = new HashSet<>();
        answers.forEach(answer -> criterions.add(answer.getQuestion().getCriterion()));

        criterions.forEach( criterion -> {
            List<Answer> criterionAnswer = answerRepository.findByEvaluationAndCriterion(evaluationId, criterion.getId());
            criterion.setSatisfactionValue(0.0f);

            criterionAnswer.forEach( reqAnswer -> {
                criterion.setSatisfactionValue( criterion.getSatisfactionValue() +
                        reqAnswer.getValue().getSatisfaction() );
            });

            criterion.setSatisfactionValue( criterion.getSatisfactionValue() / criterionAnswer.size() );
        });

        List<Criterion> resultList = new ArrayList<>();
        resultList.addAll(criterions);

        return resultList;
    }

    @Override
    public Double calculateGeneralSatisfaction(Integer evaluationId) {
        List<Answer> answers = answerRepository.findByEvaluation(evaluationId);

        Double result = answers.stream().mapToDouble( answer -> answer.getValue().getSatisfaction() ).sum() / answers.size();

        return result;
    }
}

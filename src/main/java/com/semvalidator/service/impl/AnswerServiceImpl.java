package com.semvalidator.service.impl;

import com.semvalidator.model.Answer;
import com.semvalidator.model.ModelSE;
import com.semvalidator.repository.AnswerRepository;
import com.semvalidator.repository.ModelRepository;
import com.semvalidator.repository.QuestionRepository;
import com.semvalidator.repository.RequirementRepository;
import com.semvalidator.service.AnswerService;
import com.semvalidator.util.AnswerJsonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
@Service
@Transactional
public class AnswerServiceImpl implements AnswerService{

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private RequirementRepository requirementRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public Answer save(Answer entity) {
        return answerRepository.saveAndFlush(entity);
    }

    public Answer update(Answer entity) {
        return answerRepository.saveAndFlush(entity);
    }

    @Transactional(readOnly = true)
    public Answer findById(Integer id) {
        return answerRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    public void delete(Integer id) {
        answerRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public Page<Answer> findAllPageable(Pageable pageable) {
        return answerRepository.findAll(pageable);
    }

    public void saveAllDatas(List<AnswerJsonDTO> answersJson) {

        List<Answer> answers = new ArrayList<>();
        for( AnswerJsonDTO dto : answersJson){

            Answer answer = new Answer(dto);

            if( alreadyExist(answer) ){
                answer = answerRepository.findByModelAndRequirementAndQuestion(
                        answer.getModel(), answer.getRequirement(), answer.getQuestion() );
                answer.convertFromDTO(dto);
                answerRepository.saveAndFlush(answer);
            }else {
                answers.add(answer);
            }
        }

        answerRepository.save(answers);
    }

    @Transactional(readOnly = true )
    public List<Answer> findByModelOrderByRequirementAndCriterion(ModelSE model) {
        return answerRepository.findByModelOrderByRequirementAndCriterion(model);
    }

    @Transactional(readOnly = true )
    public boolean alreadyExist(Answer answer){
        Answer savedAnswer = answerRepository.findByModelAndRequirementAndQuestion(
                answer.getModel(), answer.getRequirement(), answer.getQuestion());
        if( savedAnswer != null ){
            return true;
        }

        return false;
    }
}

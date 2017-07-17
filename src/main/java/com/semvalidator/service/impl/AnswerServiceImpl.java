package com.semvalidator.service.impl;

import com.semvalidator.model.Answer;
import com.semvalidator.model.Checklist;
import com.semvalidator.repository.AnswerRepository;
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
                answer = answerRepository.findByChecklistAndQuestion(
                        answer.getChecklist(), answer.getQuestion() );
                answer.convertFromDTO(dto);
                answerRepository.saveAndFlush(answer);
            }else {
                answers.add(answer);
            }
        }

        answerRepository.save(answers);
    }

    @Override
    public List<Answer> findByChecklist(Checklist checklist) {
        return answerRepository.findByChecklist(checklist);
    }

    @Transactional(readOnly = true )
    public boolean alreadyExist(Answer answer){
        Answer savedAnswer = answerRepository.findByChecklistAndQuestion(
                answer.getChecklist(), answer.getQuestion());

        if( savedAnswer != null ){
            return true;
        }
        return false;
    }
}

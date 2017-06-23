package com.semvalidator.service.impl;

import com.semvalidator.model.Answer;
import com.semvalidator.repository.AnswerRepository;
import com.semvalidator.service.AnswerService;
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
}

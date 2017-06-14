package com.semvalidator.service.impl;

import com.semvalidator.model.Question;
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
}

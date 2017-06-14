package com.semvalidator.service.impl;

import com.semvalidator.model.CheckList;
import com.semvalidator.repository.CheckListRepository;
import com.semvalidator.service.CheckListService;
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
public class CheckListServiceImpl implements CheckListService{
    @Autowired
    private CheckListRepository checkListRepository;

    public CheckList save(CheckList entity) {
        return checkListRepository.saveAndFlush(entity);
    }

    public CheckList update(CheckList entity) {
        return checkListRepository.saveAndFlush(entity);
    }

    public void delete(Integer id) {
        checkListRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public CheckList findById(Integer id) {
        return checkListRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<CheckList> findAll() {
        return checkListRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<CheckList> findAllPageable(Pageable pageable) {
        return checkListRepository.findAll(pageable);
    }
}

package com.semvalidator.service.impl;

import com.semvalidator.model.Checklist;
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

    public Checklist save(Checklist entity) {
        return checkListRepository.saveAndFlush(entity);
    }

    public Checklist update(Checklist entity) {
        return checkListRepository.saveAndFlush(entity);
    }

    public void delete(Integer id) {
        checkListRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public Checklist findById(Integer id) {
        return checkListRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<Checklist> findAll() {
        return checkListRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Checklist> findAllPageable(Pageable pageable) {
        return checkListRepository.findAll(pageable);
    }
}

package com.semvalidator.service.impl;

import com.semvalidator.model.Requirement;
import com.semvalidator.repository.CheckListRepository;
import com.semvalidator.repository.RequirementRepository;
import com.semvalidator.service.RequirementService;
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
public class RequirementServiceImpl implements RequirementService{
    @Autowired
    private RequirementRepository requirementRepository;

    @Autowired
    private CheckListRepository checkListRepository;

    public Requirement save(Requirement entity) {
/*        Checklist checkList = entity.getChecklist();
        if( checkList != null ){
            if( checkList.getId() == 0 ){
                entity.setChecklist(null);
            }else{
                entity.setChecklist(checkListRepository.findOne(checkList.getId()));
            }
        }*/
        return requirementRepository.saveAndFlush(entity);
    }

    public Requirement update(Requirement entity) {
        return requirementRepository.saveAndFlush(entity);
    }

    public void delete(Integer id) {
        requirementRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public Requirement findById(Integer id) {
        return requirementRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<Requirement> findAll() {
        return requirementRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Requirement> findAllPageable(Pageable pageable) {
        return requirementRepository.findAll(pageable);
    }

    @Override
    public Requirement findByIdWithCriterions(Integer id) {
        return requirementRepository.findByIdWithCriterions(id);
    }
}

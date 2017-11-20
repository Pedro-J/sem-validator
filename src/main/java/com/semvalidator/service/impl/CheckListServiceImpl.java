package com.semvalidator.service.impl;

import com.semvalidator.enums.ChecklistType;
import com.semvalidator.model.Checklist;
import com.semvalidator.model.Evaluation;
import com.semvalidator.repository.ChecklistRepository;
import com.semvalidator.repository.EvaluationRepository;
import com.semvalidator.service.ChecklistService;
import com.semvalidator.util.ChecklistDTO;
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
public class CheckListServiceImpl implements ChecklistService {

    private ChecklistRepository checklistRepository;

    private EvaluationRepository evaluationRepository;

    @Autowired
    public CheckListServiceImpl(ChecklistRepository checklistRepository, EvaluationRepository evaluationRepository) {
        this.checklistRepository = checklistRepository;
        this.evaluationRepository = evaluationRepository;
    }

    public Checklist save(Checklist entity) {
        return checklistRepository.saveAndFlush(entity);
    }

    public Checklist update(Checklist entity) {
        return checklistRepository.saveAndFlush(entity);
    }

    public void delete(Integer id) {
        checklistRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public Checklist findById(Integer id) {
        return checklistRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<Checklist> findAll() {
        return checklistRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Checklist> findAllPageable(Pageable pageable) {
        return checklistRepository.findAll(pageable);
    }

    public List<Checklist> findByChecklistType(ChecklistType type) {
        return checklistRepository.findByChecklistType(type);
    }

    public boolean isDeletable(Integer id){
        List<Evaluation> evaluations = evaluationRepository.findByChecklist(id);

        if( CollectionUtils.isEmpty(evaluations) )
            return true;
        else
            return false;
    }

    public Checklist findByIdWithQuestions(Integer id){
        return checklistRepository.findByIdWithQuestions(id);
    }

    public ChecklistDTO findDTOById(Integer id){
        Checklist checklist = checklistRepository.findByIdWithQuestions(id);
        ChecklistDTO dto = null;

        if( checklist != null )
            dto = new ChecklistDTO(checklist);

        return dto;
    }
}

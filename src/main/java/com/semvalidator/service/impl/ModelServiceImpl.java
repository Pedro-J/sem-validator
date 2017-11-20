package com.semvalidator.service.impl;

import com.semvalidator.infra.FileService;
import com.semvalidator.model.Evaluation;
import com.semvalidator.model.ModelSE;
import com.semvalidator.repository.EvaluationRepository;
import com.semvalidator.repository.ModelRepository;
import com.semvalidator.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
@Service
@Transactional
public class ModelServiceImpl implements ModelService{
    private final String APP_BASE_DIR = "uploaded_files/models";

    private ModelRepository modelRepository;

    private EvaluationRepository evaluationRepository;

    private FileService fileService;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, EvaluationRepository evaluationRepository, FileService fileService) {
        this.modelRepository = modelRepository;
        this.evaluationRepository = evaluationRepository;
        this.fileService = fileService;
    }

    public ModelSE save(ModelSE entity, MultipartFile modelFile) {
        ModelSE savedModel = null;
        if( !entity.isNew() ){
            savedModel = modelRepository.findOne(entity.getId());
            if(savedModel.getModelFileUrl() != null && !savedModel.getModelFileUrl().equals("") )
                fileService.deleteOnExist(savedModel.getModelFileUrl());
        }else {
            modelRepository.saveAndFlush(entity);
        }

        if (modelFile != null && !modelFile.getOriginalFilename().isEmpty()) {
            String baseName = "model_" + entity.getId() + "_";
            String path = fileService.write(APP_BASE_DIR, baseName, modelFile);
            entity.setModelFileUrl(path);
        }else{
            entity.setModelFileUrl(null);
        }

        savedModel = modelRepository.saveAndFlush(entity);

        return savedModel;
    }

    public boolean isDeletable(Integer id){
        List<Evaluation> evaluations = evaluationRepository.findByModel(id);

        if( CollectionUtils.isEmpty(evaluations) )
            return true;
        else
            return false;
    }

    @Override
    public ModelSE save(ModelSE entity) {
        return modelRepository.saveAndFlush(entity);
    }

    @Override
    public ModelSE update(ModelSE entity) {
        return modelRepository.saveAndFlush(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public ModelSE findById(Integer id) {
        return modelRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ModelSE> findAll() {
        return modelRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        modelRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ModelSE> findAllPageable(Pageable pageable) {
        return modelRepository.findAll(pageable);
    }
}

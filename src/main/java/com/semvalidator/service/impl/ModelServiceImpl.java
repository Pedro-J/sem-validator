package com.semvalidator.service.impl;

import com.semvalidator.infra.FileSaver;
import com.semvalidator.model.ModelSE;
import com.semvalidator.repository.ModelRepository;
import com.semvalidator.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
@Service
public class ModelServiceImpl implements ModelService{
    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private FileSaver fileSaver;

    public ModelSE save(ModelSE entity, MultipartFile modelFile) {
        if (modelFile != null && !modelFile.getOriginalFilename().isEmpty()) {
            String baseName = "model_" + entity.getId() + "_";
            String path = fileSaver.write("uploaded_files/models", baseName, modelFile);
            entity.setModelFileUrl(path);
        }else{
            entity.setModelFileUrl(null);
        }

        return modelRepository.saveAndFlush(entity);
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
    public ModelSE findById(Integer id) {
        return modelRepository.findOne(id);
    }

    @Override
    public List<ModelSE> findAll() {
        return modelRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        modelRepository.delete(id);
    }

    @Override
    public Page<ModelSE> findAllPageable(Pageable pageable) {
        return modelRepository.findAll(pageable);
    }
}

package com.semvalidator.service;

import com.semvalidator.model.ModelSE;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Created by Pedro-J on 6/14/17.
 */
public interface ModelService extends GenericService<ModelSE>{
    ModelSE save(ModelSE entity, MultipartFile modelFile);
    boolean isDeletable(Integer id);
}

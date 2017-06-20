package com.semvalidator.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by comp-dev on 4/21/17.
 */
public interface GenericService<E> {
    E save(E entity, MultipartFile modelFile);
    E update(E entity);
    E findById(Integer id);
    List<E> findAll();
    void delete(Integer id);
    Page<E> findAllPageable(Pageable pageable);
}

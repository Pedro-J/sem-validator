package com.semvalidator.service;

import com.semvalidator.model.Persona;
import com.semvalidator.model.PersonaTip;

import java.util.List;

public interface PersonaService {

    List<Persona> findAll();

    List<PersonaTip> findAllTips(Integer personaId);
}

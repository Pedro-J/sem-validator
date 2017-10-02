package com.semvalidator.service.impl;

import com.semvalidator.model.Persona;
import com.semvalidator.model.PersonaTip;
import com.semvalidator.repository.PersonaRepository;
import com.semvalidator.repository.PersonaTipRepository;
import com.semvalidator.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    private PersonaRepository personaRepository;
    private PersonaTipRepository personaTipRepository;

    @Autowired
    public PersonaServiceImpl(PersonaRepository personaRepository, PersonaTipRepository personaTipRepository) {
        this.personaRepository = personaRepository;
        this.personaTipRepository = personaTipRepository;
    }

    @Override
    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    @Override
    public List<PersonaTip> findAllTips(Integer personaId) {
        return personaTipRepository.findByPersona(personaId);
    }
}

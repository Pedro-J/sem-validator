package com.semvalidator.repository;

import com.semvalidator.model.PersonaTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonaTipRepository extends JpaRepository<PersonaTip, Integer> {

    @Query("select pt from PersonaTip pt where pt.persona.id = :personaId order by pt.persona.description")
    List<PersonaTip> findByPersona(@Param("personaId") Integer personaId);
}

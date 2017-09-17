package com.semvalidator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @Author Created by comp-dev on 6/12/17.
 */


@Entity
@Table(name = "tb_requirement")
@NamedQuery(name = "Requirement.findAll", query="select r from Requirement r order by r.description asc ")
public class Requirement extends GenericEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    public Requirement(Integer id) {
        this.id = id;
    }

    public Requirement() {
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return (id == null ? true : false);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

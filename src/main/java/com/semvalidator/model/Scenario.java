package com.semvalidator.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_scenario")
public class Scenario extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 2000)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return id == null ? true : false;
    }
}
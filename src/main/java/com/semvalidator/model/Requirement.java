package com.semvalidator.model;

import javax.persistence.*;

/**
 * @Author Created by comp-dev on 6/12/17.
 */


@Entity
@Table(name = "tb_requirement")
public class Requirement extends GenericEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private CheckList checkList;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}

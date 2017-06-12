package com.semvalidator.model;

import javax.persistence.*;

/**
 * @Author Created by comp-dev on 6/12/17.
 */

@Entity
@Table(name = "tb_criterion")
public class Criterion extends GenericEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Requirement requirement;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

}

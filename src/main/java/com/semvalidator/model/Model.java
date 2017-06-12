package com.semvalidator.model;

import javax.persistence.*;

/**
 * @Author Created by comp-dev on 6/12/17.
 */

@Entity
@Table(name = "tb_model")
public class Model extends GenericEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private String modelFileUrl;

    @ManyToOne
    private CheckList checkListVerificacao;

    @ManyToOne
    private User user;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}

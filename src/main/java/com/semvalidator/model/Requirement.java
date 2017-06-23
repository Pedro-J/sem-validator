package com.semvalidator.model;

import javax.persistence.*;
import java.util.List;

/**
 * @Author Created by comp-dev on 6/12/17.
 */


@Entity
@Table(name = "tb_requirement")
public class Requirement extends GenericEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @ManyToMany//(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "tb_requirement_criterion", joinColumns = {
            @JoinColumn(name = "id_requirement", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "id_criterion",
                    nullable = false) })
    private List<Criterion> criterions;

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

    public List<Criterion> getCriterions() {
        return criterions;
    }

    public void setCriterions(List<Criterion> criterions) {
        this.criterions = criterions;
    }
}

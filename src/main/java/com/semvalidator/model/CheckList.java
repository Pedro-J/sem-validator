package com.semvalidator.model;

import com.semvalidator.enums.CheckListType;

import javax.persistence.*;

/**
 * @Author Created by comp-dev on 6/12/17.
 */

@Entity
@Table(name = "tb_checklist")
public class CheckList extends GenericEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_validator")
    private User userValidator;

    @Enumerated(EnumType.ORDINAL)
    private CheckListType checkListType;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUserValidator() {
        return userValidator;
    }

    public void setUserValidator(User userValidator) {
        this.userValidator = userValidator;
    }

    public CheckListType getCheckListType() {
        return checkListType;
    }

    public void setCheckListType(CheckListType checkListType) {
        this.checkListType = checkListType;
    }
}

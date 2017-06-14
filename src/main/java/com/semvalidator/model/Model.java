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
    @JoinColumn(name = "id_cl_verification")
    private CheckList checkListVerification;

    @ManyToOne
    @JoinColumn(name = "id_cl_validation")
    private CheckList checkListValidation;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModelFileUrl() {
        return modelFileUrl;
    }

    public void setModelFileUrl(String modelFileUrl) {
        this.modelFileUrl = modelFileUrl;
    }

    public CheckList getCheckListVerification() {
        return checkListVerification;
    }

    public void setCheckListVerification(CheckList checkListVerification) {
        this.checkListVerification = checkListVerification;
    }

    public CheckList getCheckListValidation() {
        return checkListValidation;
    }

    public void setCheckListValidation(CheckList checkListValidation) {
        this.checkListValidation = checkListValidation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}

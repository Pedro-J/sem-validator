package com.semvalidator.model;

import javax.persistence.*;

/**
 * @Author Created by comp-dev on 6/12/17.
 */

@Entity
@Table(name = "tb_model")
public class ModelSE extends GenericEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Lob
    @Column(name = "dsc_model")
    private String description;

    @Lob
    @Column(name = "dsc_aplicabilities")
    private String objetivesDesc;

    @Lob
    @Column(name = "dsc_applicabilities")
    private String applicabilitiesDesc;

    @Column(name = "file_url")
    private String modelFileUrl;

    @ManyToOne
    @JoinColumn(name = "id_cl_verification")
    private Checklist checklistVerification;

    @ManyToOne
    @JoinColumn(name = "id_cl_validation")
    private Checklist checklistValidation;

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

    @Override
    public boolean isNew() {
        return (id == null ? true : false);
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

    public Checklist getChecklistVerification() {
        return checklistVerification;
    }

    public void setChecklistVerification(Checklist checklistVerification) {
        this.checklistVerification = checklistVerification;
    }

    public Checklist getChecklistValidation() {
        return checklistValidation;
    }

    public void setChecklistValidation(Checklist checklistValidation) {
        this.checklistValidation = checklistValidation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getObjetivesDesc() {
        return objetivesDesc;
    }

    public void setObjetivesDesc(String objetivesDesc) {
        this.objetivesDesc = objetivesDesc;
    }

    public String getApplicabilitiesDesc() {
        return applicabilitiesDesc;
    }

    public void setApplicabilitiesDesc(String applicabilitiesDesc) {
        this.applicabilitiesDesc = applicabilitiesDesc;
    }
}

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

    @Column(name = "dsc_model", length = 2000)
    private String description;

    @Column(name = "dsc_objectives", length = 2000)
    private String objectivesDesc;

    @Column(name = "dsc_applicabilities", length = 2000)
    private String applicabilitiesDesc;

    @Column(name = "file_url")
    private String modelFileUrl;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public ModelSE(Integer id) {
        this.id = id;
    }

    public ModelSE() {}

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getObjectivesDesc() {
        return objectivesDesc;
    }

    public void setObjectivesDesc(String objectivesDesc) {
        this.objectivesDesc = objectivesDesc;
    }

    public String getApplicabilitiesDesc() {
        return applicabilitiesDesc;
    }

    public void setApplicabilitiesDesc(String applicabilitiesDesc) {
        this.applicabilitiesDesc = applicabilitiesDesc;
    }

}

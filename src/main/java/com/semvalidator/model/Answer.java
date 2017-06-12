package com.semvalidator.model;

import com.semvalidator.enums.AnswerValue;

import javax.persistence.*;

/**
 * @Author Created by comp-dev on 6/12/17.
 */
@Entity
@Table(name = "tb_asnwer")
public class Answer extends GenericEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Model model;

    @ManyToOne
    private Question question;

    @Enumerated(EnumType.ORDINAL)
    private AnswerValue value;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}

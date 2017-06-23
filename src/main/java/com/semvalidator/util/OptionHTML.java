package com.semvalidator.util;

/**
 * @Author Created by Pedro-J on 6/22/17.
 */
public class OptionHTML {
    private Integer value;
    private String label;

    public OptionHTML(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
package org.catalytic.sdk.entities;

import java.util.List;

/**
 * A Field Display Options object
 */
public class FieldDisplayOptions {

    private List<String> choices;
    private Boolean valueRequired;

    public FieldDisplayOptions(List<String> choices, Boolean valueRequired) {
        this.choices = choices;
        this.valueRequired = valueRequired;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public Boolean getValueRequired() {
        return valueRequired;
    }

    public void setValueRequired(Boolean valueRequired) {
        this.valueRequired = valueRequired;
    }

    @Override
    public String toString() {
        return "FieldDisplayOptions{" +
                "choices=" + choices +
                ", valueRequired=" + valueRequired +
                '}';
    }
}

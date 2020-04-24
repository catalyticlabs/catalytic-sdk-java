package org.catalytic.sdk.entities;

import java.util.List;

public class FieldRestrictions {

    private List<String> choices;
    private Boolean valueRequired;

    public FieldRestrictions(List<String> choices, Boolean valueRequired) {
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
}

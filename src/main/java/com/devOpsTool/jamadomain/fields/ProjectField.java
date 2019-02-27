package com.devOpsTool.jamadomain.fields;

import com.devOpsTool.jamadomain.values.ProjectFieldValue;

public class ProjectField extends JamaField {
    @Override
    public ProjectFieldValue getValue() {
        ProjectFieldValue projectFieldValue = new ProjectFieldValue();
        populateFieldValue(projectFieldValue);
        return projectFieldValue;
    }

    public ProjectField(String type) {
        super(type);
    }

    public ProjectField() {
        super();
    }

}

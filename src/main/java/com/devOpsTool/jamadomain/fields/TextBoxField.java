package com.devOpsTool.jamadomain.fields;

import com.devOpsTool.jamadomain.values.TextBoxFieldValue;

public class TextBoxField extends JamaField {
    @Override
    public TextBoxFieldValue getValue() {
        TextBoxFieldValue textBoxFieldValue = new TextBoxFieldValue();
        populateFieldValue(textBoxFieldValue);
        return textBoxFieldValue;
    }

    public TextBoxField(String type) {
        super(type);
    }

    public TextBoxField() {
        super();
    }

}

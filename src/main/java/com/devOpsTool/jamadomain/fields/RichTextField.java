package com.devOpsTool.jamadomain.fields;

import com.devOpsTool.jamadomain.values.RichTextFieldValue;

public class RichTextField extends JamaField {
    @Override
    public RichTextFieldValue getValue() {
        RichTextFieldValue richTextFieldValue = new RichTextFieldValue();
        populateFieldValue(richTextFieldValue);
        return richTextFieldValue;
    }

    public RichTextField(String type) {
        super(type);
    }

    public RichTextField() {
        super();
    }

}

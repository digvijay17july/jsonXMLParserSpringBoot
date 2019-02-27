package com.devOpsTool.jamadomain.fields;

import com.devOpsTool.jamadomain.values.URLFieldValue;

public class URLField extends JamaField {
    @Override
    public URLFieldValue getValue() {
        URLFieldValue urlFieldValue = new URLFieldValue();
        populateFieldValue(urlFieldValue);
        return urlFieldValue;
    }

    public URLField(String type) {
        super(type);
    }

    public URLField() {
        super();
    }

}

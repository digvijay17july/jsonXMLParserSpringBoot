package com.devOpsTool.jamadomain.values;

import com.jamasoftware.services.restclient.exception.JamaTypeMismatchException;

public class TextFieldValue extends JamaFieldValue {
    private String value;

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setValue(Object value) throws JamaTypeMismatchException {
        checkType(String.class, value);
        this.value = (String)value;
    }
}

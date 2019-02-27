package com.devOpsTool.jamadomain.fields;

import com.devOpsTool.jamadomain.values.DateFieldValue;

public class DateField extends JamaField {
    @Override
    public DateFieldValue getValue() {
        DateFieldValue dateFieldValue = new DateFieldValue();
        populateFieldValue(dateFieldValue);
        return dateFieldValue;
    }

    public DateField(String type) {
        super(type);
    }

    public DateField() {
        super();
    }

}

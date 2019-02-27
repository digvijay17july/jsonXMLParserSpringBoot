package com.devOpsTool.jamadomain.fields;

import com.devOpsTool.jamadomain.values.CalculatedFieldValue;

public class CalculatedField extends JamaField {
    @Override
    public CalculatedFieldValue getValue() {
        CalculatedFieldValue calculatedFieldValue = new CalculatedFieldValue();
        populateFieldValue(calculatedFieldValue);
        return calculatedFieldValue;
    }

    public CalculatedField(String type) {
        super(type);
    }

    public CalculatedField() {
        super();
    }

}

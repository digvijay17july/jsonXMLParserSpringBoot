package com.devOpsTool.jamadomain.fields;

import com.devOpsTool.jamadomain.values.FlagFieldValue;

public class FlagField extends JamaField {
    @Override
    public FlagFieldValue getValue() {
        FlagFieldValue flagFieldValue = new FlagFieldValue();
        populateFieldValue(flagFieldValue);
        return flagFieldValue;
    }

    public FlagField(String type) {
        super(type);
    }

    public FlagField() {
        super();
    }

}

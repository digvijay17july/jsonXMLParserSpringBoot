package com.devOpsTool.jamadomain.fields;

import com.devOpsTool.jamadomain.values.RollupFieldValue;

public class RollupField extends JamaField {
    @Override
    public RollupFieldValue getValue() {
        RollupFieldValue rollupFieldValue = new RollupFieldValue();
        populateFieldValue(rollupFieldValue);
        return rollupFieldValue;
    }

    public RollupField(String type) {
        super(type);
    }

    public RollupField() {
        super();
    }


}

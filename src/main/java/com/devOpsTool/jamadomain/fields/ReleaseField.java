package com.devOpsTool.jamadomain.fields;

import com.devOpsTool.jamadomain.values.ReleaseFieldValue;

public class ReleaseField extends JamaField {
    @Override
    public ReleaseFieldValue getValue() {
        ReleaseFieldValue releaseFieldValue = new ReleaseFieldValue();
        populateFieldValue(releaseFieldValue);
        return releaseFieldValue;
    }

    public ReleaseField(String type) {
        super(type);
    }

    public ReleaseField() {
        super();
    }

}

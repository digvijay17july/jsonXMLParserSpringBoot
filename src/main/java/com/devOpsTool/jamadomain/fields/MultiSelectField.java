package com.devOpsTool.jamadomain.fields;

import com.devOpsTool.jamadomain.lazyresources.PickList;
import com.devOpsTool.jamadomain.values.MultiSelectFieldValue;

public class MultiSelectField extends JamaField {
    private PickList pickList;

    public PickList getPickList() {
        return pickList;
    }

    public void setPickList(PickList pickList) {
        this.pickList = pickList;
    }

    @Override
    public MultiSelectFieldValue getValue() {
        MultiSelectFieldValue multiSelectFieldValue = new MultiSelectFieldValue();
        populateFieldValue(multiSelectFieldValue);
        return multiSelectFieldValue;
    }

    public MultiSelectField(String type) {
        super(type);
    }

    public MultiSelectField() {
        super();
    }

}

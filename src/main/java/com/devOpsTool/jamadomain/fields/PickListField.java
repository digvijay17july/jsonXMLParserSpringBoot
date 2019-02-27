package com.devOpsTool.jamadomain.fields;

import com.devOpsTool.jamadomain.lazyresources.PickList;
import com.devOpsTool.jamadomain.values.PickListFieldValue;

public class PickListField extends JamaField {
    private int picklistId;
    private PickList pickList;

    public int getPicklistId() {
        return picklistId;
    }

    public void setPickList(PickList pickList) {
        this.pickList = pickList;
    }

    @Override
    public PickListFieldValue getValue() {
        PickListFieldValue pickListFieldValue = new PickListFieldValue();
        populateFieldValue(pickListFieldValue);
        return pickListFieldValue;
    }

    public PickListField(String type) {
        super(type);
    }

    public PickListField() {
        super();
    }


    public PickList getPickList() {
        return pickList;
    }
}

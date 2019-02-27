package com.devOpsTool.jamadomain.fields;

import com.devOpsTool.jamadomain.values.UserFieldValue;

public class UserField extends JamaField {
    @Override
    public UserFieldValue getValue() {
        UserFieldValue userFieldValue = new UserFieldValue();
        populateFieldValue(userFieldValue);
        return userFieldValue;
    }

    public UserField(String type) {
        super(type);
    }

    public UserField() {
        super();
    }

}

package com.devOpsTool.jamadomain.fields;

import com.devOpsTool.jamadomain.values.TestCaseStatusFieldValue;

public class TestCaseStatusField extends JamaField {
    @Override
    public TestCaseStatusFieldValue getValue() {
        TestCaseStatusFieldValue testCaseStatusFieldValue = new TestCaseStatusFieldValue();
        populateFieldValue(testCaseStatusFieldValue);
        return testCaseStatusFieldValue;
    }

    public TestCaseStatusField(String type) {
        super(type);
    }

    public TestCaseStatusField() {
        super();
    }
}

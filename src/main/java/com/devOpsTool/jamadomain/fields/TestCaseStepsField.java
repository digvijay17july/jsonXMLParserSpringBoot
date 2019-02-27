package com.devOpsTool.jamadomain.fields;

import com.devOpsTool.jamadomain.values.TestCaseStepsFieldValue;

public class TestCaseStepsField extends JamaField {
    @Override
    public TestCaseStepsFieldValue getValue() {
        TestCaseStepsFieldValue testCaseStepsFieldValue = new TestCaseStepsFieldValue();
        populateFieldValue(testCaseStepsFieldValue);
        return testCaseStepsFieldValue;
    }

    public TestCaseStepsField(String type) {
        super(type);
    }

    public TestCaseStepsField() {
        super();
    }

}

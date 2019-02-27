package com.devOpsTool.constants;

public enum PropertyConstants {
    URL("url"),
    XML_FILE_PATH("xmlFilesPath"),
    USERNAME("username"),
    PASSWORD("password"),
    RESOURCE_TIME_OUT("resourceTimeOut"),
    ;

    private String value;

    PropertyConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

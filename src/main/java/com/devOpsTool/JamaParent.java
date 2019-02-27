package com.devOpsTool;

import com.devOpsTool.exception.RestClientException;
import com.devOpsTool.jamaServices.JamaInstance;
import com.devOpsTool.jamadomain.lazyresources.JamaItem;

import java.util.List;

// Decorator
public interface JamaParent {
    List<JamaItem> getChildren() throws RestClientException;
//    void addChild(JamaItem jamaItem) throws RestClientException;
    boolean isProject();
    void makeChildOf(JamaParent jamaParent) throws RestClientException;
    Integer getId();
    JamaInstance getJamaInstance();
}

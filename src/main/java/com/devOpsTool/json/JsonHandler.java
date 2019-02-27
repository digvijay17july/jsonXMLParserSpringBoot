package com.devOpsTool.json;

import com.devOpsTool.jamaclient.JamaPage;
import com.devOpsTool.jamaServices.JamaDomainObject;
import com.devOpsTool.jamaServices.JamaInstance;
import org.springframework.web.client.RestClientException;

public interface JsonHandler {
    JamaPage getPage(String json, JamaInstance jamaInstance) throws RestClientException;

    JamaDomainObject deserialize(String json, JamaInstance jamaInstance) throws RestClientException;

    String serializeCreated(JamaDomainObject jamaDomainObject) throws RestClientException;

    String serializeEdited(JamaDomainObject jamaDomainObject) throws RestClientException;

    Integer deserializeLocation(String response) throws RestClientException;
}



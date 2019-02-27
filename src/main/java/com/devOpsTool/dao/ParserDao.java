package com.devOpsTool.dao;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Base64;
import java.util.Map;

public interface ParserDao {
    String getDataFromServer(String url, String username, String password);

    String getXmlData(String jsonObject) throws Exception;

    void createXMLFile(String xmlData) throws Exception;

    default HttpHeaders getHeadersWithBasicAuth(String username, String password, Map<String, String> headersMap) {
        String plainCreds = username + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        headers.setContentType(MediaType.APPLICATION_JSON);
        for (Map.Entry<String, String> entry : headersMap.entrySet()) {
            headers.add(entry.getKey(), entry.getValue());
        }
        return headers;
    }

}

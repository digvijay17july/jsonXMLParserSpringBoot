package com.devOpsTool.jamaclient;

import com.devOpsTool.dao.ParserDao;
import com.devOpsTool.jamadomain.core.JamaDomainObject;
import com.devOpsTool.jamadomain.core.JamaInstance;
import com.devOpsTool.json.JsonHandler;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

public class JamaClient {
    private ParserDao parserDao;
    private JsonHandler json;
    private String username;
    private String password;
    private String baseUrl;


    public JamaClient(ParserDao parserDao, JsonHandler json, String baseUrl, String username, String password) {
        this.parserDao = parserDao;
        this.json = json;
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
    }

    public JamaDomainObject getResource(String resource, JamaInstance jamaInstance) throws RestClientException {
        String response = parserDao.getDataFromServer(baseUrl + resource, username, password);
        return json.deserialize(response, jamaInstance);
    }

    public JamaPage getPage(String url, JamaInstance jamaInstance) throws RestClientException {
        return getPage(url, "", jamaInstance);
    }


    public JamaPage getPage(String url, String startAt, JamaInstance jamaInstance) throws RestClientException {
        String response = parserDao.getDataFromServer(url + startAt, username, password);
        JamaPage page = json.getPage(response, jamaInstance);
        page.setJamaClient(this);
        page.setUrl(url);
        return page;
    }

    public List<JamaDomainObject> getAll(String url, JamaInstance jamaInstance) throws RestClientException {
        List<JamaDomainObject> results = new ArrayList<>();
        JamaPage page = getPage(url, jamaInstance);
        results.addAll(page.getResults());
        while (page.hasNext()) {
            page = page.getNext(jamaInstance);
            results.addAll(page.getResults());
        }
        return results;
    }
    public void ping() throws RestClientException {
        parserDao.getDataFromServer(baseUrl, username, password);
    }
}

package com.devOpsTool.config;

import com.devOpsTool.dao.ParserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class JamaConfig {
    @Value("${serverProperties.url}")
    private String baseUrl;

    @Value("${serverProperties.username}")
    private String username;

    @Value("${serverProperties.password}")
    private String password;


    @Autowired
    private ParserDao parserDao;

    @Value("${serverProperties.resourceTimeOut}")
    private Integer resourceTimeOut;    //value is seconds
    private String openUrlBase;
    private String apiKey = null;

    @Value("${serverProperties.resource}")
    private String resource;

    public ParserDao getParserDao() {
        return parserDao;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }


    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        if (baseUrl.contains("/rest/")) {
            if (baseUrl.endsWith("/")) {
                this.baseUrl = baseUrl;
                return;
            }
            this.baseUrl = baseUrl + "/";
            return;
        }
        if (baseUrl.endsWith("/")) {
            this.baseUrl = baseUrl + "rest/v1/";
            return;
        }
        this.baseUrl = baseUrl + "/rest/v1/";
    }

    public void setOpenUrlBase(String baseOpenUrl) {
        if (baseOpenUrl.contains("/perspective.req#/")) {
            if (baseOpenUrl.contains("items/")) {
                if (baseOpenUrl.endsWith("/")) {
                    this.openUrlBase = baseOpenUrl;
                    return;
                }
                this.openUrlBase = baseOpenUrl + "/";
                return;
            } else {
                if (baseOpenUrl.endsWith("/")) {
                    this.openUrlBase = baseOpenUrl + "items/";
                } else {
                    this.openUrlBase = baseOpenUrl + "/items/";
                }
            }
        } else {
            if (baseOpenUrl.endsWith("/")) {
                this.openUrlBase = baseOpenUrl + "perspective.req#/items/";
                return;
            }
            this.openUrlBase = baseOpenUrl + "/perspective.req#/items/";
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public ParserDao getParseDao() {
        return parserDao;
    }

    public void setParserDao(ParserDao parserDao) {
        this.parserDao = parserDao;
    }

    public Integer getResourceTimeOut() {
        return resourceTimeOut;
    }

    public void setResourceTimeOut(Integer resourceTimeOut) {
        this.resourceTimeOut = resourceTimeOut;
    }

    public String getOpenUrlBase() {
        return this.openUrlBase;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return this.apiKey;
    }
}
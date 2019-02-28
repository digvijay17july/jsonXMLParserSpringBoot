package com.devOpsTool.dao;

import com.devOpsTool.config.ToolConfiguration;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.text.ParseException;

@Service
public class ParserDaoImpl implements ParserDao {
    private Logger LOGGER = LoggerFactory.getLogger(ParserDaoImpl.class);
    private RestTemplate restTemplate;
    private ToolConfiguration toolConfiguration;


    public ParserDaoImpl(RestTemplate restTemplate, ToolConfiguration toolConfiguration) {
        this.restTemplate = restTemplate;
        this.toolConfiguration = toolConfiguration;
    }

    @Override
    public String getDataFromServer(String url, String username, String password) {
        LOGGER.info("Entering.. ParserDaoImpl.getDataFromServer");
        JSONArray body = null;

        //Uncomment this file to add server call
        try {
            body = getPages(url, username, password);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LOGGER.info("EXit.. ParserDaoImpl.getDataFromServer");
        return body.toString();
    }

    @Override
    public String getXmlData(String jsonObject) throws Exception {
        String xmlData;
        LOGGER.info("Entering.. ParserDaoImpl.getXmlData with Data : ${}", jsonObject);
        try {
            JSONObject json = new JSONObject(jsonObject);
            xmlData = XML.toString(json);
            return xmlData;
        } catch (Exception ex) {
            LOGGER.error("Exception.. ParserDaoImpl.getXmlData with exception : ${} ", ex);
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void createXMLFile(String xmlData) {
        LOGGER.info("Entering.. ParserDaoImpl.createXMLFile with Data : ${}", xmlData);
        try {
            Document document = convertStringToXMLDocument(xmlData);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            String path = toolConfiguration.getServerProperties().get("xmlFilesPath") == null ? toolConfiguration.getServerProperties().get("xmlFilesPath") : System.getProperty("user.dir");
            StreamResult result = new StreamResult(new File(path + "/ScoreDetail.xml"));
            transformer.transform(source, result);
            LOGGER.info("EXit.. ParserDaoImpl.getXmlData");
        } catch (Exception ex) {
            LOGGER.error("Exception.. ParserDaoImpl.createXMLFile with exception : ${} ", ex);
        }
    }

    private Document convertStringToXMLDocument(String xmlString) throws Exception {
        LOGGER.info("Entering.. ParserDaoImpl.convertStringToXMLDocument with Data : ${}", xmlString);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <projects>" + xmlString + "</projects>")));
            LOGGER.info("EXit.. ParserDaoImpl.convertStringToXMLDocument");
            return doc;

        } catch (Exception e) {
            LOGGER.error("Exception.. ParserDaoImpl.convertStringToXMLDocument with exception : ${} ", e);

            throw new Exception(e.getMessage());
        }

    }

    public JSONArray getPages(String url, String username, String password) throws MalformedURLException, IOException, ParseException {
        String baseURL = url + "/rest/latest/";
        JSONArray projects = null;

        String resource = "projects";

        int allowedResults = 20;
        String maxResults = "maxResults=" + allowedResults;

        long resultCount = -1;
        long startIndex = 0;

        HttpHeaders httpHeaders = getHeadersWithBasicAuth(username, password, null);
        JsonParser parser = JsonParserFactory.getJsonParser();

        while (resultCount != 0) {
            String startAt = "startAt=" + startIndex;

            String requestURL = baseURL + resource + "?" + startAt + "&" + maxResults;
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(null, httpHeaders), String.class);

            JSONObject response = (JSONObject) parser.parseMap(responseEntity.getBody());
            JSONObject meta = (JSONObject) response.get("meta");
            JSONObject pageInfo = (JSONObject) meta.get("pageInfo");

            startIndex = (long) pageInfo.get("startIndex") + allowedResults;
            resultCount = (long) pageInfo.get("resultCount");

            projects = (JSONArray) response.get("data");
            for (Object arrayElement : projects) {
                JSONObject project = (JSONObject) arrayElement;
                JSONObject fields = (JSONObject) project.get("fields");
                System.out.println(fields.get("name"));
            }
        }
        return projects;
    }

}

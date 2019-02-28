package com.devOpsTool.pagination;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Base64;


public class Pagination {
    public static void page() throws MalformedURLException, IOException, ParseException {
        String baseURL = "{base url}/rest/latest/";

        // Username and password should be stored according
        // to your organization's security policies
        String username = "API_User";
        String password = "********";
        String auth = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

        String resource = "projects";

        int allowedResults = 20;
        String maxResults = "maxResults=" + allowedResults;

        long resultCount = -1;
        long startIndex = 0;

        JsonParser parser = JsonParserFactory.getJsonParser();

        while (resultCount != 0) {
            String startAt = "startAt=" + startIndex;

            String requestURL = baseURL + resource + "?" + startAt + "&" + maxResults;

            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", "Basic " + auth);

            InputStream content = connection.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(content));

            JSONObject response = (JSONObject) parser.parseMap(in.readLine());
            JSONObject meta = (JSONObject) response.get("meta");
            JSONObject pageInfo = (JSONObject) meta.get("pageInfo");

            startIndex = (long) pageInfo.get("startIndex") + allowedResults;
            resultCount = (long) pageInfo.get("resultCount");

            JSONArray projects = (JSONArray) response.get("data");
            for (Object arrayElement : projects) {
                JSONObject project = (JSONObject) arrayElement;
                JSONObject fields = (JSONObject) project.get("fields");
                System.out.println(fields.get("name"));
            }
        }
    }
}


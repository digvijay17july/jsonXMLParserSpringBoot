package com.devOpsTool.service;

import com.devOpsTool.config.JamaConfig;
import com.devOpsTool.dao.ParserDao;
import com.devOpsTool.exception.RestClientException;
import com.devOpsTool.jamaServices.JamaDomainObject;
import com.devOpsTool.jamaServices.JamaInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParserServiceImpl implements ParserService {
    private Logger LOGGER= LoggerFactory.getLogger(ParserServiceImpl.class);
    @Autowired
    JamaInstance jamaInstance;

    @Autowired
    ParserDao parserDao;

    @Autowired
    JamaConfig jamaConfig;

    @Override
    public String getDataFromServer() {
        LOGGER.info("Entering.. ParserServiceImpl.getDataFromServer");
        List<JamaDomainObject> projectJsonData= null;
        try {
            projectJsonData = jamaInstance.getAll(jamaConfig.getResource());
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        LOGGER.info("EXit.. ParserServiceImpl.getDataFromServer");
        return projectJsonData.toString();
    }

    @Override
    public String getXmlData(String jsonObject) throws Exception{
        LOGGER.info("Entering.. ParserServiceImpl.getXmlData with Data : ${}",jsonObject);
        String projectXmlData=parserDao.getXmlData(jsonObject);
        LOGGER.info("EXit.. ParserServiceImpl.getXmlData");
        return projectXmlData;
    }

    @Override
    public void createXMLFile(String xmlData) throws Exception{
        LOGGER.info("Entering.. ParserServiceImpl.createXMLFile with Data : ${}",xmlData);
        parserDao.createXMLFile(xmlData);
        LOGGER.info("EXit.. ParserServiceImpl.createXMLFile");

    }

}

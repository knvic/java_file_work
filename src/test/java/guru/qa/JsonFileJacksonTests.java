package guru.qa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.*;
import guru.qa.PageObject.PageObjectJsonJackson;
import guru.qa.model.Equipment;
import guru.qa.model.Measurements;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonFileJacksonTests {

    ClassLoader cl = JsonFileJacksonTests.class.getClassLoader();
PageObjectJsonJackson pageObjectJsonJackson=new PageObjectJsonJackson();


    @Test
    public void createJavaObjectFromJsonStringTest() throws JsonProcessingException {
        Equipment equipment = pageObjectJsonJackson.createJavaObjectFromJsonString();
        Assertions.assertEquals("breacker", equipment.getType());
    }



    @Test
    void createJsonWithJacsonLibrary() throws IOException {

        pageObjectJsonJackson.createJsonFileFromJavaObject();
        Assertions.assertTrue(new File("src/test/resources/equipmentsJackson.json").exists());


    }




}

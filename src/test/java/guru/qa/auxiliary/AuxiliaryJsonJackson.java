package guru.qa.auxiliary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import guru.qa.JsonFileJacksonTests;
import guru.qa.interfaces.WorkWithJson;
import guru.qa.model.Equipment;

import java.io.*;
import java.util.List;

public class AuxiliaryJsonJackson implements WorkWithJson {
    ClassLoader cl = JsonFileJacksonTests.class.getClassLoader();

    @Override
    public void createJsonFileFromJavaObject() throws IOException {
        List<Equipment> equipments = WorkWithJson.createEquipments();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        StringWriter stringEmp = new StringWriter();
        objectMapper.writeValue(stringEmp, equipments);

        try (Writer writer = new FileWriter("src/test/resources/equipmentsJackson.json")) {
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true).writeValue(writer, stringEmp);


        }
    }



    @Override

    public Equipment createJavaObjectFromJsonString() throws JsonProcessingException {
        String equipmentJson = "{\r\n  \"name\" : \"�-110\",\r\n  \"type\" : \"breacker\",\r\n  \"voltage\" : \"110\",\r\n  \"list\" : [ {\r\n    \"type\" : \"Analog\",\r\n    \"name\" : \"Power\"\r\n  }, {\r\n    \"type\" : \"Analog\",\r\n    \"name\" : \"Voltage\"\r\n  }, {\r\n    \"type\" : \"Discret\",\r\n    \"name\" : \"Remote\"\r\n  } ]\r\n}";
        ObjectMapper objectMapper = new ObjectMapper();
        Equipment equipment = objectMapper.readValue(equipmentJson, Equipment.class);
        return equipment;
    }

    @Override
    public List<Object> readFromJsonFileTest() throws IOException {
        //TODO
        return null;
    }


}


package guru.qa;

import com.fasterxml.jackson.core.JsonProcessingException;
import guru.qa.auxiliary.AuxiliaryJsonJackson;
import guru.qa.model.Equipment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.*;

public class JsonFileJacksonTests {

    ClassLoader cl = JsonFileJacksonTests.class.getClassLoader();
AuxiliaryJsonJackson auxiliaryJsonJackson =new AuxiliaryJsonJackson();


    @Test
    @DisplayName("Создаем Java объект из строки Json и проверяем содержимое Java объекта с помоью методов класса")
      public void createJavaObjectFromJsonStringTest() throws JsonProcessingException {
        Equipment equipment = auxiliaryJsonJackson.createJavaObjectFromJsonString();
        Assertions.assertEquals("breacker", equipment.getType());
    }



    @Test
    @DisplayName("Создаем json файл из объекта(на основе класса Java c массивом внутри." +
            "Проверяем наличие созданного файла")
    void createJsonWithJacsonLibrary() throws IOException {
        auxiliaryJsonJackson.createJsonFileFromJavaObject();
        Assertions.assertTrue(new File("src/test/resources/equipmentsJackson.json").exists());


    }




}

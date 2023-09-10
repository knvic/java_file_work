package guru.qa;

import com.google.gson.JsonArray;
import guru.qa.PageObjectJsonTests.PageObjectJsonGson;
import guru.qa.model.Equipment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.List;


public class JsonFileGsonTests extends BaseJsonTest {

    PageObjectJsonGson pageObjectJsonGson = new PageObjectJsonGson();

    @Test
    @DisplayName("Создаем json файл из объекта(на основе класса Java c массивом внутри." +
            "Проверяем наличие созданного файла")
    void createJsonFileFromJavaObjectTest() throws IOException {
        pageObjectJsonGson.createJsonFileFromJavaObject();
        Assertions.assertTrue(new File("src/test/resources/equipments.json").exists());
    }

    @Test
    @DisplayName("Читаем и проверяем содержимое Json файла. " +
            "Получаем из имплементации интерфейса как Object Java" +
            "и приводим нужному дла Gson объекту JsonArray")
    void readAndCheckContentsJsonFile() throws IOException {
        List<Object> result= pageObjectJsonGson.readFromJsonFileTest();
        JsonArray jsonArray=(JsonArray)result.get(0);
           Assertions.assertEquals("В-110", jsonArray.get(0)
                .getAsJsonObject()
                .get("name").
                getAsString());
        Assertions.assertEquals("Analog", jsonArray.get(0)
                .getAsJsonObject()
                .get("list")
                .getAsJsonArray()
                .get(0)
                .getAsJsonObject()
                .get("type")
                .getAsString());


    }

    @Test
    @DisplayName("Создаем Java объект из строки Json и проверяем содержимое Java объекта с помоью методов класса")
    public void createJavaObjectFromJsonStringTest() {
        Equipment equipment = pageObjectJsonGson.createJavaObjectFromJsonString();
        Assertions.assertEquals("breacker", equipment.getType());
    }

}

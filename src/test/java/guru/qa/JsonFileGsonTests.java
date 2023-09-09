package guru.qa;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import guru.qa.PageObject.PageObjectJsonGson;
import guru.qa.model.Equipment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.List;


public class JsonFileGsonTests extends BaseJsonTest {

    PageObjectJsonGson pageObjectJsonGson = new PageObjectJsonGson();

    @Test
    void createJsonFileFromJavaObjectTest() throws IOException {
        pageObjectJsonGson.createJsonFileFromJavaObject();
        Assertions.assertTrue(new File("src/test/resources/equipments.json").exists());
    }

    @Test
    void readAndCheckContentsJsonFile() throws IOException {
        JsonArray jsonArray = pageObjectJsonGson.readFromJsonFileTest();

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
    public void createJavaObjectFromJsonStringTest() {
        Equipment equipment = pageObjectJsonGson.createJavaObjectFromJsonString();
        Assertions.assertEquals("breacker", equipment.getType());
    }

}

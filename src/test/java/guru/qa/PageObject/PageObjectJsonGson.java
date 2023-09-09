package guru.qa.PageObject;

import com.google.gson.*;
import guru.qa.JsonFileJacksonTests;
import guru.qa.interfaces.WorkWithJson;
import guru.qa.model.Equipment;
import org.junit.jupiter.api.Assertions;

import java.io.*;
import java.util.List;

public class PageObjectJsonGson implements WorkWithJson {
    ClassLoader cl = JsonFileJacksonTests.class.getClassLoader();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void createJsonFileFromJavaObject() throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Equipment> equipments = WorkWithJson.createEquipments();
        String jsonStr = gson.toJson(equipments);
        JsonArray jsonArray = gson.fromJson(jsonStr, JsonArray.class);
        try (Writer writer = new FileWriter("src/test/resources/equipments.json")) {
            gson.toJson(equipments, writer);
            writer.flush();

        }
    }

    @Override
    public JsonArray readFromJsonFileTest() throws IOException {
        try (InputStream stream = cl.getResourceAsStream("equipments.json");
             Reader reader = new InputStreamReader(stream)) {

            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);

            return jsonArray;

        }
    }

    public JsonArray convertStringJsonToJsonArray(String str) {
        JsonArray jsonArray = gson.fromJson(str, JsonArray.class);
        return jsonArray;

    }

    @Override
    public Equipment createJavaObjectFromJsonString() {

        String equipmentJson = "{\"name\":\"V1\",\"type\":\"breacker\",\"voltage\":\"110\",\"list\":[{\"type\":\"A1\",\"name\":\"110\"},{\"type\":\"A2\",\"name\":\"330\"}]}";
        Gson gson = new Gson();
        Equipment equipment = gson.fromJson(equipmentJson, Equipment.class);
        return equipment;
    }


}
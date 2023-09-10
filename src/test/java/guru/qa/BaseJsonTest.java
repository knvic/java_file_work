package guru.qa;

import com.codeborne.selenide.Configuration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import guru.qa.interfaces.WorkWithJson;
import guru.qa.model.Equipment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class BaseJsonTest {

    @DisplayName("Создаем json файл с наполнением для работы остальных тестов")
    @BeforeAll
    static void createFileJson() throws IOException {
        WorkWithJson.createEquipments();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Equipment> equipments = WorkWithJson.createEquipments();
        try (Writer writer = new FileWriter("src/test/resources/equipments.json")) {
            gson.toJson(equipments, writer);
            writer.flush();
        }
    }

}

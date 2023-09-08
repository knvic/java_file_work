package guru.qa;

import com.google.gson.*;
import guru.qa.model.Equipment;
import guru.qa.model.Measurements;
import org.junit.jupiter.api.Test;


import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JsonFileTests {
/*
{
        "name": "V1",
        "type": "breacker",
        "voltage": "110",
        "measurements": ["diskret", "analog", "remote"]
        }
*/

    @Test
    void jsonFileTests() {
        String equipmentJson1 = "{'name' : 'V1', "
                + "'type': 'breacker',"
                + "'voltage': '110',"
                + "'measurements' : ["
                + "{'type': 'Analog','name': 'U'}, "
                + "{'type': 'Analog','name': 'I'}, "
                + "{'type': 'Discret','name': 'D'}]}";

        String equipmentJson = "{\"name\":\"V1\",\"type\":\"breacker\",\"voltage\":\"110\",\"list\":[{\"type\":\"A1\",\"name\":\"110\"},{\"type\":\"A2\",\"name\":\"330\"}]}";


        Gson gson = new Gson();

        Equipment equipment = gson.fromJson( equipmentJson, Equipment.class);

        System.out.println(equipment);
    }





    @Test
    void createJsonFile() throws Exception {
        List<Equipment> equipments =new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Measurements measurements=new Measurements("Analog","Power");
        Measurements measurements2=new Measurements("Analog","Voltage");
        Measurements measurements3=new Measurements("Discret","Remote");
        List<Measurements> list=List.of(measurements,measurements2,measurements3);
        Equipment equipment= new Equipment("В-110","breacker","110",list);
        equipments.add(equipment);

        measurements=new Measurements("Analog","Current");
        measurements2=new Measurements("Discret","StateEquipment");
        list=List.of(measurements,measurements2);
        equipment= new Equipment("LR-110-I","Disconnector","110",list);
        equipments.add(equipment);

        String jsonStr=gson.toJson(equipments);

        System.out.println(jsonStr);

       try( Writer writer = new FileWriter("src/test/resources/equipments.json")) {
           gson.toJson(equipments, writer);
           writer.flush();
       }

        System.out.println(equipments);

    }

}

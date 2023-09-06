package guru.qa;

import com.google.gson.Gson;
import guru.qa.utils.Equipment;
import org.junit.jupiter.api.Test;

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
        String equipmentJson = "{'name' : 'V1', "
                + "'type': 'breacker',"
                + "'voltage': '110',"
                + "'measurements' : ["
                + "{'type': 'Analog','name': 'U'}, "
                + "{'type': 'Analog','name': 'I'}, "
                + "{'type': 'Discret','name': 'D'}]}";

        Gson gson = new Gson();

        Equipment equipment = gson.fromJson( equipmentJson, Equipment.class);

        System.out.println(equipment);
    }
}

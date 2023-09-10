package guru.qa.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import guru.qa.model.Equipment;
import guru.qa.model.Measurements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface WorkWithJson {


    static List<Equipment> createEquipments() {
        List<Measurements> measurements = new ArrayList<>();
        measurements.add(new Measurements("Analog", "Power"));
        measurements.add(new Measurements("Analog", "Voltage"));
        measurements.add(new Measurements("Discret", "Remote"));
        Equipment equipment1 = new Equipment("В-110", "breacker", "110", measurements);

        measurements = List.of(new Measurements("Analog", "Current"), new Measurements("Discret", "StateEquipment"));
        Equipment equipment2 = new Equipment("LR-110-I", "Disconnector", "110", measurements);

        List<Equipment> equipments = List.of(equipment1, equipment2);
        return equipments;
    }

    void  createJsonFileFromJavaObject() throws IOException;

    Equipment createJavaObjectFromJsonString() throws JsonProcessingException;

    //JsonArray  readFromJsonFileTest() throws IOException;
    List<Object>  readFromJsonFileTest() throws IOException;


}

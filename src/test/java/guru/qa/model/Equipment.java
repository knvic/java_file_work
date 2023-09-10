package guru.qa.model;

/**
 {
 "name" : "В-110",
 "type" : "breacker",
 "voltage" : "110",
 "list" : [ {
 "type" : "Analog",
 "name" : "Power"
 }, {
 "type" : "Analog",
 "name" : "Voltage"
 }, {
 "type" : "Discret",
 "name" : "Remote"
 } ]
 }

**/


import java.util.List;


public class Equipment {

    private String name;
    private String type;
    private String voltage;
    private List<Measurements> list;

    public Equipment(String name, String type, String voltage, List<Measurements> list) {
        this.name = name;
        this.type = type;
        this.voltage = voltage;
        this.list = list;
    }

    public Equipment() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public List<Measurements> getList() {
        return list;
    }

    public void setList(List<Measurements> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", voltage='" + voltage + '\'' +
                ", list=" + list +
                '}';
    }
}

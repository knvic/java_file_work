package guru.qa.model;

/*
{
        "name": "V1",
        "type": "breacker",
        "voltage": "110",
        "typePositions": 1,
        "measurements": ["diskret", "analog", "remote"]
        }
*/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }

       public Equipment() {
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

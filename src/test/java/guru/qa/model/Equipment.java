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
import lombok.Setter;

import java.util.List;


   @Getter
   @Setter
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
   }

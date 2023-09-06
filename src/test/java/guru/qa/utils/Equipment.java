package guru.qa.utils;

/*
{
        "name": "V1",
        "type": "breacker",
        "voltage": "110",
        "typePositions": 1,
        "measurements": ["diskret", "analog", "remote"]
        }
*/

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


    }

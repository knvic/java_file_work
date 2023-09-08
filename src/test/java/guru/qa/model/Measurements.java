package guru.qa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Measurements {

    String type;
    String name;

    public Measurements(String type, String name) {
        this.type = type;
        this.name = name;
    }
}

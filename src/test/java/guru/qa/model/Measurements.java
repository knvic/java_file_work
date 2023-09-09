package guru.qa.model;


import com.google.gson.annotations.SerializedName;



public class Measurements {
    @SerializedName("type")
    private String type;
    @SerializedName("name")
    private String name;

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Measurements{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Measurements() {
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Measurements(String type, String name) {
        this.type = type;
        this.name = name;
    }

}

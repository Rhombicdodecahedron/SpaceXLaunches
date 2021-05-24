package spacexlaunches.main.beans;

public class Rocket {

    private String rocketId;
    private String name;
    private String type;


    public Rocket() {
    }

    public Rocket(String rocketId, String name, String type) {
        this.rocketId = rocketId;
        this.name = name;
        this.type = type;
    }

    public String getRocketId() {
        return rocketId;
    }

    public void setRocketId(String rocketId) {
        this.rocketId = rocketId;
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

    @Override
    public String toString() {
        return "Rocket{" +
                "rocketId='" + rocketId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

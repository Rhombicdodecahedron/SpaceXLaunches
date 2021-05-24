package spacexlaunches.main.beans;

public class LaunchPad {

    private String name;
    private String fullName;
    private String locality;
    private String region;
    private String id;

    public LaunchPad() {

    }

    public LaunchPad(String id, String name, String fullName, String locality, String region) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.locality = locality;
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LaunchPad{" +
                "name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", locality='" + locality + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}

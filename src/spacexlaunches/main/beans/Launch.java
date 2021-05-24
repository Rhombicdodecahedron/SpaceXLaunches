package spacexlaunches.main.beans;

import com.codename1.io.Externalizable;
import com.codename1.io.Util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

public class Launch {

    private String img;
    private String id;
    private String name;
    private int flightNumber;
    private Rocket rocket;
    private Date launchDate;
    private boolean success;
    private LaunchPad launchPad;
    private String details;

    public Launch() {
    }

    public Launch(String img, String id, String name, int flightNumber, Rocket rocket, Date launchDate, boolean success, LaunchPad launchPad, String details) {
        this.img = img;
        this.id = id;
        this.name = name;
        this.flightNumber = flightNumber;
        this.rocket = rocket;
        this.launchDate = launchDate;
        this.success = success;
        this.launchPad = launchPad;
        this.details = details;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public LaunchPad getLaunchPad() {
        return launchPad;
    }

    public void setLaunchPad(LaunchPad launchPad) {
        this.launchPad = launchPad;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Launch{" +
                "img='" + img + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", flightNumber=" + flightNumber +
                ", rocket=" + rocket +
                ", launchDate=" + launchDate +
                ", success=" + success +
                ", launchPad=" + launchPad +
                ", details='" + details + '\'' +
                '}';
    }

}

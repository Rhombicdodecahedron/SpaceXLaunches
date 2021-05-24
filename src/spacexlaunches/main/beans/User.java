package spacexlaunches.main.beans;

import java.util.Date;

public class User {

    private int pk_user;
    private String firstname;
    private String lastname;
    private String email;
    private Date timestamp;


    public User() {
    }

    public User(int pk_user, String firstname, String lastname, String email, Date timestamp) {
        this.pk_user = pk_user;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "User{" +
                "pk_user=" + pk_user +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    public int getPk_user() {
        return pk_user;
    }

    public void setPk_user(int pk_user) {
        this.pk_user = pk_user;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}

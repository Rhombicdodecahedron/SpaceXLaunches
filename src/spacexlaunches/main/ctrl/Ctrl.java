package spacexlaunches.main.ctrl;

import spacexlaunches.main.beans.Launch;
import spacexlaunches.main.enumeration.Sort;
import spacexlaunches.main.ihm.Ihm;
import spacexlaunches.main.wrk.Wrk;
import com.codename1.io.Log;

import java.util.ArrayList;
import java.util.Map;

import static com.codename1.ui.CN.*;

public class Ctrl {


    private Ihm refIhm;
    private Wrk refWrk;

    public Ctrl() {

    }


    public void afficheLogin() {
        refIhm.afficheLogin();
    }

    public void start() {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if (err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();

            refIhm.showError("There was a networking error in the connection to " + err.getConnectionRequest().getUrl());
        });

        refIhm.showCurrentView();

    }

    public void checkLogin(String email, String password) {
        if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {
            try {
                Map<String, Object> response = refWrk.postUserLogin(email, password);
                boolean isError = Boolean.parseBoolean(response.get("error").toString());
                String message = response.get("message").toString();
                if (!isError) {
                    afficheLogged();
                } else {
                    refIhm.showError(message);
                }
            } catch (Exception e) {
                refIhm.showError(e.getMessage());
            }
        } else {
            refIhm.showError("You need to complete all fields of the form !");
        }
    }

    public void checkRegister(String firstname, String lastname, String email, String password) {
        if (email != null && !email.isEmpty() && firstname != null && !firstname.isEmpty() && lastname != null && !lastname.isEmpty() && password != null && !password.isEmpty()) {
            try {
                Map<String, Object> response = refWrk.postUserRegister(firstname, lastname, email, password);
                boolean isError = Boolean.parseBoolean(response.get("error").toString());
                String message = response.get("message").toString();
                if (!isError) {
                    afficheLogged();
                } else {
                    refIhm.showError(message);
                }
            } catch (Exception e) {
                refIhm.showError(e.getMessage());
            }
        } else {
            refIhm.showError("You need to complete all fields of the form !");
        }
    }

    public ArrayList<Launch> getAllLaunches(Sort sort) throws Exception {
        return refWrk.getAllLaunches(sort);
    }
    public ArrayList<Launch> getAllLaunches() throws Exception {
        return refWrk.getAllLaunches();
    }
    public void afficheHome() {
        refIhm.afficheHome();
    }

    public void afficheLogged() {
        //TESTER SI L'UTILISATEUR EST CONNECTE OU NON
        refIhm.afficheLogged();
        //getAllLaunches();
    }

    public void isUserConnectedAndShowView() {
        try {
            if (refWrk.isUserConnected()) {
                afficheLogged();
            } else {
                afficheHome();
            }
        } catch (Exception e) {
            refIhm.showError(e.getMessage());
        }
    }


    public Launch getNextLaunches() throws Exception {
        return refWrk.getNextLaunch();
    }

    public void disconnect() {
        //faire une popup qui pose une question de si nous voulons vraiment nous déconnecter
        if (refIhm.askPopUp("Yep")) {
            try {
                if (!refWrk.disconnect()) {
                    refIhm.infoPopUp("Info", "Vous avez correctement été déconecté !", "OK", null);
                }
            } catch (Exception e) {
                refIhm.showError(e.getMessage());
            }
        }
    }

    public void stop() {
        /*
        current = getCurrentForm();
        if (current instanceof Dialog) {
            ((Dialog) current).dispose();
            current = getCurrentForm();
        }*/
    }


    //SETTER
    public void setRefIhm(Ihm refIhm) {
        this.refIhm = refIhm;
    }

    public void setRefWrk(Wrk refWrk) {
        this.refWrk = refWrk;
    }

}

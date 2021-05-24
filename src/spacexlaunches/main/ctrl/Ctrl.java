package spacexlaunches.main.ctrl;

import spacexlaunches.main.beans.Launch;
import spacexlaunches.main.beans.User;
import spacexlaunches.main.ihm.Ihm;
import spacexlaunches.main.wrk.Wrk;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static com.codename1.ui.CN.addNetworkErrorListener;
import static com.codename1.ui.CN.updateNetworkThreadCount;

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

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(false);

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
    }

    public void checkLogin(String email, String password) {
        if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {
            try {
                Map<String, Object> response = refWrk.postUserLogin(email, password);

                boolean isError = Boolean.parseBoolean(response.get("error").toString());
                String message = response.get("message").toString();
                if (!isError) {
                    //  refIhm.infoPopUp("Success", message, "OK", null);
                    afficheLogged();
                } else {
                    refIhm.showError(message);
                }
            } catch (Exception e) {
                refIhm.showError(e.getMessage());
            }
        } else {

        }
    }

    public void checkRegister(String firstname, String lastname, String email, String password) {
        if (email != null && !email.isEmpty() && firstname != null && !firstname.isEmpty() && lastname != null && !lastname.isEmpty() && password != null && !password.isEmpty()) {
            try {
                Map<String, Object> response = refWrk.postUserRegister(firstname, lastname, email, password);

                boolean isError = Boolean.parseBoolean(response.get("error").toString());

                String message = response.get("message").toString();
                if (!isError) {
                    //  refIhm.infoPopUp("Success", message, "OK", null);
                    afficheLogged();
                } else {
                    refIhm.showError(message);
                }
            } catch (Exception e) {
                refIhm.showError(e.getMessage());
            }

        }
    }


    public void connectDatabase(String jpa_pu) {
        if (jpa_pu != null && !jpa_pu.isEmpty()) {
            try {
                // boolean ok = refWrk.connectDatabase(jpa_pu);
                //    if (ok) {
                //ok
                //     } else {
                //error
                //   }

            } catch (Exception e) {
                //e.printStackTrace();
            }

        }
    }


    public ArrayList<Launch> getAllLaunches() throws Exception {
        // ArrayList<Launch> result = null;
        //  try {
        //refIhm.showLoadingScreen();
        // refIhm.showAllLaunches();
        // refIhm.hideLoadingScreen();
        //    result = ;
        // } catch (Exception e) {
        //    refIhm.infoPopUp("Error", e.getMessage(), "OK", null);
        //  }
        return refWrk.getAllLaunches();
    }


    //SETTER

    public void setRefIhm(Ihm refIhm) {
        this.refIhm = refIhm;
    }

    public void setRefWrk(Wrk refWrk) {
        this.refWrk = refWrk;
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
        try {
            if (!refWrk.disconnect()) {
                refIhm.infoPopUp("Info", "Vous avez correctement été déconecté !", "OK", null);
            }
        } catch (Exception e) {
            refIhm.showError(e.getMessage());
        }
    }
}

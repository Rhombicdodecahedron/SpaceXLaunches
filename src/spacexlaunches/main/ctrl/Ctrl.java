package spacexlaunches.main.ctrl;

import com.codename1.ui.Form;
import spacexlaunches.main.Constantes;
import spacexlaunches.main.beans.Launch;
import spacexlaunches.main.enumeration.ListIhm;
import spacexlaunches.main.enumeration.Sort;
import spacexlaunches.main.exception.RestException;
import spacexlaunches.main.ihm.Ihm;
import spacexlaunches.main.wrk.Wrk;
import com.codename1.io.Log;

import java.util.ArrayList;
import java.util.Map;

import static com.codename1.ui.CN.*;

public class Ctrl {


    private Ihm refIhm;
    private Wrk refWrk;


    public void start() {
        updateNetworkThreadCount(2);
        Log.bindCrashProtection(true);
        showCurrentView();
    }

    public void checkLogin(String email, String password) {
        if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {
            try {
                Map<String, Object> response = refWrk.postUserLogin(email, password);
                boolean isError = Boolean.parseBoolean(response.get(Constantes.ERROR).toString());
                String message = response.get("message").toString();
                if (!isError) {
                    refIhm.afficheLogged();
                } else {
                    showError(message);
                }
            } catch (Exception e) {
                showError(e.getMessage());
            }
        } else {
            showError("You haven't completed all the fields of this form !");
        }
    }

    public void checkRegister(String firstname, String lastname, String email, String password) {
        if (email != null && !email.isEmpty() && firstname != null && !firstname.isEmpty() && lastname != null && !lastname.isEmpty() && password != null && !password.isEmpty()) {
            try {
                Map<String, Object> response = refWrk.postUserRegister(firstname, lastname, email, password);
                boolean isError = Boolean.parseBoolean(response.get(Constantes.ERROR).toString());
                String message = response.get("message").toString();
                if (!isError) {
                    refIhm.afficheLogin();
                } else {
                    showError(message);
                }
            } catch (Exception e) {
                showError(e.getMessage());
            }
        } else {
            showError("You need to complete all fields of the form !");
        }
    }

    public ArrayList<Launch> getAllLaunches(Sort sort, ArrayList<Launch> launches) {
        return refWrk.getAllLaunches(sort, launches);
    }

    public Launch getNextLaunches() throws Exception {
        return refWrk.getNextLaunch();
    }

    public void disconnect() {
        try {
            if (refWrk.disconnect()) {
                refIhm.infoPopUp("Info", "You're disconnected !", "OK", null);
            }
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    public void stop() {
        refIhm.stop();
    }


    public void showCurrentView() {
        try {
            boolean isUserConnected = refWrk.isUserConnected();
            Form current = getCurrentForm();

            if (current != null && current.getName() != null) {
                String currentFormName = current.getName();
                if (currentFormName != null) {
                    if (isUserConnected) {
                        switch (ListIhm.valueOf(currentFormName)) {
                            case IhmHome:
                            case IhmLogin:
                            case IhmRegister:
                                refIhm.afficheLogged();
                                break;
                        }
                    } else {
                        switch (ListIhm.valueOf(currentFormName)) {
                            case IhmLogged:
                            case IhmLaunchInfo:
                                refIhm.afficheHome();
                                break;
                        }
                    }
                }


            } else {
                if (isUserConnected) {
                    refIhm.afficheLogged();
                } else {
                    refIhm.afficheHome();
                }
            }
        } catch (RestException e) {
            showError(e.getMessage());
            refIhm.afficheHome();
        } catch (NullPointerException ignored) {
            ignored.printStackTrace();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    public boolean writeSortToStorage(String sort) {
        return refWrk.writeSortToStorage(sort);
    }

    public Sort readSortFromStorage() {
        return refWrk.readSortFromStorage();
    }

    public ArrayList<Launch> getAllLaunches() throws Exception {
        return refWrk.getAllLaunches();
    }

    //SETTER
    public void setRefIhm(Ihm refIhm) {
        this.refIhm = refIhm;
    }

    public void setRefWrk(Wrk refWrk) {
        this.refWrk = refWrk;
    }

    public void showError(String message) {
        refIhm.showError(message);
    }
}

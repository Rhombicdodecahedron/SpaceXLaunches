package spacexlaunches.main.wrk;

import spacexlaunches.main.beans.Launch;
import spacexlaunches.main.beans.User;
import spacexlaunches.main.ctrl.Ctrl;

import java.util.ArrayList;
import java.util.Map;

public class Wrk {

    private Ctrl refCtrl;

    private final WrkREST wrkREST;

    public Wrk() {
        wrkREST = new WrkREST();
    }


    public ArrayList<Launch> getAllLaunches() throws Exception {
        return wrkREST.getAllLaunches();
    }

    public Map<String, Object> postUserLogin(String email, String password) throws Exception {
        return wrkREST.postUserLogin(email, password);
    }

    public Map<String, Object> postUserRegister(String firstname, String lastname, String email, String password) throws Exception {
        return wrkREST.postUserRegister(firstname, lastname, email, password);
    }

    public User getSession() throws Exception {
        return wrkREST.getSession();
    }

    public boolean disconnect() throws Exception {
        return wrkREST.disconnect();
    }

    public boolean isUserConnected() throws Exception {
        return wrkREST.isUserConnected();
    }

    public Launch getNextLaunch() throws Exception {
        return wrkREST.getNextLaunch();
    }


    public void setRefCtrl(Ctrl refCtrl) {
        this.refCtrl = refCtrl;
    }

}

package spacexlaunches.main.ihm;

import com.codename1.ui.Toolbar;
import spacexlaunches.main.beans.Launch;
import spacexlaunches.main.ctrl.Ctrl;
import com.codename1.ui.Dialog;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import spacexlaunches.main.enumeration.Sort;

import java.util.ArrayList;

public class Ihm {

    private Ctrl refCtrl;
    private final IhmRegister ihmRegister;
    private final IhmHome ihmHome;
    private final IhmLogin ihmLogin;
    private final IhmLogged ihmLogged;
    private final IhmLaunchInfo ihmLaunchInfo;


    private Dialog infiniteProgress;


    public Ihm() {
        Resources theme = UIManager.initFirstTheme("/theme");

        ihmRegister = new IhmRegister(theme);
        ihmRegister.setRefIhm(this);

        ihmHome = new IhmHome(theme);
        ihmHome.setRefIhm(this);

        ihmLogin = new IhmLogin(theme);
        ihmLogin.setRefIhm(this);

        ihmLogged = new IhmLogged(theme);
        ihmLogged.setRefIhm(this);

        ihmLaunchInfo = new IhmLaunchInfo(theme);
        ihmLaunchInfo.setRefIhm(this);

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(false);
    }

    public void afficheLogin() {
        ihmLogin.show();
    }

    public void afficheRegister() {
        ihmRegister.show();
    }

    public void afficheLogged() {
        ihmLogged.show();
        ihmLogged.showAllLaunches();
    }


    public void checkLogin(String email, String password) {
        refCtrl.checkLogin(email, password);
    }

    public void checkRegister(String firstname, String lastname, String email, String password) {
        refCtrl.checkRegister(firstname, lastname, email, password);
    }


    public void infoPopUp(String title, String text, String okText, String cancelText) {
        Dialog.show(title, text, okText, cancelText);
        // LaunchErrorDialog.show(null, null, true);
    }


    public void setRefCtrl(Ctrl refCtrl) {
        this.refCtrl = refCtrl;
    }

    public void afficheHome() {
        ihmHome.show();
    }

    public void goBackHome() {
        ihmHome.showBack();
    }

    public ArrayList<Launch> getAllLaunches(Sort sort, ArrayList<Launch> launches) {
        return refCtrl.getAllLaunches(sort, launches);
    }

    public ArrayList<Launch> getAllLaunches() throws Exception{
        return refCtrl.getAllLaunches();
    }

    public Launch getNextLaunch() throws Exception {
        return refCtrl.getNextLaunches();
    }

    public void disconnect() {
        refCtrl.disconnect();
    }

    public void showError(String message) {
        Dialog.show("Error", message, "OK", null);
    }

    public void showLaunchInfo(Launch launch) {
        ihmLaunchInfo.showInfos(launch);
        ihmLaunchInfo.show();
    }

    public void goBackLoggedView() {
        ihmLogged.showBack();
    }

    public boolean askPopUp(String message) {
        return Dialog.show("Confirmation", message, "Yes", "No");
    }

}

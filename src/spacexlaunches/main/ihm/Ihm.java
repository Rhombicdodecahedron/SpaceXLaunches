package spacexlaunches.main.ihm;

import spacexlaunches.main.beans.Launch;
import spacexlaunches.main.ctrl.Ctrl;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Dialog;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

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

    public void showLoadingScreen() {
        infiniteProgress = new InfiniteProgress().showInfiniteBlocking();
    }

    public void hideLoadingScreen() {
        infiniteProgress.dispose();
    }

    public ArrayList<Launch> getAllLaunches() throws Exception {
        return refCtrl.getAllLaunches();
    }

    public Launch getNextLaunch() throws Exception {
        return refCtrl.getNextLaunches();
    }

    public void disconnect() {
        refCtrl.disconnect();
    }

    public void showError(String message) {

    }

    public void showLaunchInfo(Launch launch) {
        ihmLaunchInfo.showInfos(launch);
        ihmLaunchInfo.show();
    }

    public void goBackLoggedView() {
        ihmLogged.showBack();
    }
}

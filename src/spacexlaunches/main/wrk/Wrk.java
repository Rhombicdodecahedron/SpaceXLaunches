package spacexlaunches.main.wrk;

import spacexlaunches.main.beans.Launch;
import spacexlaunches.main.ctrl.Ctrl;
import spacexlaunches.main.enumeration.Sort;
import spacexlaunches.main.exception.RestException;

import java.util.ArrayList;
import java.util.Map;

/**
 * Cette classe renferme le worker principal de l'application.
 *
 * @author Stella Alexis
 * @version 1.0
 * @since 17.05.2021
 */
public class Wrk {

    /**
     * Instance de la classe Ctrl
     */
    private Ctrl refCtrl;
    /**
     * Instance de la classe WrkStorage
     */
    private final WrkStorage wrkStorage;
    /**
     * Instance de la classe WrkLaunches
     */
    private final WrkLaunches wrkLaunches;
    /**
     * Instance de la classe WrkDatabase
     */
    private final WrkDatabase wrkDatabase;

    /**
     * Constructeur de la classe Worker de l'application. Il définit les
     * instances de WrkStorage, WrkDataBase et WrkLaunches.
     */
    public Wrk() {
        wrkStorage = new WrkStorage();
        wrkLaunches = new WrkLaunches();
        wrkDatabase = new WrkDatabase();
        wrkLaunches.setRefWrk(this);
        wrkDatabase.setRefWrk(this);
    }

    /* ----------------------- STORAGE ---------------------------------- */

    public boolean writeSortToStorage(String sort) {
        return wrkStorage.writeSortToStorage(sort);
    }

    public Sort readSortFromStorage() {
        return wrkStorage.readSortFromStorage();
    }

    /* ----------------------- LAUNCHES ---------------------------------- */

    public ArrayList<Launch> getAllLaunches(Sort sort, ArrayList<Launch> launches) {
        return wrkLaunches.getAllLaunches(sort, launches);
    }

    public ArrayList<Launch> getAllLaunches() throws Exception {
        return wrkLaunches.getAllLaunches();
    }

    public Launch getNextLaunch() throws Exception {
        return wrkLaunches.getNextLaunch();
    }

    /* ----------------------- USER ---------------------------------- */

    public Map<String, Object> postUserLogin(String email, String password) throws RestException {
        return wrkDatabase.postUserLogin(email, password);
    }

    public Map<String, Object> postUserRegister(String firstname, String lastname, String email, String password) throws RestException {
        return wrkDatabase.postUserRegister(firstname, lastname, email, password);
    }

    public boolean disconnect() throws RestException {
        return wrkDatabase.disconnect();
    }

    public boolean isUserConnected() throws RestException {
        return wrkDatabase.isUserConnected();
    }

    //SETTER AND GETTER

    public void setRefCtrl(Ctrl refCtrl) {
        this.refCtrl = refCtrl;
    }

    public void showError(String message) {
        refCtrl.showError(message);
    }
}

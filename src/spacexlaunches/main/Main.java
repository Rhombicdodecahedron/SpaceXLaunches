package spacexlaunches.main;

import spacexlaunches.main.ctrl.Ctrl;
import spacexlaunches.main.ihm.Ihm;
import spacexlaunches.main.wrk.Wrk;

public class Main {

    private Ctrl ctrl;

    public void init(Object context) {
        ctrl = new Ctrl();
        Ihm ihm = new Ihm();
        Wrk wrk = new Wrk();
        ctrl.setRefIhm(ihm);
        ctrl.setRefWrk(wrk);
        wrk.setRefCtrl(ctrl);
        ihm.setRefCtrl(ctrl);
    }

    public void start() {
        ctrl.start();
    }

    public void stop() {
        ctrl.stop();
    }

    public void destroy() {
    }
}

package spacexlaunches.main.ihm;

import com.codename1.components.MultiButton;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.events.ActionEvent;
import spacexlaunches.main.beans.Launch;
import spacexlaunches.main.utils.DateUtils;

import java.util.ArrayList;

public class IhmLogged extends Form {

    private Ihm refIhm;


    public void showAllLaunches() {
        removeAll();
        InfiniteContainer list = new InfiniteContainer() {
            private ArrayList<Launch> launches;

            @Override
            public Component[] fetchComponents(int index, int amount) {
                MultiButton[] multiButtons = null;
                try {
                    if (index == 0) {
                        launches = refIhm.getAllLaunches();
                    }
                    if (launches.size() > 0) {

                        if ((index + amount) < launches.size()) {
                            multiButtons = new MultiButton[launches.size() + 3];
                            multiButtons[0] = new MultiButton();
                            multiButtons[0].setText("Next Launch");
                            multiButtons[0].setEnabled(false);
                            Launch nextLaunch = refIhm.getNextLaunch();
                            MultiButton nextLaunchMultiButton = createLaunchCard(nextLaunch);
                            if (nextLaunch != null) {
                                multiButtons[1] = nextLaunchMultiButton;
                            }
                            multiButtons[2] = new MultiButton();
                            multiButtons[2].setText("All Launches");
                            multiButtons[2].setEnabled(false);

                            for (int iter = 0; iter < launches.size(); iter++) {
                                Launch currentListing = launches.get(iter);
                                int offset = iter + 3;
                                MultiButton multiButton = createLaunchCard(currentListing);
                                if (multiButton != null) {
                                    multiButtons[offset] = multiButton;
                                }
                            }
                        }
                    }else{
                        removeAll();
                        multiButtons = new MultiButton[1];
                        multiButtons[0] = new MultiButton();
                        multiButtons[0].setText("No Launch");
                        multiButtons[0].setEnabled(false);
                    }

                } catch (Exception e) {
                    refIhm.showError(e.getMessage());
                }
                return multiButtons;
            }
        };
        list.setScrollableY(true);
        addComponent(list);
    }


    private MultiButton createLaunchCard(Launch launch) {
        MultiButton result = null;
        if (launch != null) {
            result = new MultiButton(launch.getName());
            result.addActionListener(evt -> refIhm.showLaunchInfo(launch));
            result.setTextLine2(DateUtils.formatDate(launch.getLaunchDate()));
            String details = launch.getDetails();
            if (details != null && details.length() > 35) {
                details = details.substring(0, 35) + "...";
            }
            result.setTextLine3(details);
        }
        return result;
    }


    public IhmLogged() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }


    public IhmLogged(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getToolbar().setUIID("Transparent");
        getToolbar().setTitle("SpaceX Launches");
    }

    private void onLogoutCommand(ActionEvent ev, Command command) {
        refIhm.disconnect();
        refIhm.goBackHome();
    }


    public void setRefIhm(Ihm refIhm) {
        this.refIhm = refIhm;
    }

    //////-- DON'T EDIT BELOW THIS LINE!!!


    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setEnabled(true);
        setScrollableY(false);
        setInlineStylesTheme(resourceObjectInstance);
        setTitle("SpaceX Launches");
        setName("IhmLogged");
        com.codename1.ui.Toolbar cn1Toolbar = getToolbar();
        if (cn1Toolbar == null) {
            cn1Toolbar = new com.codename1.ui.Toolbar();
            setToolbar(cn1Toolbar);
        }
        com.codename1.ui.Command cmd_Logout = new com.codename1.ui.Command("") {
            public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
                onLogoutCommand(ev, this);
            }
        };
        com.codename1.ui.FontImage.setMaterialIcon(cmd_Logout, "\ue879".charAt(0), "TitleCommand");
        cn1Toolbar.addCommandToLeftBar(cmd_Logout);
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}

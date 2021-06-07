package spacexlaunches.main.ihm;

import com.codename1.components.MultiButton;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import spacexlaunches.main.beans.Launch;
import spacexlaunches.main.enumeration.Sort;
import spacexlaunches.main.utils.DateUtils;

import java.util.ArrayList;

public class IhmLogged extends Form {

    private Ihm refIhm;

    public IhmLogged() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }


    public IhmLogged(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getToolbar().setUIID("Transparent");
        getToolbar().setTitle("SpaceX Launches");
    }

    public void showAllLaunches() {
        removeAll();
        InfiniteContainer list = new InfiniteContainer() {
            private ArrayList<Launch> launches;

            @Override
            public Component[] fetchComponents(int index, int amount) {
                MultiButton[] multiButtons = null;
                try {
                    Sort sort = refIhm.readSortFromStorage();
                    if (index == 0) {
                        launches = refIhm.getAllLaunches(sort, refIhm.getAllLaunches());
                    }
                    if (launches.size() > 0) {
                        if ((index + amount) < launches.size()) {
                            multiButtons = new MultiButton[launches.size() + 4];
                            MultiButton b = new MultiButton("Filters");
                            b.setTextLine2(sort.toString());

                            b.addActionListener(e -> {
                                Dialog d = new Dialog();
                                d.setLayout(BoxLayout.y());
                                d.getContentPane().setScrollableY(true);
                                for (Sort s : Sort.values()) {
                                    MultiButton mb = new MultiButton(s.toString());
                                    d.add(mb);
                                    mb.addActionListener(ee -> {
                                        if (refIhm.writeSortToStorage(s.toString())) {
                                            d.dispose();
                                            showAllLaunches();
                                        }
                                    });
                                }
                                d.showPopupDialog(b);
                            });
                            multiButtons[0] = b;
                            multiButtons[1] = new MultiButton();
                            multiButtons[1].setText("Next Launch");
                            multiButtons[1].setEnabled(false);
                            Launch nextLaunch = refIhm.getNextLaunch();
                            MultiButton nextLaunchMultiButton = createLaunchCard(nextLaunch);
                            if (nextLaunch != null) {
                                multiButtons[2] = nextLaunchMultiButton;
                            }
                            multiButtons[3] = new MultiButton();
                            multiButtons[3].setText("All Launches");
                            multiButtons[3].setEnabled(false);

                            for (int iter = 0; iter < launches.size(); iter++) {
                                Launch currentListing = launches.get(iter);
                                int offset = iter + 4;
                                MultiButton multiButton = createLaunchCard(currentListing);
                                if (multiButton != null) {
                                    multiButtons[offset] = multiButton;
                                }
                            }
                        }
                    } else {
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
        add(list);
        revalidate();
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

    private void onLogoutCommand(ActionEvent ev, Command command) {
        refIhm.disconnect();
        refIhm.goBackHome();
    }

    public void setRefIhm(Ihm refIhm) {
        this.refIhm = refIhm;
    }

    //////////////-- DON'T EDIT BELOW THIS LINE!!!


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
        if(cn1Toolbar == null) {
                cn1Toolbar = new com.codename1.ui.Toolbar();
                setToolbar(cn1Toolbar);
        }
        com.codename1.ui.Command cmd_Logout = new com.codename1.ui.Command("") {
                public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
                        onLogoutCommand(ev, this);
                }
        };
        com.codename1.ui.FontImage.setMaterialIcon(cmd_Logout,"\ue879".charAt(0), "TitleCommand");
        cn1Toolbar.addCommandToLeftBar(cmd_Logout);
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}

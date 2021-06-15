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
    private ArrayList<Launch> launches;
    private Launch nextLaunch;

    public IhmLogged() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }


    public IhmLogged(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getToolbar().setTitle("SpaceX Launches");
    }

    public void showAllLaunches() {
        removeAll();
        InfiniteContainer list = new InfiniteContainer() {
            private final Sort sort = refIhm.readSortFromStorage();

            @Override
            public void refresh() {
                Display.getInstance().invokeAndBlock(() -> {
                    try {
                        launches = refIhm.getAllLaunches(sort, refIhm.getAllLaunches());
                        nextLaunch = refIhm.getNextLaunch();
                        showAllLaunches();
                    } catch (Exception e) {
                        refIhm.showError(e.getMessage());
                    }
                });
            }

            @Override
            public Component[] fetchComponents(int index, int amount) {
                MultiButton[] multiButtons = null;

                if (launches != null && nextLaunch != null) {
                    if (index < launches.size()){
                        if (launches.size() > 0) {
                            multiButtons = new MultiButton[launches.size() + 4];
                            MultiButton b = new MultiButton("Filters");
                            b.setTextLine2(sort.toString());
                            b.addActionListener(e -> {
                                Dialog d = new Dialog();
                                d.setLayout(BoxLayout.y());
                                d.getContentPane().setScrollableY(true);
                                for (Sort s : Sort.values()) {
                                    String sortName = s.toString();
                                    MultiButton mb = new MultiButton(sortName);
                                    d.add(mb);
                                    mb.addActionListener(ee -> {
                                        if (refIhm.writeSortToStorage(sortName)) {
                                            d.dispose();
                                            launches = refIhm.getAllLaunches(Sort.valueOf(sortName), launches);
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

                        } else {
                            removeAll();
                            multiButtons = new MultiButton[1];
                            multiButtons[0] = new MultiButton();
                            multiButtons[0].setText("No Launch");
                            multiButtons[0].setEnabled(false);
                        }
                    }

                } else {
                    refresh();
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
            result.setTextLine2(DateUtils.formatDate(launch.getLaunchDate()) + " UTC");
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

    ////////////////////-- DON'T EDIT BELOW THIS LINE!!!


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

package spacexlaunches.main.ihm;

import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.io.Storage;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import spacexlaunches.main.beans.Launch;
import spacexlaunches.main.enumeration.Sort;
import spacexlaunches.main.utils.DateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        String[] characters = {"Tyrion Lannister", "Jaime Lannister", "Cersei Lannister", "Daenerys Targaryen", "Jon Snow"};

        Picker p = new Picker();
        p.setStrings(characters);
        p.setSelectedString(characters[0]);
        p.addActionListener(e -> ToastBar.showMessage("You picked " + p.getSelectedString(), FontImage.MATERIAL_INFO));

        InfiniteContainer list = new InfiniteContainer() {
            private ArrayList<Launch> launches;

            @Override
            public Component[] fetchComponents(int index, int amount) {
                MultiButton[] multiButtons = null;
                try {
                    if (index == 0) {
                        launches = refIhm.getAllLaunches((Sort) Storage.getInstance().readObject("sort"), refIhm.getAllLaunches());

                    }
                    if (launches.size() > 0) {

                        if ((index + amount) < launches.size()) {
                            multiButtons = new MultiButton[launches.size() + 4];
                            //multiButtons[0].addActionListener(e -> ToastBar.showMessage("You picked " + p.getSelectedString(), FontImage.MATERIAL_INFO));
                            MultiButton b = new MultiButton("Filters");
//faire une méthode qui prend
                            b.addActionListener(e -> {
                                Dialog d = new Dialog();
                                d.setLayout(BoxLayout.y());
                                d.getContentPane().setScrollableY(true);
                                for (Sort sort : Sort.values()) {
                                    MultiButton mb = new MultiButton(sort.toString());
                                    d.add(mb);
                                    mb.addActionListener(ee -> {
                                        b.setTextLine1(mb.getTextLine1());
                                        b.setTextLine2(mb.getTextLine2());
                                        b.setIcon(mb.getIcon());


                                        launches = refIhm.getAllLaunches(sort, launches);
                                        d.dispose();
                                        b.revalidate();
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

    private Map<String, Object> createListEntry(String name, String date) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", name);
        entry.put("Line2", date);
        return entry;
    }


    public void setRefIhm(Ihm refIhm) {
        this.refIhm = refIhm;
    }

    //////////-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.ui.Container gui_Picker = new com.codename1.ui.Container(new com.codename1.ui.layouts.LayeredLayout());
    protected com.codename1.ui.Container gui_Container = new com.codename1.ui.Container(new com.codename1.ui.layouts.LayeredLayout());


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
        gui_Picker.setPreferredSizeStr("148.5mm 16.9mm");
        gui_Picker.setInlineStylesTheme(resourceObjectInstance);
        gui_Picker.setName("Picker");
        gui_Container.setPreferredSizeStr("148.2mm 102.9mm");
        gui_Container.setInlineStylesTheme(resourceObjectInstance);
        gui_Container.setName("Container");
        addComponent(gui_Picker);
        addComponent(gui_Container);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Picker.getLayout()).setPreferredWidthMM((float) 148.5);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Picker.getLayout()).setPreferredHeightMM((float) 16.9);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Picker.getParent().getLayout()).setInsets(gui_Picker, "0.0mm 0.0mm 85.95317% 0.0mm").setReferenceComponents(gui_Picker, "-1 -1 -1 -1").setReferencePositions(gui_Picker, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container.getLayout()).setPreferredWidthMM((float) 148.2);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container.getLayout()).setPreferredHeightMM((float) 102.9);
        ((com.codename1.ui.layouts.LayeredLayout) gui_Container.getParent().getLayout()).setInsets(gui_Container, "13.963211% 0.0mm 0.0mm 0.30000305mm").setReferenceComponents(gui_Container, "-1 -1 -1 -1").setReferencePositions(gui_Container, "0.0 0.0 0.0 0.0");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}

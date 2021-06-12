package spacexlaunches.main.ihm;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.table.TableModel;
import spacexlaunches.main.beans.Launch;


public class IhmLaunchInfo extends com.codename1.ui.Form {
    private Ihm refIhm;

    public IhmLaunchInfo() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public IhmLaunchInfo(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

    public void showInfos(Launch launch) {
        if (launch != null) {
            try {
                FontImage p = FontImage.createMaterial(FontImage.MATERIAL_DISABLED_BY_DEFAULT, "Label", 15);
                EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth(), p.getHeight()), true);
                gui_txt_launch_name.setText(launch.getName());
                gui_txt_img.setIcon(p);
                if (launch.getImg() != null) {
                    Display.getInstance().invokeAndBlock(() -> {
                        gui_txt_img.setIcon(URLImage.createToStorage(placeholder, launch.getId(), launch.getImg()));

                    });
                }
                gui_txt_img.setText("");
                gui_Container.removeAll();
                TableModel model = new DefaultTableModel(new String[]{"", ""}, new Object[][]{
                        {"Flight Number", launch.getFlightNumber()},
                        {"Launch Date", launch.getLaunchDate()},
                        {"Rocket Name", launch.getRocket().getName()},
                        {"Rocket Type", launch.getRocket().getType()},
                        {"Launchpad Name", launch.getLaunchPad().getName()},
                        {"Location", launch.getLaunchPad().getLocality()},
                        {"Success", launch.isSuccess()},
                }) {
                    public boolean isCellEditable(int row, int col) {
                        return col != 0;
                    }
                };
                Table table = new Table(model) {
                    @Override
                    protected TableLayout.Constraint createCellConstraint(Object value, int row, int column) {
                        TableLayout.Constraint con = super.createCellConstraint(value, row, column);
                        if (row == 1 && column == 1) {
                            con.setHorizontalSpan(2);
                        }
                        con.setWidthPercentage(50);
                        return con;
                    }
                };
                table.setEnabled(false);
                gui_txt_area.setText(launch.getDetails());
                gui_txt_area.setEditable(false);

                gui_Container.add(BorderLayout.CENTER, table);
            } catch (IllegalArgumentException e) {
                refIhm.showError(e.getMessage());
            }
        }
    }

    private void onBackCommand(ActionEvent ev, Command command) {
        refIhm.goBackLoggedView();
    }

    public void setRefIhm(Ihm refIhm) {
        this.refIhm = refIhm;
    }

    //////////-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.ui.Label gui_txt_launch_name = new com.codename1.ui.Label();
    protected com.codename1.ui.Label gui_txt_img = new com.codename1.ui.Label();
    protected com.codename1.ui.Container gui_Container = new com.codename1.ui.Container(new com.codename1.ui.layouts.LayeredLayout());
    protected com.codename1.ui.TextArea gui_txt_area = new com.codename1.ui.TextArea();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableY(false);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("");
        setName("IhmLaunchInfo");
        com.codename1.ui.Toolbar cn1Toolbar = getToolbar();
        if(cn1Toolbar == null) {
                cn1Toolbar = new com.codename1.ui.Toolbar();
                setToolbar(cn1Toolbar);
        }
        com.codename1.ui.Command cmd_Back = new com.codename1.ui.Command("Back") {
                public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
                        onBackCommand(ev, this);
                }
        };
        com.codename1.ui.FontImage.setMaterialIcon(cmd_Back,"\ue5c4".charAt(0), "TitleCommand");
        cn1Toolbar.addCommandToLeftBar(cmd_Back);
        gui_txt_launch_name.setText("Launch Name");
                gui_txt_launch_name.setInlineStylesTheme(resourceObjectInstance);
        gui_txt_launch_name.setInlineAllStyles("font:6.0mm;");
        gui_txt_launch_name.setName("txt_launch_name");
        gui_txt_img.setText("Label");
                gui_txt_img.setInlineStylesTheme(resourceObjectInstance);
        gui_txt_img.setInlineAllStyles("font:7.0mm;");
        gui_txt_img.setName("txt_img");
        gui_Container.setPreferredSizeStr("76.70166mm 79.41077mm");
        gui_Container.setScrollableY(false);
                gui_Container.setInlineStylesTheme(resourceObjectInstance);
        gui_Container.setName("Container");
        gui_txt_area.setPreferredSizeStr("74.66982mm 28.953606mm");
        gui_txt_area.setText("TextArea");
                gui_txt_area.setInlineStylesTheme(resourceObjectInstance);
        gui_txt_area.setName("txt_area");
        gui_txt_area.setColumns(8);
        addComponent(gui_txt_launch_name);
        addComponent(gui_txt_img);
        addComponent(gui_Container);
        addComponent(gui_txt_area);
        ((com.codename1.ui.layouts.LayeredLayout)gui_txt_launch_name.getParent().getLayout()).setInsets(gui_txt_launch_name, "6.859593% auto auto auto").setReferenceComponents(gui_txt_launch_name, "-1 -1 -1 -1").setReferencePositions(gui_txt_launch_name, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_txt_img.getParent().getLayout()).setInsets(gui_txt_img, "14.737406% auto auto auto").setReferenceComponents(gui_txt_img, "-1 -1 -1 -1").setReferencePositions(gui_txt_img, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container.getLayout()).setPreferredWidthMM((float)76.70166);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container.getLayout()).setPreferredHeightMM((float)79.41077);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container.getParent().getLayout()).setInsets(gui_Container, "29.577465% 0.0mm 26.478872% 0.0mm").setReferenceComponents(gui_Container, "-1 -1 -1 -1").setReferencePositions(gui_Container, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_txt_area.getParent().getLayout()).setInsets(gui_txt_area, "0.0mm 0.0mm 33.451958% auto").setReferenceComponents(gui_txt_area, "2 -1 -1 -1").setReferencePositions(gui_txt_area, "1.0 0.0 0.0 0.0");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}

package spacexlaunches.main.ihm;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class IhmHome extends com.codename1.ui.Form {

    private Ihm refIhm;

    public IhmHome() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public IhmHome(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getToolbar().setUIID("Transparent");
    }

    public void onbtn_load_loginActionEvent(ActionEvent ev) {
        refIhm.afficheLogin();
    }

    public void onbtn_load_registerActionEvent(ActionEvent ev) {
        refIhm.afficheRegister();
    }

    public void setRefIhm(Ihm refIhm) {
        this.refIhm = refIhm;
    }

    private void onInfoCommand(ActionEvent ev, Command command) {
    }
    //////-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.ui.Container gui_Container = new com.codename1.ui.Container(new com.codename1.ui.layouts.LayeredLayout());
    protected com.codename1.ui.Label gui_Label = new com.codename1.ui.Label();
    protected com.codename1.ui.Button gui_btn_load_login = new com.codename1.ui.Button();
    protected com.codename1.ui.Button gui_btn_load_register = new com.codename1.ui.Button();
    protected com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_btn_load_login.addActionListener(callback);
        gui_btn_load_register.addActionListener(callback);
    }

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {
        private com.codename1.ui.Component cmp;
        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();

            if(sourceComponent.getParent().getLeadParent() != null && (sourceComponent.getParent().getLeadParent() instanceof com.codename1.components.MultiButton || sourceComponent.getParent().getLeadParent() instanceof com.codename1.components.SpanButton)) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if(sourceComponent == gui_btn_load_login) {
                onbtn_load_loginActionEvent(ev);
            }
            if(sourceComponent == gui_btn_load_register) {
                onbtn_load_registerActionEvent(ev);
            }
        }

        public void dataChanged(int type, int index) {
        }
    }
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableY(false);
                setInlineStylesTheme(resourceObjectInstance);
        setInlineAllStyles("bgImage:SpaceXAppWallpaper.jpg;");
        setTitle("");
        setName("IhmHome");
        com.codename1.ui.Toolbar cn1Toolbar = getToolbar();
        if(cn1Toolbar == null) {
                cn1Toolbar = new com.codename1.ui.Toolbar();
                setToolbar(cn1Toolbar);
        }
        com.codename1.ui.Command cmd_Info = new com.codename1.ui.Command("") {
                public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
                        onInfoCommand(ev, this);
                }
        };
        cn1Toolbar.addCommandToLeftBar(cmd_Info);
        gui_Container.setPreferredSizeStr("132.27513mm inherit");
                gui_Container.setInlineStylesTheme(resourceObjectInstance);
        gui_Container.setInlineAllStyles("bgImage:null;");
        gui_Container.setName("Container");
        addComponent(gui_Container);
        gui_Label.setText("Launches");
                gui_Label.setInlineStylesTheme(resourceObjectInstance);
        gui_Label.setInlineAllStyles("font:10.0mm; fgColor:fbf9f9; bgImage:null;");
        gui_Label.setName("Label");
        gui_btn_load_login.setPreferredSizeStr("20.899471mm inherit");
        gui_btn_load_login.setText("Log In");
                gui_btn_load_login.setInlineStylesTheme(resourceObjectInstance);
        gui_btn_load_login.setInlineAllStyles("font:6.0mm; fgColor:eb8f0e;");
        gui_btn_load_login.setName("btn_load_login");
        gui_btn_load_register.setPreferredSizeStr("28.042328mm inherit");
        gui_btn_load_register.setText("Register");
                gui_btn_load_register.setInlineStylesTheme(resourceObjectInstance);
        gui_btn_load_register.setInlineAllStyles("font:6.0mm; fgColor:eb8f0e;");
        gui_btn_load_register.setName("btn_load_register");
        gui_Label_1.setPreferredSizeStr("85.50626mm 51.981033mm");
                gui_Label_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Label_1.setInlineAllStyles("bgImage:SpaceX-Logo.png;");
        gui_Label_1.setName("Label_1");
        gui_Container.addComponent(gui_Label);
        gui_Container.addComponent(gui_btn_load_login);
        gui_Container.addComponent(gui_btn_load_register);
        gui_Container.addComponent(gui_Label_1);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Label.getParent().getLayout()).setInsets(gui_Label, "35.603344% auto auto auto").setReferenceComponents(gui_Label, "-1 -1 -1 -1").setReferencePositions(gui_Label, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_btn_load_login.getParent().getLayout()).setInsets(gui_btn_load_login, "19.709543% auto auto 12.060302%").setReferenceComponents(gui_btn_load_login, "0 -1 -1 -1").setReferencePositions(gui_btn_load_login, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_btn_load_register.getParent().getLayout()).setInsets(gui_btn_load_register, "19.709543% 8.040201% auto auto").setReferenceComponents(gui_btn_load_register, "0 -1 -1 -1").setReferencePositions(gui_btn_load_register, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Label_1.getParent().getLayout()).setInsets(gui_Label_1, "44.70588% 5.3596616% 9.558824% 22.849083%").setReferenceComponents(gui_Label_1, "-1 -1 0 -1").setReferencePositions(gui_Label_1, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container.getLayout()).setPreferredWidthMM((float)132.27513);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container.getLayout()).setPreferredHeightMM((float)221.42857);
        ((com.codename1.ui.layouts.LayeredLayout)gui_Container.getParent().getLayout()).setInsets(gui_Container, "0.26455027mm -0.26455027mm -0.26455027mm 0.26455027mm").setReferenceComponents(gui_Container, "-1 -1 -1 -1").setReferencePositions(gui_Container, "0.0 0.0 0.0 0.0");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}

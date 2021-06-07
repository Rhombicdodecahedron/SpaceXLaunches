package spacexlaunches.main.ihm;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;


public class IhmLogin extends com.codename1.ui.Form {

    private Ihm refIhm;

    public IhmLogin() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public IhmLogin(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getToolbar().setTitle("Log In");
    }

    private void onBackCommand(ActionEvent ev, Command command) {
        refIhm.goBackHome();
    }

    private void onbtm_loginActionEvent(ActionEvent ev) {
        refIhm.checkLogin(gui_txt_email.getText(), gui_txt_password.getText());
    }

    public void setRefIhm(Ihm refIhm) {
        this.refIhm = refIhm;
    }

    //////////-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.ui.TextField gui_txt_email = new com.codename1.ui.TextField();
    protected com.codename1.ui.TextField gui_txt_password = new com.codename1.ui.TextField();
    protected com.codename1.ui.Button gui_btm_login = new com.codename1.ui.Button();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_btm_login.addActionListener(callback);
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

            if(sourceComponent == gui_btm_login) {
                onbtm_loginActionEvent(ev);
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
        setTitle("Log In");
        setName("IhmLogin");
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
        gui_txt_email.setPreferredSizeStr("89.41799mm 10.31746mm");
        gui_txt_email.setHint("Email");
                gui_txt_email.setInlineStylesTheme(resourceObjectInstance);
        gui_txt_email.setName("txt_email");
        gui_txt_email.setConstraint(com.codename1.ui.TextArea.EMAILADDR);
        gui_txt_password.setPreferredSizeStr("117.46032mm 10.31746mm");
        gui_txt_password.setHint("Password");
                gui_txt_password.setInlineStylesTheme(resourceObjectInstance);
        gui_txt_password.setName("txt_password");
        gui_txt_password.setConstraint(com.codename1.ui.TextArea.PASSWORD);
        gui_btm_login.setPreferredSizeStr("76.455025mm 9.78836mm");
        gui_btm_login.setText("Log In");
                gui_btm_login.setInlineStylesTheme(resourceObjectInstance);
        gui_btm_login.setInlineAllStyles("font:6.0mm; bgColor:ffffff; transparency:21;");
        gui_btm_login.setName("btm_login");
        addComponent(gui_txt_email);
        addComponent(gui_txt_password);
        addComponent(gui_btm_login);
        ((com.codename1.ui.layouts.LayeredLayout)gui_txt_email.getParent().getLayout()).setInsets(gui_txt_email, "20.644402% 5.56mm auto 5.5555563mm").setReferenceComponents(gui_txt_email, "-1 -1 -1 -1").setReferencePositions(gui_txt_email, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_txt_password.getParent().getLayout()).setInsets(gui_txt_password, "4.622568% 5.56mm auto 5.56mm").setReferenceComponents(gui_txt_password, "0 -1 -1 -1").setReferencePositions(gui_txt_password, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_btm_login.getParent().getLayout()).setInsets(gui_btm_login, "6.349207mm 8.99471mm auto 8.99471mm").setReferenceComponents(gui_btm_login, "1 -1 -1 -1").setReferencePositions(gui_btm_login, "1.0 0.0 0.0 0.0");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}

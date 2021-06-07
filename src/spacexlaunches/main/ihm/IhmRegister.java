package spacexlaunches.main.ihm;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class IhmRegister extends com.codename1.ui.Form {

    private Ihm refIhm;

    public IhmRegister() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public IhmRegister(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getToolbar().setUIID("Transparent");
        getToolbar().setTitle("Register");
    }

    private void onBackCommand(ActionEvent ev, Command command) {
        refIhm.goBackHome();
    }

    public void onbtm_registerActionEvent(ActionEvent ev) {
        refIhm.checkRegister(gui_txt_firstname.getText(), gui_txt_lastname.getText(), gui_txt_email.getText(), gui_txt_password.getText());
    }

    public void setRefIhm(Ihm refIhm) {
        this.refIhm = refIhm;
    }

    ////////-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.ui.TextField gui_txt_firstname = new com.codename1.ui.TextField();
    protected com.codename1.ui.TextField gui_txt_lastname = new com.codename1.ui.TextField();
    protected com.codename1.ui.TextField gui_txt_email = new com.codename1.ui.TextField();
    protected com.codename1.ui.TextField gui_txt_password = new com.codename1.ui.TextField();
    protected com.codename1.ui.Button gui_btm_register = new com.codename1.ui.Button();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_btm_register.addActionListener(callback);
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

            if(sourceComponent == gui_btm_register) {
                onbtm_registerActionEvent(ev);
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
        setTitle("");
        setName("IhmRegister");
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
        gui_txt_firstname.setPreferredSizeStr("33.862434mm 9.523809mm");
        gui_txt_firstname.setHint("Firstname");
                gui_txt_firstname.setInlineStylesTheme(resourceObjectInstance);
        gui_txt_firstname.setInlineAllStyles("alignment:left;");
        gui_txt_firstname.setName("txt_firstname");
        gui_txt_lastname.setPreferredSizeStr("33.862434mm 9.523809mm");
        gui_txt_lastname.setHint("Lastname");
                gui_txt_lastname.setInlineStylesTheme(resourceObjectInstance);
        gui_txt_lastname.setName("txt_lastname");
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
        gui_btm_register.setPreferredSizeStr("76.455025mm 9.78836mm");
        gui_btm_register.setText("Register");
                gui_btm_register.setInlineStylesTheme(resourceObjectInstance);
        gui_btm_register.setInlineAllStyles("font:6.0mm; bgColor:ffffff; transparency:21;");
        gui_btm_register.setName("btm_register");
        addComponent(gui_txt_firstname);
        addComponent(gui_txt_lastname);
        addComponent(gui_txt_email);
        addComponent(gui_txt_password);
        addComponent(gui_btm_register);
        ((com.codename1.ui.layouts.LayeredLayout)gui_txt_firstname.getParent().getLayout()).setInsets(gui_txt_firstname, "20.0mm 59.740265% auto 5.56mm").setReferenceComponents(gui_txt_firstname, "-1 -1 -1 -1").setReferencePositions(gui_txt_firstname, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_txt_lastname.getParent().getLayout()).setInsets(gui_txt_lastname, "20.0mm 5.56mm auto 58.18182%").setReferenceComponents(gui_txt_lastname, "-1 -1 -1 -1").setReferencePositions(gui_txt_lastname, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_txt_email.getParent().getLayout()).setInsets(gui_txt_email, "4.000004% 5.56mm auto 5.5555563mm").setReferenceComponents(gui_txt_email, "0 -1 -1 -1").setReferencePositions(gui_txt_email, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_txt_password.getParent().getLayout()).setInsets(gui_txt_password, "4.622568% 5.56mm auto 5.56mm").setReferenceComponents(gui_txt_password, "2 -1 -1 -1").setReferencePositions(gui_txt_password, "1.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_btm_register.getParent().getLayout()).setInsets(gui_btm_register, "4.4866962mm 8.99471mm auto 8.99471mm").setReferenceComponents(gui_btm_register, "3 -1 -1 -1").setReferencePositions(gui_btm_register, "1.0 0.0 0.0 0.0");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.batorek.carspy;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Vector;
import javax.microedition.rms.*;
import javax.bluetooth.*;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.io.StreamConnection;



/**
 * @author Bartek
 */
public class CarSpy extends MIDlet implements CommandListener, ItemCommandListener {


    private boolean midletPaused = false;
    public Preferences prefs;
    public BluetoothConnection BTConn;
    public COMMReader comread;
    public GPS gps;
    public DataSender dataSender;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Form StartForm;
    private StringItem stringItem;
    private Form OptionsForm;
    private StringItem stringItemOptionsGPS;
    private TextField textFieldOptionsServer;
    private TextField textFieldOptionsTimeout;
    private Form MainForm;
    private StringItem stringItemMainStatus;
    private StringItem stringItemMainFix;
    private StringItem stringItemMainLat;
    private StringItem stringItemMainLon;
    private StringItem stringItemMainAlt;
    private StringItem stringItemMainSpeed;
    private List SearchBTList;
    private Command okCommand;
    private Command exitCommand;
    private Command okCommandOptions;
    private Command cancelCommandOptions;
    private Command exitCommandMain;
    private Command screenCommandMain;
    private Command screenCommandOptions;
    private Command okCommandSearchBT;
    private Command cancelCommandSearchBT;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The CarSpy constructor.
     */
    public CarSpy() {
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|
    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        prefs = new Preferences();
        BTConn = new BluetoothConnection();
        comread = new COMMReader();
        gps = new GPS();
        dataSender = new DataSender();

        switchDisplayable(null, getStartForm());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: StartForm ">//GEN-BEGIN:|14-getter|0|14-preInit
    /**
     * Returns an initiliazed instance of StartForm component.
     * @return the initialized component instance
     */
    public Form getStartForm() {
        if (StartForm == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
            StartForm = new Form("Car Spy v." + getAppProperty("MIDlet-Version"), new Item[] { getStringItem() });//GEN-BEGIN:|14-getter|1|14-postInit
            StartForm.addCommand(getOkCommand());
            StartForm.addCommand(getExitCommand());
            StartForm.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
        }//GEN-BEGIN:|14-getter|2|
        return StartForm;
    }
    //</editor-fold>//GEN-END:|14-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == MainForm) {//GEN-BEGIN:|7-commandAction|1|36-preAction
            if (command == exitCommandMain) {//GEN-END:|7-commandAction|1|36-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|2|36-postAction
                // write post-action user code here
            } else if (command == screenCommandMain) {//GEN-LINE:|7-commandAction|3|34-preAction
                // write pre-action user code here
                loadPrefs();//GEN-LINE:|7-commandAction|4|34-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|26-preAction
        } else if (displayable == OptionsForm) {
            if (command == cancelCommandOptions) {//GEN-END:|7-commandAction|5|26-preAction
                // write pre-action user code here
                prefs.read();
                IfValidPrefsMethod();//GEN-LINE:|7-commandAction|6|26-postAction
                // write post-action user code here
            } else if (command == okCommandOptions) {//GEN-LINE:|7-commandAction|7|24-preAction
                // write pre-action user code here
                savePrefs();//GEN-LINE:|7-commandAction|8|24-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|9|60-preAction
        } else if (displayable == SearchBTList) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|9|60-preAction
                // write pre-action user code here
                SearchBTListAction();//GEN-LINE:|7-commandAction|10|60-postAction
                // write post-action user code here
            } else if (command == cancelCommandSearchBT) {//GEN-LINE:|7-commandAction|11|65-preAction
                // write pre-action user code here
                switchDisplayable(null, getOptionsForm());//GEN-LINE:|7-commandAction|12|65-postAction
                // write post-action user code here
            } else if (command == okCommandSearchBT) {//GEN-LINE:|7-commandAction|13|63-preAction
                // write pre-action user code here
                BTConn.discoverServices();
                switchDisplayable(null, getOptionsForm());//GEN-LINE:|7-commandAction|14|63-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|15|18-preAction
        } else if (displayable == StartForm) {
            if (command == exitCommand) {//GEN-END:|7-commandAction|15|18-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|16|18-postAction
                // write post-action user code here
            } else if (command == okCommand) {//GEN-LINE:|7-commandAction|17|16-preAction
                // write pre-action user code here
                IfValidPrefsMethod();//GEN-LINE:|7-commandAction|18|16-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|19|7-postCommandAction
        }//GEN-END:|7-commandAction|19|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|20|
    //</editor-fold>//GEN-END:|7-commandAction|20|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|15-getter|0|15-preInit
    /**
     * Returns an initiliazed instance of okCommand component.
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {//GEN-END:|15-getter|0|15-preInit
            // write pre-init user code here
            okCommand = new Command("Start", Command.OK, 0);//GEN-LINE:|15-getter|1|15-postInit
            // write post-init user code here
        }//GEN-BEGIN:|15-getter|2|
        return okCommand;
    }
    //</editor-fold>//GEN-END:|15-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|17-getter|0|17-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|17-getter|0|17-preInit
            // write pre-init user code here
            exitCommand = new Command("Wyjd\u017A", Command.EXIT, 0);//GEN-LINE:|17-getter|1|17-postInit
            // write post-init user code here
        }//GEN-BEGIN:|17-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|17-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem ">//GEN-BEGIN:|21-getter|0|21-preInit
    /**
     * Returns an initiliazed instance of stringItem component.
     * @return the initialized component instance
     */
    public StringItem getStringItem() {
        if (stringItem == null) {//GEN-END:|21-getter|0|21-preInit
            // write pre-init user code here
            stringItem = new StringItem("Witamy!\n", "Politechnika \u015Al\u0105ska\nWydzia\u0142 Elektryczny\nProjekt in\u017Cynierski\nBartosz B\u0105torek\nAby rozpocz\u0105\u0107 naci\u015Bnij Start.", Item.PLAIN);//GEN-LINE:|21-getter|1|21-postInit
            // write post-init user code here
        }//GEN-BEGIN:|21-getter|2|
        return stringItem;
    }
    //</editor-fold>//GEN-END:|21-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: OptionsForm ">//GEN-BEGIN:|22-getter|0|22-preInit
    /**
     * Returns an initiliazed instance of OptionsForm component.
     * @return the initialized component instance
     */
    public Form getOptionsForm() {
        if (OptionsForm == null) {//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here
            OptionsForm = new Form("Opcje", new Item[] { getStringItemOptionsGPS(), getTextFieldOptionsServer(), getTextFieldOptionsTimeout() });//GEN-BEGIN:|22-getter|1|22-postInit
            OptionsForm.addCommand(getOkCommandOptions());
            OptionsForm.addCommand(getCancelCommandOptions());
            OptionsForm.setCommandListener(this);//GEN-END:|22-getter|1|22-postInit
            // write post-init user code here
        }//GEN-BEGIN:|22-getter|2|
        return OptionsForm;
    }
    //</editor-fold>//GEN-END:|22-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommandOptions ">//GEN-BEGIN:|23-getter|0|23-preInit
    /**
     * Returns an initiliazed instance of okCommandOptions component.
     * @return the initialized component instance
     */
    public Command getOkCommandOptions() {
        if (okCommandOptions == null) {//GEN-END:|23-getter|0|23-preInit
            // write pre-init user code here
            okCommandOptions = new Command("Zapisz", Command.OK, 1);//GEN-LINE:|23-getter|1|23-postInit
            // write post-init user code here
        }//GEN-BEGIN:|23-getter|2|
        return okCommandOptions;
    }
    //</editor-fold>//GEN-END:|23-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cancelCommandOptions ">//GEN-BEGIN:|25-getter|0|25-preInit
    /**
     * Returns an initiliazed instance of cancelCommandOptions component.
     * @return the initialized component instance
     */
    public Command getCancelCommandOptions() {
        if (cancelCommandOptions == null) {//GEN-END:|25-getter|0|25-preInit
            // write pre-init user code here
            cancelCommandOptions = new Command("Anuluj", Command.CANCEL, 2);//GEN-LINE:|25-getter|1|25-postInit
            // write post-init user code here
        }//GEN-BEGIN:|25-getter|2|
        return cancelCommandOptions;
    }
    //</editor-fold>//GEN-END:|25-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: IfValidPrefsMethod ">//GEN-BEGIN:|27-if|0|27-preIf
    /**
     * Performs an action assigned to the IfValidPrefsMethod if-point.
     */
    public void IfValidPrefsMethod() {//GEN-END:|27-if|0|27-preIf
        // enter pre-if user code here
        if (prefs.IsValidPrefs().booleanValue()) {//GEN-LINE:|27-if|1|28-preAction
            // write pre-action user code here
            btConnectMethod();//GEN-LINE:|27-if|2|28-postAction
            // write post-action user code here
        } else {//GEN-LINE:|27-if|3|29-preAction
            // write pre-action user code here
            switchDisplayable(null, getOptionsForm());//GEN-LINE:|27-if|4|29-postAction
            // write post-action user code here
        }//GEN-LINE:|27-if|5|27-postIf
        // enter post-if user code here
    }//GEN-BEGIN:|27-if|6|
    //</editor-fold>//GEN-END:|27-if|6|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: MainForm ">//GEN-BEGIN:|32-getter|0|32-preInit
    /**
     * Returns an initiliazed instance of MainForm component.
     * @return the initialized component instance
     */
    public Form getMainForm() {
        if (MainForm == null) {//GEN-END:|32-getter|0|32-preInit
            // write pre-init user code here
            MainForm = new Form("CarSpy", new Item[] { getStringItemMainStatus(), getStringItemMainLat(), getStringItemMainLon(), getStringItemMainAlt(), getStringItemMainSpeed(), getStringItemMainFix() });//GEN-BEGIN:|32-getter|1|32-postInit
            MainForm.addCommand(getScreenCommandMain());
            MainForm.addCommand(getExitCommandMain());
            MainForm.setCommandListener(this);//GEN-END:|32-getter|1|32-postInit
            // write post-init user code here
        }//GEN-BEGIN:|32-getter|2|
        return MainForm;
    }
    //</editor-fold>//GEN-END:|32-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommandMain ">//GEN-BEGIN:|33-getter|0|33-preInit
    /**
     * Returns an initiliazed instance of screenCommandMain component.
     * @return the initialized component instance
     */
    public Command getScreenCommandMain() {
        if (screenCommandMain == null) {//GEN-END:|33-getter|0|33-preInit
            // write pre-init user code here
            screenCommandMain = new Command("Opcje", Command.SCREEN, 0);//GEN-LINE:|33-getter|1|33-postInit
            // write post-init user code here
        }//GEN-BEGIN:|33-getter|2|
        return screenCommandMain;
    }
    //</editor-fold>//GEN-END:|33-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommandMain ">//GEN-BEGIN:|35-getter|0|35-preInit
    /**
     * Returns an initiliazed instance of exitCommandMain component.
     * @return the initialized component instance
     */
    public Command getExitCommandMain() {
        if (exitCommandMain == null) {//GEN-END:|35-getter|0|35-preInit
            // write pre-init user code here
            exitCommandMain = new Command("Wyjd\u017A", Command.EXIT, 0);//GEN-LINE:|35-getter|1|35-postInit
            // write post-init user code here
        }//GEN-BEGIN:|35-getter|2|
        return exitCommandMain;
    }
    //</editor-fold>//GEN-END:|35-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItemOptionsGPS ">//GEN-BEGIN:|46-getter|0|46-preInit
    /**
     * Returns an initiliazed instance of stringItemOptionsGPS component.
     * @return the initialized component instance
     */
    public StringItem getStringItemOptionsGPS() {
        if (stringItemOptionsGPS == null) {//GEN-END:|46-getter|0|46-preInit
            // write pre-init user code here
            stringItemOptionsGPS = new StringItem("Ustaw GPS\n", prefs.getGPSName(), Item.BUTTON);//GEN-BEGIN:|46-getter|1|46-postInit
            stringItemOptionsGPS.addCommand(getScreenCommandOptions());
            stringItemOptionsGPS.setItemCommandListener(this);
            stringItemOptionsGPS.setDefaultCommand(getScreenCommandOptions());//GEN-END:|46-getter|1|46-postInit
            // write post-init user code here
        }//GEN-BEGIN:|46-getter|2|
        return stringItemOptionsGPS;
    }
    //</editor-fold>//GEN-END:|46-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Items ">//GEN-BEGIN:|8-itemCommandAction|0|8-preItemCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular item.
     * @param command the Command that was invoked
     * @param displayable the Item where the command was invoked
     */
    public void commandAction(Command command, Item item) {//GEN-END:|8-itemCommandAction|0|8-preItemCommandAction
        // write pre-action user code here
        if (item == stringItemOptionsGPS) {//GEN-BEGIN:|8-itemCommandAction|1|48-preAction
            if (command == screenCommandOptions) {//GEN-END:|8-itemCommandAction|1|48-preAction
                // write pre-action user code here
                switchDisplayable(null, getSearchBTList());//GEN-LINE:|8-itemCommandAction|2|48-postAction
                BTConn.discoverDevices();
                // write post-action user code here
            }//GEN-BEGIN:|8-itemCommandAction|3|8-postItemCommandAction
        }//GEN-END:|8-itemCommandAction|3|8-postItemCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|8-itemCommandAction|4|
    //</editor-fold>//GEN-END:|8-itemCommandAction|4|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommandOptions ">//GEN-BEGIN:|47-getter|0|47-preInit
    /**
     * Returns an initiliazed instance of screenCommandOptions component.
     * @return the initialized component instance
     */
    public Command getScreenCommandOptions() {
        if (screenCommandOptions == null) {//GEN-END:|47-getter|0|47-preInit
            // write pre-init user code here
            screenCommandOptions = new Command("Ustaw GPS", Command.SCREEN, 3);//GEN-LINE:|47-getter|1|47-postInit
            // write post-init user code here
        }//GEN-BEGIN:|47-getter|2|
        return screenCommandOptions;
    }
    //</editor-fold>//GEN-END:|47-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textFieldOptionsServer ">//GEN-BEGIN:|49-getter|0|49-preInit
    /**
     * Returns an initiliazed instance of textFieldOptionsServer component.
     * @return the initialized component instance
     */
    public TextField getTextFieldOptionsServer() {
        if (textFieldOptionsServer == null) {//GEN-END:|49-getter|0|49-preInit
            // write pre-init user code here
            textFieldOptionsServer = new TextField("Adres serwera\n", prefs.getServerAddress(), 80, TextField.ANY | TextField.NON_PREDICTIVE);//GEN-LINE:|49-getter|1|49-postInit
            // write post-init user code here
        }//GEN-BEGIN:|49-getter|2|
        return textFieldOptionsServer;
    }
    //</editor-fold>//GEN-END:|49-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: loadPrefs ">//GEN-BEGIN:|50-entry|0|51-preAction
    /**
     * Performs an action assigned to the loadPrefs entry-point.
     */
    public void loadPrefs() {//GEN-END:|50-entry|0|51-preAction
        // write pre-action user code here

        prefs.read();

        // jesli formatka juz istnieje (byla pokazywana) to wpisujemy wartosci z prefs
        // jesli nie byla, to sie same wpisza z konstruktora formatki
        // najpierw domyslne "", a potem Preferences.read() z konstruktora
        if (OptionsForm != null) {
            stringItemOptionsGPS.setText(prefs.getGPSName());
            textFieldOptionsServer.setString(prefs.getServerAddress());
            textFieldOptionsTimeout.setString(prefs.getTimeout());
        }

        switchDisplayable(null, getOptionsForm());//GEN-LINE:|50-entry|1|51-postAction
        // write post-action user code here
    }//GEN-BEGIN:|50-entry|2|
    //</editor-fold>//GEN-END:|50-entry|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: savePrefs ">//GEN-BEGIN:|54-entry|0|55-preAction
    /**
     * Performs an action assigned to the savePrefs entry-point.
     */
    public void savePrefs() {//GEN-END:|54-entry|0|55-preAction
        // write pre-action user code here

        prefs.setGPSName(stringItemOptionsGPS.getText());
        prefs.setGPSUrl(prefs.getGPSUrl());
        // prefs.GPS_Address jest ustawiany juz na formie szukania BT
        prefs.setServerAddress(textFieldOptionsServer.getString());
        prefs.setTimeout(textFieldOptionsTimeout.getString());

        prefs.write();
        IfValidPrefsMethod();//GEN-LINE:|54-entry|1|55-postAction
        // write post-action user code here
    }//GEN-BEGIN:|54-entry|2|
    //</editor-fold>//GEN-END:|54-entry|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: SearchBTList ">//GEN-BEGIN:|58-getter|0|58-preInit
    /**
     * Returns an initiliazed instance of SearchBTList component.
     * @return the initialized component instance
     */
    public List getSearchBTList() {
        if (SearchBTList == null) {//GEN-END:|58-getter|0|58-preInit
            // write pre-init user code here
            SearchBTList = new List("Szukanie...", Choice.IMPLICIT);//GEN-BEGIN:|58-getter|1|58-postInit
            SearchBTList.addCommand(getOkCommandSearchBT());
            SearchBTList.addCommand(getCancelCommandSearchBT());
            SearchBTList.setCommandListener(this);
            SearchBTList.setFitPolicy(Choice.TEXT_WRAP_DEFAULT);
            SearchBTList.setSelectCommand(null);//GEN-END:|58-getter|1|58-postInit
            // write post-init user code here
        }//GEN-BEGIN:|58-getter|2|
        return SearchBTList;
    }
    //</editor-fold>//GEN-END:|58-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: SearchBTListAction ">//GEN-BEGIN:|58-action|0|58-preAction
    /**
     * Performs an action assigned to the selected list element in the SearchBTList component.
     */
    public void SearchBTListAction() {//GEN-END:|58-action|0|58-preAction
        // enter pre-action user code here
        String __selectedString = getSearchBTList().getString(getSearchBTList().getSelectedIndex());//GEN-LINE:|58-action|1|58-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|58-action|2|
    //</editor-fold>//GEN-END:|58-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommandSearchBT ">//GEN-BEGIN:|62-getter|0|62-preInit
    /**
     * Returns an initiliazed instance of okCommandSearchBT component.
     * @return the initialized component instance
     */
    public Command getOkCommandSearchBT() {
        if (okCommandSearchBT == null) {//GEN-END:|62-getter|0|62-preInit
            // write pre-init user code here
            okCommandSearchBT = new Command("Wybierz", Command.OK, 0);//GEN-LINE:|62-getter|1|62-postInit
            // write post-init user code here
        }//GEN-BEGIN:|62-getter|2|
        return okCommandSearchBT;
    }
    //</editor-fold>//GEN-END:|62-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cancelCommandSearchBT ">//GEN-BEGIN:|64-getter|0|64-preInit
    /**
     * Returns an initiliazed instance of cancelCommandSearchBT component.
     * @return the initialized component instance
     */
    public Command getCancelCommandSearchBT() {
        if (cancelCommandSearchBT == null) {//GEN-END:|64-getter|0|64-preInit
            // write pre-init user code here
            cancelCommandSearchBT = new Command("Anuluj", Command.CANCEL, 0);//GEN-LINE:|64-getter|1|64-postInit
            // write post-init user code here
        }//GEN-BEGIN:|64-getter|2|
        return cancelCommandSearchBT;
    }
    //</editor-fold>//GEN-END:|64-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItemMainStatus ">//GEN-BEGIN:|71-getter|0|71-preInit
    /**
     * Returns an initiliazed instance of stringItemMainStatus component.
     * @return the initialized component instance
     */
    public StringItem getStringItemMainStatus() {
        if (stringItemMainStatus == null) {//GEN-END:|71-getter|0|71-preInit
            // write pre-init user code here
            stringItemMainStatus = new StringItem("Status: ", "\n", Item.PLAIN);//GEN-LINE:|71-getter|1|71-postInit
            // write post-init user code here
        }//GEN-BEGIN:|71-getter|2|
        return stringItemMainStatus;
    }
    //</editor-fold>//GEN-END:|71-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItemMainLat ">//GEN-BEGIN:|72-getter|0|72-preInit
    /**
     * Returns an initiliazed instance of stringItemMainLat component.
     * @return the initialized component instance
     */
    public StringItem getStringItemMainLat() {
        if (stringItemMainLat == null) {//GEN-END:|72-getter|0|72-preInit
            // write pre-init user code here
            stringItemMainLat = new StringItem("Szer: ", "\n");//GEN-LINE:|72-getter|1|72-postInit
            // write post-init user code here
        }//GEN-BEGIN:|72-getter|2|
        return stringItemMainLat;
    }
    //</editor-fold>//GEN-END:|72-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItemMainLon ">//GEN-BEGIN:|73-getter|0|73-preInit
    /**
     * Returns an initiliazed instance of stringItemMainLon component.
     * @return the initialized component instance
     */
    public StringItem getStringItemMainLon() {
        if (stringItemMainLon == null) {//GEN-END:|73-getter|0|73-preInit
            // write pre-init user code here
            stringItemMainLon = new StringItem("D\u0142: ", "\n");//GEN-LINE:|73-getter|1|73-postInit
            // write post-init user code here
        }//GEN-BEGIN:|73-getter|2|
        return stringItemMainLon;
    }
    //</editor-fold>//GEN-END:|73-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItemMainAlt ">//GEN-BEGIN:|74-getter|0|74-preInit
    /**
     * Returns an initiliazed instance of stringItemMainAlt component.
     * @return the initialized component instance
     */
    public StringItem getStringItemMainAlt() {
        if (stringItemMainAlt == null) {//GEN-END:|74-getter|0|74-preInit
            // write pre-init user code here
            stringItemMainAlt = new StringItem("Wys: ", "\n");//GEN-LINE:|74-getter|1|74-postInit
            // write post-init user code here
        }//GEN-BEGIN:|74-getter|2|
        return stringItemMainAlt;
    }
    //</editor-fold>//GEN-END:|74-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItemMainSpeed ">//GEN-BEGIN:|75-getter|0|75-preInit
    /**
     * Returns an initiliazed instance of stringItemMainSpeed component.
     * @return the initialized component instance
     */
    public StringItem getStringItemMainSpeed() {
        if (stringItemMainSpeed == null) {//GEN-END:|75-getter|0|75-preInit
            // write pre-init user code here
            stringItemMainSpeed = new StringItem("Pr\u0119dko\u015B\u0107: ", "\n");//GEN-LINE:|75-getter|1|75-postInit
            // write post-init user code here
        }//GEN-BEGIN:|75-getter|2|
        return stringItemMainSpeed;
    }
    //</editor-fold>//GEN-END:|75-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItemMainFix ">//GEN-BEGIN:|76-getter|0|76-preInit
    /**
     * Returns an initiliazed instance of stringItemMainFix component.
     * @return the initialized component instance
     */
    public StringItem getStringItemMainFix() {
        if (stringItemMainFix == null) {//GEN-END:|76-getter|0|76-preInit
            // write pre-init user code here
            stringItemMainFix = new StringItem("Fix: ", "\n");//GEN-LINE:|76-getter|1|76-postInit
            // write post-init user code here
        }//GEN-BEGIN:|76-getter|2|
        return stringItemMainFix;
    }
    //</editor-fold>//GEN-END:|76-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: btConnectMethod ">//GEN-BEGIN:|77-entry|0|78-preAction
    /**
     * Performs an action assigned to the btConnectMethod entry-point.
     */
    public void btConnectMethod() {//GEN-END:|77-entry|0|78-preAction
        // write pre-action user code here
        switchDisplayable(null, getMainForm());//GEN-LINE:|77-entry|1|78-postAction
        comread.start();
       // dataSender.start();
        // write post-action user code here
    }//GEN-BEGIN:|77-entry|2|
    //</editor-fold>//GEN-END:|77-entry|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textFieldOptionsTimeout ">//GEN-BEGIN:|81-getter|0|81-preInit
    /**
     * Returns an initiliazed instance of textFieldOptionsTimeout component.
     * @return the initialized component instance
     */
    public TextField getTextFieldOptionsTimeout() {
        if (textFieldOptionsTimeout == null) {//GEN-END:|81-getter|0|81-preInit
            // write pre-init user code here
            textFieldOptionsTimeout = new TextField("Cz\u0119sto\u015B\u0107 wysy\u0142ania danych w [ms]\n", prefs.getTimeout(), 5, TextField.NUMERIC);//GEN-LINE:|81-getter|1|81-postInit
            // write post-init user code here
        }//GEN-BEGIN:|81-getter|2|
        return textFieldOptionsTimeout;
    }
    //</editor-fold>//GEN-END:|81-getter|2|

    public void setStatus(String msg) {
        getStringItemMainStatus().setText(msg + "\n");
    }

    // <editor-fold defaultstate="collapsed" desc=" Standard MIDlet methods">
    
    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {        
        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }
    // </editor-fold>


    //<editor-fold defaultstate="collapsed" desc=" Class: Preferences ">
    public final class Preferences {

        private String GPS_Name;
        private String GPS_Url;
        private String Server_Address;
        private String Timeout;

        public Preferences() {
            GPS_Name = "";
            GPS_Url = "";
            Server_Address = "http://batorek.dyndns.org:8080/CarSpyServer6/CarSpyDataSave.do";
            Timeout = "7000";
            read();
        }

        private String removeNull(String text) {
            return text != null ? text : "";
        }

        private String restoreNull(String text) {
            return text.length() > 0 ? text : null;
        }

        public Boolean IsValidPrefs() {
            if ((GPS_Url.equals("")) || (Server_Address.equals("")) || (Timeout.equals(""))) {
                return Boolean.FALSE;
            } else {
                return Boolean.TRUE;
            }
        }

        public String getGPSName() {
            return GPS_Name;
        }

        public String getGPSUrl() {
            return GPS_Url;
        }

        public String getServerAddress() {
            return Server_Address;
        }

        public String getTimeout() {
            return Timeout;
        }

        public void setGPSName(String msg) {
            GPS_Name = msg;
        }

        public void setGPSUrl(String msg) {
            GPS_Url = msg;
        }

        public void setServerAddress(String msg) {
            Server_Address = msg;
        }

        public void setTimeout(String msg) {
            Timeout = msg;
        }

        //<editor-fold defaultstate="expanded" desc=" Method: Preferences read()">
        public void read() {
            RecordStore rs;
            try {
                rs = RecordStore.openRecordStore("carspy", false);
                byte[] buf_byte = rs.getRecord(1);
                ByteArrayInputStream bais = new ByteArrayInputStream(buf_byte);
                DataInputStream data_input = new DataInputStream(bais);

                GPS_Name = data_input.readUTF();
                GPS_Url = data_input.readUTF();
                Server_Address = data_input.readUTF();
                Timeout = data_input.readUTF();

                data_input.close();
                //bais.close();
                rs.closeRecordStore();
            } catch (Exception ex) {
                //ex.printStackTrace();
                write();
                return;
            }
        }
        //</editor-fold>

        //<editor-fold defaultstate="expanded" desc=" Method: Preferences write()">
        public void write() {

            RecordStore rs;
            byte[] buf_byte;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream data_output = new DataOutputStream(baos);

            try {
                //RecordStore.deleteRecordStore("carspy"); //kasujemy wszystko przed zapisem
                rs = RecordStore.openRecordStore("carspy", true);
            } catch (RecordStoreException ex) {
                //ex.printStackTrace();
                return;
            }

            try {
                data_output.writeUTF(GPS_Name);
                data_output.writeUTF(GPS_Url);
                data_output.writeUTF(Server_Address);
                data_output.writeUTF(Timeout);

                buf_byte = baos.toByteArray();

                //rs.addRecord(buf_byte, 0, buf_byte.length);

                if (rs.getNumRecords() == 0) {
                    rs.addRecord(buf_byte, 0, buf_byte.length);
                } else {
                    rs.setRecord(1, buf_byte, 0, buf_byte.length);
                }

                data_output.close();
                //baos.close();
                rs.closeRecordStore();
            } catch (Exception ex) {
            }
        }
        //</editor-fold>


    }
    //</editor-fold>


    //<editor-fold defaultstate="collapsed" desc=" Class: BluetoothConnection ">
    public class BluetoothConnection implements DiscoveryListener {

        Vector remoteDevices = new Vector();
        Vector deviceNames = new Vector();

        DiscoveryAgent discoveryAgent;

        // obviously, 0x1101 is the UUID for
        // the Serial Profile
        UUID[] uuidSet = {new UUID(0x1101) };

        // 0x0100 is the attribute for the service name element
        // in the service record
        int[] attrSet = {0x0100};

        public BluetoothConnection() {

        }


        public void discoverDevices() {
            try {                
                LocalDevice localDevice = LocalDevice.getLocalDevice();
                discoveryAgent = localDevice.getDiscoveryAgent();
                discoveryAgent.startInquiry(DiscoveryAgent.GIAC, this);
                getSearchBTList().setTitle("Szukanie...");
                getSearchBTList().deleteAll();
                remoteDevices.removeAllElements();

            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass cod) {
            
            remoteDevices.addElement(remoteDevice);
            // dodajemy remoteDevice do vectora
            try{
                getSearchBTList().append(remoteDevice.getFriendlyName(true), null);

            } catch (Exception e){
                getSearchBTList().append(remoteDevice.getBluetoothAddress(), null);

            }
            // dodajemy pozycje do formatki z lista urzadzen
            // jak sie da to nazwe, a jak nie, to adres urzadzenia
        }

        public void inquiryCompleted(int discType) {

            if (remoteDevices.size() > 0) {
                getSearchBTList().setTitle("Zako\u0144czono.");
                //!!!dodac tutaj listenera na commandy, zeby nie mozna bylo wybrac urzadzenia
                //!!!przed zakonczeniem szukania

            } else {
                getSearchBTList().setTitle("Nic nie znaleziono!!!");

		}

        }

        public void discoverServices(){

            try {
                RemoteDevice remoteDevice = (RemoteDevice)remoteDevices.elementAt(getSearchBTList().getSelectedIndex());
                discoveryAgent.searchServices(attrSet, uuidSet, remoteDevice , this);
                //po wskazaniu konkretnego urzadzenia patrzymy jakie oferuje uslugi

            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        public void servicesDiscovered(int transID, ServiceRecord[] servRecord){

            for(int i = 0; i < servRecord.length; i++) {

                DataElement serviceNameElement = servRecord[i].getAttributeValue(0x0100);
                String _serviceName = (String)serviceNameElement.getValue();
                String serviceName = _serviceName.trim();

                prefs.setGPSUrl(servRecord[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false));


                try{
                    prefs.setGPSName(servRecord[i].getHostDevice().getFriendlyName(true));

                } catch (Exception e){
                    prefs.setGPSName(servRecord[i].getHostDevice().getBluetoothAddress());

                }

            }
            

        }

        public void serviceSearchCompleted(int transID, int respCode) {

            if (respCode == DiscoveryListener.SERVICE_SEARCH_COMPLETED) {
                // the service search process was successful
                getStringItemOptionsGPS().setText(prefs.getGPSName());

            } else {
                // the service search process has failed
                //alert

            }

        }

   }
   //</editor-fold>


    // <editor-fold defaultstate="collapsed" desc=" Class: COMMReader ">
    public class COMMReader extends Thread {

        public COMMReader() {

        }

        public void run() {
            long millis = 0;
            long millis2 = 0;
            long timeout = Long.parseLong(prefs.getTimeout());

            try {
                StreamConnection connection = (StreamConnection) Connector.open(prefs.getGPSUrl());

                //
                // open an input stream to get some data
                //

                InputStream in = connection.openInputStream();

//			byte[] serialData;
//			//readData = true;
//			//while(readData == true){
//                        while(true == true){
//				int lengthavai=0;
//				lengthavai = in.available();
//
//				if(lengthavai > 0){
//					serialData = new byte[lengthavai];
//					int length = in.read(serialData);
//					//getMainForm().append(new String(serialData));
//                                        getStringItemMainFix().setText(new String(serialData));
//				}
//			}

                setStatus("Połączono.");
                millis = System.currentTimeMillis();
                millis2 = System.currentTimeMillis();
                String output;
                int input;

                while (true == true) {
                    output = "";
                    while ((input = in.read()) != 13) {
                        output += (char) input;

                        //in.read(); //odczytujemy #10 ktore jest po wczesniejszym #13
                    }

                    //getMainForm().append(output);

                    if (output.length() > 6) {  //gdyby był pusty string
                        output = output.substring(1, output.indexOf("*")); //usuwamy #10 z poczatku stringa ktore bylo jako znak konca linii
                        gps.parseNMEA(output);                        
                    }
                    
                    //co 0.5 sec aktualizujemy dane na ekranie
                    if ((System.currentTimeMillis() - millis) > 500) {
                        dataSender.updateScreen();
                        millis = System.currentTimeMillis();
                    }
                    
                    //co kilka sec wysylamy dane (GPRS rozlaczal sie po 10 sec bezaktywnosci)
                    //ale tylko jesli mamy fixa na GPS
                    if ((System.currentTimeMillis() - millis2) > timeout && gps.getFixValid()) {
                        dataSender.sendData();
                        millis2 = System.currentTimeMillis();
                    }
                    
                }

                //in.close();
                //connection.close();

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }


        }
    }
    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc=" Class: GPS ">
    public final class GPS {

        private String sLatitude = " ";
        private String sLatitudeDir = " ";
        private String sLongitude = " ";
        private String sLongitudeDir = " ";
        private float Altitude = 0;
        private float Speed = 0;
        private String sDate = " ";
        private String sTime = " ";
        private String sFix = "No valid Fix";

        private Tokenizer tokenizer;
        private String sen_type = " ";

        public GPS() {

        }

        public void parseNMEA(String sentence) {
            tokenizer = new Tokenizer(sentence);
            sen_type = tokenizer.next();

            if (sen_type.endsWith("$GPGGA")) {
                setTime(tokenizer.next()); //UTC Time HHMMSS
                setLatitude(tokenizer.next()); //Latitude
                setLatitudeDir(tokenizer.next()); //Latitude direction
                setLongitude(tokenizer.next()); //Longitude
                setLongitudeDir(tokenizer.next()); //Longitude direction
                if (tokenizer.next().endsWith("0")) { //Fix 0:No Fix >0:Fix OK
                    setFix("No valid Fix");
                }
                tokenizer.next(); //Satellites used
                tokenizer.next(); //HDOP
                setAltitude(tokenizer.next()); //Altitude in meters
                //dalej nie ma juz nic ciekawego
            }

            if (sen_type.endsWith("$GPGSA")) {

                tokenizer.next(); //Mode1 M:Manual A:Auto
                sen_type = tokenizer.next(); //Mode2 1:No Fix 2:2D 3:3D

                if (sen_type.endsWith("1")) {
                    setFix("No valid Fix");
                }

                if (sen_type.endsWith("2")) {
                    setFix("2D");
                }

                if (sen_type.endsWith("3")) {
                    setFix("3D");
                }
                //dalej nie ma nic ciekawego
            }

            if (sen_type.endsWith("$GPRMC")) {
                setTime(tokenizer.next()); //UTC Time HHMMSS

                sen_type = tokenizer.next(); //A:Data valid V:Data not valid

                if (sen_type.endsWith("V")) {
                    setFix("No valid Fix");
                }

                if (sen_type.endsWith("A")) {
                    setFix("2D");
                }

                setLatitude(tokenizer.next()); //Latitude
                setLatitudeDir(tokenizer.next()); //Latitude direction
                setLongitude(tokenizer.next()); //Longitude
                setLongitudeDir(tokenizer.next()); //Longitude direction
                setSpeed(tokenizer.next()); //Speed in knots
                tokenizer.next(); //Course in degrees
                setDate(tokenizer.next()); //Date DDMMYY
                //dalej nic ciekawego
            }

        }

        public void setLatitude(String lat) {
            sLatitude = lat;
        }

        public void setLatitudeDir(String dir) {
            sLatitudeDir = dir;
        }

        public void setLongitude(String lon) {
            sLongitude = lon;
        }

        public void setLongitudeDir(String dir) {
            sLongitudeDir = dir;
        }

        public void setAltitude(String alt) {
            Altitude = Float.valueOf(alt).floatValue();
        }

        public void setSpeed(String speed) {
            Speed = Float.valueOf(speed).floatValue();
        }

        public void setDate(String date) {
            sDate = date;
        }

        public void setTime(String time) {
            sTime = time;
        }

        public void setFix(String fix) {
            sFix = fix;
        }

        public int getLatDegrees() {
            int latD = 0;

            latD = Integer.parseInt(sLatitude.substring(0, 2));
            return latD;
        }

        public float getLatMinutes() {
            float latM = 0f;
            
            latM = Float.parseFloat(sLatitude.substring(2, sLatitude.length() - 1));
            return latM;
        }

        public String getLatDir() {
            return sLatitudeDir;
        }

        public float getLatDD() {
            float latDD = 0f;
            float latD = getLatDegrees();
            float latM = getLatMinutes();

            latDD = latD + (latM / 60f);

            latDD = (float)(int)(((latDD)+0.000005f)*100000.0f)/100000.0f;
            return latDD;
        }

        public int getLonDegrees() {
            int lonD = 0;

            lonD = Integer.parseInt(sLongitude.substring(0, 3));
            return lonD;
        }

        public float getLonMinutes() {
            float lonM = 0f;

            lonM = Float.parseFloat(sLongitude.substring(3, sLongitude.length() - 1));
            return lonM;
        }

        public String getLonDir() {
            return sLongitudeDir;
        }

        public float getLonDD() {
            float lonDD = 0f;
            float lonD = getLonDegrees();
            float lonM = getLonMinutes();

            lonDD = lonD + (lonM / 60f);

            lonDD = (float)(int)(((lonDD)+0.000005f)*100000.0f)/100000.0f;
            return lonDD;
        }

        public float getAltitude() {
            return Altitude;
        }

        public float getSpeed() {
            return (float)(int)(((Speed * 1.852f)+0.005f)*100.0f)/100.0f;
        }

        public String getDate() {
            return sDate;
        }

        public String getTime() {
            return sTime;
        }

        public String getFix() {
            return sFix;
        }

        public boolean getFixValid() {

            if (sFix.equalsIgnoreCase("no valid fix")) {
                return false;
            }else {
                return true;
            }
        }
    }
    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc=" Class: DataSender">
    public final class DataSender {
        private String UID;

        public DataSender() {
            genUID();
        }

        // <editor-fold defaultstate="collapsed" desc=" Method: updateScreen()">
        public void updateScreen() {


            //while (gps.getFixValid()) {
            getStringItemMainLat().setText(String.valueOf(gps.getLatDegrees()) + "\u00B0" + String.valueOf(gps.getLatMinutes()) + "\' " + gps.getLatDir() + "\n");
            getStringItemMainLon().setText(String.valueOf(gps.getLonDegrees()) + "\u00B0" + String.valueOf(gps.getLonMinutes()) + "\' " + gps.getLonDir() + "\n");
            getStringItemMainAlt().setText(String.valueOf(gps.getAltitude()) + " m\n");
            getStringItemMainSpeed().setText(String.valueOf(gps.getSpeed()) + " km/h\n");
            getStringItemMainFix().setText(gps.getFix() + "\n");

            //try {
            //  Thread.sleep(1000);

            //     } catch (InterruptedException ex) {
            //ex.printStackTrace();
            //      }

            //    }
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc=" Method: sendData()">
        public void sendData() {
            String url = prefs.getServerAddress();
            String time = gps.getTime();
            String date = gps.getDate();
            String lat = String.valueOf(gps.getLatDD());
            String lat_d = gps.getLatDir();
            String lon = String.valueOf(gps.getLonDD());
            String lon_d = gps.getLonDir();
            int resp = 0;

            UID = checkLength(UID, 20);
            date = checkLength(date, 6);
            time = checkLength(time, 6);
            lat = checkLength(lat, 9);
            lat_d = checkLength(lat_d, 1);
            lon = checkLength(lon, 10);
            lon_d = checkLength(lon_d, 1);

            setStatus("Wysyłanie...");

            try {
                do {
                    HttpConnection httpConn = (HttpConnection) Connector.open(url + "?uid=" + UID + "&date=" + date + "&time=" + time + "&lat=" + lat + "&lat_d=" + lat_d + "&lon=" + lon + "&lon_d=" + lon_d);
                    resp = httpConn.getResponseCode();
                    //httpConn.close();

                } while (resp != 200); //sprawdzamy czy http response = 200 czyli OK

            } catch (IOException ex) {
                setStatus("Błąd HTTP!");
                ex.printStackTrace();
            }
            setStatus("Wysłano.");

        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc=" Method: checkLength(str, len)">
        public String checkLength(String str, int len) {

            if (str.length() > len) {
                str = str.substring(0, len);
            }

            return str;
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc=" Method: genUID">
        public void genUID() {
            Random rnd = new Random();
            long lUID = 0;

            do {
                rnd.setSeed(System.currentTimeMillis());
                lUID = rnd.nextLong();
            } while (lUID == 0); //generujemy tak zeby nie było zero


            if (lUID < 0) {
                lUID = lUID * -1; //tylko dodatnie liczby
            }

            UID = String.valueOf(lUID);
        }
        // </editor-fold>

    }
    // </editor-fold>


}

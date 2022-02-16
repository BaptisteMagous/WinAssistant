package com.winassistant.controller;

import com.sun.jna.Native;
import com.winassistant.Application;
import com.winassistant.data.DataManager;
import com.winassistant.windowsFinder.StringByReference;
import com.winassistant.windowsFinder.WindowsFinder;
import com.winassistant.data.Article;
import com.winassistant.widget.MainMenu;
import com.winassistant.widget.UserProfile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javafx.scene.text.Text;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Controller {

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    @FXML
    protected void onMinimiseButtonClick() {
        Application.main.minimise();
    }

    @FXML
    protected void onMaximiseButtonClick() {
        Application.main.maximise();
    }

    @FXML
    protected void goToMainMenu() throws IOException {
        Application.main.loadContent(new MainMenu().getContent());
    }

    @FXML
    protected void goToUserProfile() throws IOException {
        Application.main.loadContent(new UserProfile(/*app.getUser()*/).getContent()  );
    }

    @FXML
    protected void useShortcut(ActionEvent event) throws AWTException {
        WindowsFinder windowsFinder = WindowsFinder.INSTANCE;

        int interation = 0;

        do{
            WindowsFinder.ExampleCallbackImpl callback = new WindowsFinder.ExampleCallbackImpl();

            if(interation > 0) windowsFinder.EnumWindows(callback, new StringByReference(DataManager.getSettings().getWindowsName()));
            if(interation > 0) System.out.println(DataManager.getSettings().getWindowsName());
            Native.setLastError(0);
            windowsFinder.ShowWindow(Application.windowsHandle, WindowsFinder.SW_SHOWMAXIMIZED);
        }
        while(Native.getLastError() != 0 && interation++ < 5);

        Node node = (Node) event.getSource();
        Article data = (Article) node.getUserData();

        if(Native.getLastError() == 0){
            windowsFinder.SetWindowPos(Application.windowsHandle, WindowsFinder.WINDOW_TOPMOST, 0, 0, 0, 0, 43);
            Robot robot = new Robot();

            for(int i = 0; i < data.getShortcuts().length; i++){
                if(data.getShortcuts()[i].isCtrl()) robot.keyPress(KeyEvent.VK_CONTROL);
                if(data.getShortcuts()[i].isShift()) robot.keyPress(KeyEvent.VK_SHIFT);
                if(data.getShortcuts()[i].isAlt()) robot.keyPress(KeyEvent.VK_ALT);

                robot.keyPress(data.getShortcuts()[i].getKey());
                robot.keyRelease(data.getShortcuts()[i].getKey());

                if(data.getShortcuts()[i].isCtrl()) robot.keyRelease(KeyEvent.VK_CONTROL);
                if(data.getShortcuts()[i].isShift()) robot.keyRelease(KeyEvent.VK_SHIFT);
                if(data.getShortcuts()[i].isAlt()) robot.keyRelease(KeyEvent.VK_ALT);
            }

            windowsFinder.SetWindowPos(Application.windowsHandle, WindowsFinder.WINDOW_NONTOPMOST, 0, 0, 0, 0, 43);
        }

        else{
            ((Text) node.getParent().lookup("#errorTxt")).setText("Handle : " + Application.windowsHandle + " - Error : " + Native.getLastError() );
        }



    }
}
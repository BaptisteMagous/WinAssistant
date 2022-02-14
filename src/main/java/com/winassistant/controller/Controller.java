package com.winassistant.controller;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.winassistant.Application;
import com.winassistant.data.DataManager;
import com.winassistant.WindowsFinder;
import com.winassistant.widget.Article;
import com.winassistant.widget.MainMenu;
import com.winassistant.widget.UserProfile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import java.awt.*;
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
        //Pointer hWnd = WindowsFinder.INSTANCE.FindWindow(null, "test.txt - Bloc-notes");
        Native.setLastError(0);

        Pointer windowsHandle = WindowsFinder.INSTANCE.FindWindow(null, DataManager.getSettings().getWindowsName());
        //if(windowsHandle != null) WindowsFinder.INSTANCE.SetFocus(windowsHandle);
        if(windowsHandle != null) WindowsFinder.INSTANCE.SetWindowPos(windowsHandle, WindowsFinder.WINDOW_TOPMOST, 0, 0, 0, 0, 43);

        int error = Native.getLastError();

        Node node = (Node) event.getSource();

        ((Text) node.getParent().lookup("#errorTxt")).setText("Handle : " + windowsHandle + " - Error : " + error);

        Article data = (Article) node.getUserData();

        Robot robot = new Robot();
        robot.keyPress(data.getKey());
    }
}
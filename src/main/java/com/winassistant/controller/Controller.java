package com.winassistant.controller;

import com.sun.jna.Native;
import com.winassistant.Application;
import com.winassistant.data.DataManager;
import com.winassistant.windowsFinder.StringByReference;
import com.winassistant.windowsFinder.WindowsFinder;
import com.winassistant.data.Article;
import com.winassistant.widget.MainMenu;
import com.winassistant.widget.Options;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
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
    protected void onMaximiseButtonClick(ActionEvent event) {
        Application.main.maximise();
    }

    @FXML
    protected void goToMainMenu() throws IOException {
        Application.main.loadContent(new MainMenu().getContent());
    }

    @FXML
    protected void goToUserProfile() throws IOException {
        Application.main.loadContent(new Options(/*app.getUser()*/).getContent()  );
    }

    @FXML
    protected void search(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();

        Application.main.loadContent(new MainMenu(((TextField) node.getParent().lookup("#searchInput")).getText()).getContent());
    }

    @FXML
    protected void changeOrientation(){
        Application.orientation = !Application.orientation;
        Application.main.positionStage();
    }

}
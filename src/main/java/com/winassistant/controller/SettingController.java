package com.winassistant.controller;

import com.winassistant.Application;
import com.winassistant.data.DataManager;
import com.winassistant.widget.MainMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

import static javafx.application.Platform.exit;

public class SettingController {

    @FXML
    protected void quit(ActionEvent event) throws IOException {
        exit();
    }

    @FXML
    protected void manualLoad(ActionEvent event) throws IOException {
        DataManager.save();
        DataManager.load();

        Button button = (Button) event.getSource();
        button.setText("Rechargé !");
    }

    @FXML
    protected void changeWindowsName(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        DataManager.getSettings().setWindowsName(((TextField) node.getParent().lookup("#windowsName")).getText());
        DataManager.save();

        Application.main.loadContent(new MainMenu().getContent());
    }

}
package com.winassistant.widget;

import com.winassistant.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;

public class ContentPage {
    protected Parent content;

    protected String fxml;

    public Parent getContent(){
        return content;
    }

    protected void loadContent() throws IOException {
        content = new FXMLLoader(Application.class.getResource(fxml)).load();
    }

}

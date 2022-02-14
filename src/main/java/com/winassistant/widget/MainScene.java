package com.winassistant.widget;

import com.winassistant.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;

public class MainScene {

    Scene mainScene, miniScene;

    Pane contentPane;

    public MainScene() throws IOException {

        FXMLLoader fxmlMainScene = new FXMLLoader(Application.class.getResource("main-scene.fxml"));
        FXMLLoader fxmlMiniScene = new FXMLLoader(Application.class.getResource("mini-scene.fxml"));

        mainScene = new Scene(fxmlMainScene.load());
        miniScene = new Scene(fxmlMiniScene.load());
        mainScene.setFill(Color.TRANSPARENT);
        miniScene.setFill(Color.TRANSPARENT);


        contentPane = (Pane) mainScene.lookup("#content");
    }

    public Scene getMainScene(){
        return mainScene;
    }

    public Scene getMiniScene(){
        return miniScene;
    }

    public void loadContent(Parent content){
        contentPane.getChildren().clear();
        contentPane.getChildren().add(content);
    }
}

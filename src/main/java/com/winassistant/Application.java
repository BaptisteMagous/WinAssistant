package com.winassistant;

import com.sun.jna.Pointer;
import com.winassistant.data.DataManager;
import com.winassistant.widget.MainMenu;
import com.winassistant.widget.MainScene;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Application extends javafx.application.Application {

    public static String valueToSearch = "Discord";
    public static Pointer windowsHandle = null;

    public static Application main;
    MainScene mainScene;
    Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        mainScene = new MainScene();

        this.stage.initStyle(StageStyle.TRANSPARENT);
        this.stage.setAlwaysOnTop(true);
        this.stage.setTitle("WinAssistant");

        stage.show();

        maximise();
        loadContent(new MainMenu().getContent());

        Application.main = this;
    }

    public void maximise(){
        stage.setScene(mainScene.getMainScene());

        positionStage();
    }
    public void minimise(){
        stage.setScene(mainScene.getMiniScene());

        positionStage();
    }

    public void positionStage(){
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        stage.setX(bounds.getWidth() - stage.getWidth()-10);
        stage.setY(bounds.getHeight() - stage.getHeight()-10);
    }


    public void loadContent(Parent content){
        mainScene.loadContent(content);
        stage.hide();
        stage.show();
        positionStage();
    }

    public static void main(String[] args) {

        DataManager.createFile();
        DataManager.load();

        launch();
    }
}
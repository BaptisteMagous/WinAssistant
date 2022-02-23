package com.winassistant.controller;

import com.sun.jna.Native;
import com.winassistant.Application;
import com.winassistant.data.Article;
import com.winassistant.data.Data;
import com.winassistant.data.DataManager;
import com.winassistant.data.Shortcut;
import com.winassistant.widget.EditArticleWidget;
import com.winassistant.widget.MainMenu;
import com.winassistant.widget.Options;
import com.winassistant.windowsFinder.StringByReference;
import com.winassistant.windowsFinder.WindowsFinder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ArticleController {


    @FXML
    protected void useShortcut(ActionEvent event) throws AWTException {
        WindowsFinder windowsFinder = WindowsFinder.INSTANCE;

        int interation = 0;

        System.out.println("Looking for " + DataManager.getSettings().getWindowsName());

        do{
            System.out.println("Creating callback ...");
            WindowsFinder.ExampleCallbackImpl callback = new WindowsFinder.ExampleCallbackImpl();
            System.out.println("Creating callback DONE");

            Native.setLastError(0);
            System.out.println("Enumerating Windows ...");
            windowsFinder.EnumWindows(callback, new StringByReference(DataManager.getSettings().getWindowsName()));
            System.out.println("Enumerating Windows DONE");
            System.out.println("Found : " + Application.windowsHandle);
            System.out.println("Maximizing windows ...");
            windowsFinder.ShowWindow(Application.windowsHandle, WindowsFinder.SW_SHOWMAXIMIZED);
            System.out.println("Maximizing windows DONE");
        }
        while(Native.getLastError() != 0 && interation++ < 5);

        Node node = (Node) event.getSource();
        Article data = (Article) node.getUserData();

        if(Native.getLastError() == 0){
            System.out.println("Focusing windows ...");
            windowsFinder.SetWindowPos(Application.windowsHandle, WindowsFinder.WINDOW_TOPMOST, 0, 0, 0, 0, 43);
            System.out.println("Focusing windows DONE");


            System.out.println("Creating new Robot...");
            Robot robot = new Robot();
            System.out.println("Creating new Robot DONE");

            for(int i = 0; i < data.getShortcuts().length; i++){
                System.out.println("Pressing Shortcut " + String.valueOf(i));
                if(data.getShortcuts()[i].isCtrl()) robot.keyPress(KeyEvent.VK_CONTROL);
                if(data.getShortcuts()[i].isShift()) robot.keyPress(KeyEvent.VK_SHIFT);
                if(data.getShortcuts()[i].isAlt()) robot.keyPress(KeyEvent.VK_ALT);

                int key;

                switch (data.getShortcuts()[i].getKey()){
                    case "F1":
                        key = KeyEvent.VK_F1;
                        break;
                    case "F2":
                        key = KeyEvent.VK_F2;
                        break;
                    case "F3":
                        key = KeyEvent.VK_F3;
                        break;
                    case "F4":
                        key = KeyEvent.VK_F4;
                        break;
                    case "F5":
                        key = KeyEvent.VK_F5;
                        break;
                    case "F6":
                        key = KeyEvent.VK_F6;
                        break;
                    case "F7":
                        key = KeyEvent.VK_F7;
                        break;
                    case "F8":
                        key = KeyEvent.VK_F8;
                        break;
                    case "F9":
                        key = KeyEvent.VK_F9;
                        break;
                    case "F10":
                        key = KeyEvent.VK_F10;
                        break;
                    case "F11":
                        key = KeyEvent.VK_F11;
                        break;
                    case "F12":
                        key = KeyEvent.VK_F12;
                        break;
                    case "Alt":
                        key = KeyEvent.VK_ALT;
                        break;
                    default:
                        key = data.getShortcuts()[i].getKey().charAt(0);
                        break;
                }
                robot.keyPress(key);
                robot.keyRelease(key);

                if(data.getShortcuts()[i].isCtrl()) robot.keyRelease(KeyEvent.VK_CONTROL);
                if(data.getShortcuts()[i].isShift()) robot.keyRelease(KeyEvent.VK_SHIFT);
                if(data.getShortcuts()[i].isAlt()) robot.keyRelease(KeyEvent.VK_ALT);
                System.out.println("Releasing Shortcut " + String.valueOf(i));
            }

            System.out.println("Un-Focusing windows ...");
            windowsFinder.SetWindowPos(Application.windowsHandle, WindowsFinder.WINDOW_NONTOPMOST, 0, 0, 0, 0, 43);
            System.out.println("Un-Focusing windows DONE");
        }

        else{
            ((Text) node.getParent().lookup("#errorTxt")).setText("Handle : " + Application.windowsHandle + " - Error : " + Native.getLastError() );
        }
    }

    @FXML
    protected void addShortcut(ActionEvent event) throws IOException {
        updateArticle(event);

        Node node = (Node) event.getSource();
        int articleId = (int) node.getUserData();

        if(articleId == -1) {
            Application.draftArticle.addShortcut(new Shortcut(false, false, false, "A"));
        }
        else DataManager.get(articleId).addShortcut(new Shortcut(false, false, false, "A"));

        DataManager.save();

        Application.main.loadContent(new EditArticleWidget(articleId).getContent());
    }

    @FXML
    protected void saveArticle(ActionEvent event) throws IOException {
        updateArticle(event);

        Node node = (Node) event.getSource();
        int articleId = (int) node.getUserData();

        if(articleId == -1) {
            DataManager.addArticle(Application.draftArticle);
            Application.draftArticle = new Article("", "", new Shortcut[0]);
        }

        DataManager.save();

        Application.main.loadContent(new Options().getContent());
    }

    @FXML
    protected void updateArticle(ActionEvent event){
        Node node = (Node) event.getSource();
        int articleId = (int) node.getUserData();

        if((TextField) node.getParent().lookup("#articleName") != null){

            if(articleId == -1) {
                Application.draftArticle.setTitle(((TextField) node.getParent().lookup("#articleName")).getText());
            }
            else DataManager.get(articleId).setTitle(((TextField) node.getParent().lookup("#articleName")).getText());


        }

        DataManager.save();

    }

    @FXML
    protected void updateName(ActionEvent event){
        Node node = (Node) event.getSource();
        int articleId = (int) node.getUserData();

        if((TextField) node.getParent().lookup("#articleName") != null){

            if(articleId == -1) {
                Application.draftArticle.setTitle(((TextField) node.getParent().lookup("#articleName")).getText());
            }
            else DataManager.get(articleId).setTitle(((TextField) node.getParent().lookup("#articleName")).getText());


        }
    }
}

package com.winassistant.controller;

import com.sun.jna.Native;
import com.winassistant.Application;
import com.winassistant.data.Article;
import com.winassistant.data.DataManager;
import com.winassistant.data.Shortcut;
import com.winassistant.widget.EditArticleWidget;
import com.winassistant.widget.Options;
import com.winassistant.windowsFinder.StringByReference;
import com.winassistant.windowsFinder.WindowsFinder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ShortcutController {

    @FXML
    protected void updateCtrl(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        int articleId = ((Integer[]) node.getUserData())[0];
        int shortcutId = ((Integer[]) node.getUserData())[1];

        if(articleId == -1) {
            Application.draftArticle.getShortcuts()[shortcutId].setCtrl(((CheckBox) event.getSource()).isSelected());
        }
        else DataManager.get(articleId).getShortcuts()[shortcutId].setCtrl(((CheckBox) event.getSource()).isSelected());

    }
    @FXML
    protected void updateShift(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        int articleId = ((Integer[]) node.getUserData())[0];
        int shortcutId = ((Integer[]) node.getUserData())[1];

        if(articleId == -1) {
            Application.draftArticle.getShortcuts()[shortcutId].setShift(((CheckBox) event.getSource()).isSelected());
        }
        else DataManager.get(articleId).getShortcuts()[shortcutId].setShift(((CheckBox) event.getSource()).isSelected());

    }
    @FXML
    protected void updateAlt(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        int articleId = ((Integer[]) node.getUserData())[0];
        int shortcutId = ((Integer[]) node.getUserData())[1];

        if(articleId == -1) {
            Application.draftArticle.getShortcuts()[shortcutId].setAlt(((CheckBox) event.getSource()).isSelected());
        }
        else DataManager.get(articleId).getShortcuts()[shortcutId].setAlt(((CheckBox) event.getSource()).isSelected());

    }

    @FXML
    protected void deleteShortcut(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        int articleId = ((Integer[]) node.getUserData())[0];
        int shortcutId = ((Integer[]) node.getUserData())[1];

        Shortcut[] shortcuts;

        if(articleId == -1) shortcuts = Application.draftArticle.getShortcuts();
        else shortcuts = DataManager.get(articleId).getShortcuts();

        // Copy shortcut array without the deleted one
        Shortcut[] newShortcuts = new Shortcut[shortcuts.length-1];

        // Copy the elements from starting till index
        // from original array to the other array
        System.arraycopy(shortcuts, 0, newShortcuts, 0, shortcutId);

        // Copy the elements from index + 1 till end
        // from original array to the other array
        System.arraycopy(shortcuts, shortcutId + 1,
                newShortcuts, shortcutId,
                shortcuts.length - shortcutId - 1);


        if(articleId == -1) Application.draftArticle.setShortcuts(newShortcuts);
        else DataManager.get(articleId).setShortcuts(newShortcuts);

        Application.main.loadContent(new EditArticleWidget(articleId).getContent());
    }

    @FXML
    protected void updateKey(ActionEvent event){

        Node node = (Node) event.getSource();
        int articleId = ((Integer[]) node.getUserData())[0];
        int shortcutId = ((Integer[]) node.getUserData())[1];

        System.out.println((String) ((ChoiceBox) event.getSource()).getValue());

        if(articleId == -1) Application.draftArticle.getShortcuts()[shortcutId].setKey((String) ((ChoiceBox) event.getSource()).getValue());

        else DataManager.get(articleId).getShortcuts()[shortcutId].setKey((String) ((ChoiceBox) event.getSource()).getValue());
    }
}

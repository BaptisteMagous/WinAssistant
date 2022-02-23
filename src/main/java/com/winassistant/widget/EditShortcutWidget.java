package com.winassistant.widget;

import com.winassistant.Application;
import com.winassistant.data.Article;
import com.winassistant.data.DataManager;
import com.winassistant.data.Shortcut;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class EditShortcutWidget extends ContentPage {
    public EditShortcutWidget(int articleId, int shortcutId) throws IOException {
        fxml = "shortcut/edit-shortcut.fxml";

        loadContent();

        Article article = new Article("", "", new Shortcut[0]);

        if(articleId == -1)
            article = Application.draftArticle;
        else if(DataManager.get(articleId) != null)
            article = DataManager.get(articleId);
        else{
            System.out.println("Article " + articleId + " can't be found");
        }

        Shortcut shortcut = article.getShortcuts()[shortcutId];

        ((CheckBox) getContent().lookup("#ctrl")).setSelected(shortcut.isCtrl());
        ((CheckBox) getContent().lookup("#shift")).setSelected(shortcut.isShift());
        ((CheckBox) getContent().lookup("#alt")).setSelected(shortcut.isAlt());

        getContent().lookup("#ctrl").setUserData(new Integer[]{articleId, shortcutId});
        getContent().lookup("#shift").setUserData(new Integer[]{articleId, shortcutId});
        getContent().lookup("#alt").setUserData(new Integer[]{articleId, shortcutId});
        getContent().lookup("#key").setUserData(new Integer[]{articleId, shortcutId});
        getContent().lookup("#deleteShortcut").setUserData(new Integer[]{articleId, shortcutId});

        ChoiceBox choiceBox = (ChoiceBox) getContent().lookup("#key");

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Traverse the string using for loop
        for (int i = 0; i < characters.length(); i++) {
            choiceBox.getItems().add(characters.charAt(i));
        }
        choiceBox.getItems().add("F1");
        choiceBox.getItems().add("F2");
        choiceBox.getItems().add("F3");
        choiceBox.getItems().add("F4");
        choiceBox.getItems().add("F5");
        choiceBox.getItems().add("F6");
        choiceBox.getItems().add("F7");
        choiceBox.getItems().add("F8");
        choiceBox.getItems().add("F9");
        choiceBox.getItems().add("F10");
        choiceBox.getItems().add("F11");
        choiceBox.getItems().add("F12");
        choiceBox.getItems().add("Alt");

        choiceBox.getSelectionModel().select(Integer.valueOf(shortcut.getKey())-65);

    }
}

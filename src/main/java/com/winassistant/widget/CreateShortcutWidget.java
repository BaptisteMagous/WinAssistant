package com.winassistant.widget;

import com.winassistant.data.Article;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.io.IOException;

public class CreateShortcutWidget extends ContentPage {
    public CreateShortcutWidget(int articleId) throws IOException {
        fxml = "shortcut/create-shortcut.fxml";

        loadContent();
        ((Button) getContent().lookup("#create")).setUserData(articleId);
    }
}

package com.winassistant.widget;

import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class ArticleWidget extends ContentPage {

    public ArticleWidget(Article article) throws IOException {
        fxml = "article.fxml";

        loadContent();

        ((Text) content.lookup("#title")).setText(article.getTitle());
        ((Text) content.lookup("#shortcut")).setText(String.valueOf(article.getKey()));
        ((Button) content.lookup("#useShortcut")).setUserData(article);

    }
}

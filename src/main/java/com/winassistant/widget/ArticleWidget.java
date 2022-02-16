package com.winassistant.widget;

import com.winassistant.data.Article;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class ArticleWidget extends ContentPage {

    public ArticleWidget(Article article) throws IOException {
        fxml = "article.fxml";

        loadContent();

        ((Text) content.lookup("#title")).setText(article.getTitle());

        String shortcuts = "";
        for(int i = 0; i < article.getShortcuts().length; i++){
            shortcuts += article.getShortcuts()[i].toString();
        }

        ((Text) content.lookup("#shortcut")).setText(shortcuts);
        ((Button) content.lookup("#useShortcut")).setUserData(article);

    }
}

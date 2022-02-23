package com.winassistant.widget;

import com.winassistant.data.Article;
import com.winassistant.data.DataManager;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class ArticleAdminWidget extends ContentPage {

    public ArticleAdminWidget(int articleId) throws IOException {
        fxml = "article/article-admin.fxml";

        loadContent();

        Article article = DataManager.get(articleId);

        ((Text) content.lookup("#title")).setText(article.getTitle());

        String shortcuts = "";
        for(int i = 0; i < article.getShortcuts().length; i++){
            shortcuts += article.getShortcuts()[i].toString();
        }

        ((Text) content.lookup("#shortcut")).setText(shortcuts);
        ((Button) content.lookup("#edit")).setUserData(articleId);
        ((Button) content.lookup("#delete")).setUserData(articleId);
    }
}

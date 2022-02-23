package com.winassistant.widget;

import com.winassistant.data.Article;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class NoArticleWidget extends ContentPage {

    public NoArticleWidget() throws IOException {
        fxml = "article/no-article.fxml";

        loadContent();
    }
}

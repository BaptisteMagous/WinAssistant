package com.winassistant.widget;

import java.io.IOException;

public class CreateArticleWidget extends ContentPage {

    public CreateArticleWidget() throws IOException {
        fxml = "article/edit-article.fxml";

        loadContent();
    }
}

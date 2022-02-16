package com.winassistant.widget;

import com.winassistant.data.Article;
import com.winassistant.data.DataManager;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainMenu extends ContentPage{

    public MainMenu() throws IOException {
        fxml = "main-menu.fxml";
        loadContent();

        VBox articlesPane = new VBox();
        articlesPane.setSpacing(10);

        Article[] articles = DataManager.getPreferences(0/*Application.userCode*/);
        if(articles.length == 0) articles = DataManager.getDefaultArticles();


        for(int i = 0; i < articles.length; i++){
            articlesPane.getChildren().add(new ArticleWidget(articles[i]).getContent());
        }

        ((ScrollPane) content.lookup("#articles")).setContent(articlesPane);
    }

    public MainMenu(String search) throws IOException {
        this();

        Article[] articles = DataManager.getPreferences(0/*Application.userCode*/);
        if(articles.length == 0) articles = DataManager.getDefaultArticles();


        for(int i = 0; i < articles.length; i++){
            articlesPane.getChildren().add(new ArticleWidget(articles[i]).getContent());
        }

        ((ScrollPane) content.lookup("#articles")).setContent(articlesPane);


    }
}

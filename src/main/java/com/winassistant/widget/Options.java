package com.winassistant.widget;

import com.winassistant.data.DataManager;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Options extends ContentPage {
    public Options() throws IOException {
        fxml = "options.fxml";
        loadContent();

        ((TextField) content.lookup("#windowsName")).setText(DataManager.getSettings().getWindowsName());



        VBox articlesPane = new VBox();
        articlesPane.setSpacing(10);

        Integer[] articlesId = DataManager.getArticlesId();

        for(int i = 0; i < articlesId.length; i++){
            articlesPane.getChildren().add(new ArticleAdminWidget(articlesId[i]).getContent());
        }

        ((ScrollPane) getContent().lookup("#articleList")).setContent(articlesPane);
    }
}

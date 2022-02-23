package com.winassistant.widget;

import com.winassistant.Application;
import com.winassistant.data.Article;
import com.winassistant.data.DataManager;
import com.winassistant.data.Shortcut;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class EditArticleWidget extends ContentPage {

    public EditArticleWidget(int articleId) throws IOException {
        fxml = "article/edit-article.fxml";

        loadContent();

        Article reference = new Article("", "", new Shortcut[0]);

        if(articleId == -1)
            reference = Application.draftArticle;
        else if(DataManager.get(articleId) != null)
            reference = DataManager.get(articleId);
        else{
            System.out.println("Article " + articleId + " can't be found");
        }

        ((TextField) getContent().lookup("#articleName")).setText(reference.getTitle());
        ((TextField) getContent().lookup("#articleName")).setUserData(articleId);
        ((Button) getContent().lookup("#validate")).setUserData(articleId);


        VBox shortcutsPane = new VBox();
        shortcutsPane.setSpacing(10);
        shortcutsPane.setAlignment(Pos.TOP_CENTER);
        shortcutsPane.setPrefWidth(300);

        for(int i = 0; i < reference.getShortcuts().length; i++){
            shortcutsPane.getChildren().add(new EditShortcutWidget(articleId, i).getContent());
        }

        shortcutsPane.getChildren().add(new CreateShortcutWidget(articleId).getContent());

        ((ScrollPane) getContent().lookup("#shortcuts")).setContent(shortcutsPane);
    }
}

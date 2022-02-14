package com.winassistant.widget;

import com.winassistant.data.DataManager;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UserProfile extends ContentPage {
    public UserProfile() throws IOException {
        fxml = "user-menu.fxml";
        loadContent();

        ((TextField) content.lookup("#windowsName")).setText(DataManager.getSettings().getWindowsName());
    }
}

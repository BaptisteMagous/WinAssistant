package com.winassistant.data;

import java.util.ArrayList;
import java.util.Arrays;

public class Article {
    private String title;
    private String description;
    private Shortcut[] shortcuts;

    public Article(String title, String description, Shortcut[] shortcuts) {
        this.title = title;
        this.description = description;
        this.shortcuts = shortcuts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Shortcut[] getShortcuts() {
        return shortcuts;
    }

    public void setShortcuts(Shortcut[] shortcuts) {
        this.shortcuts = shortcuts;
    }

    public void addShortcut(Shortcut shortcut) {
        ArrayList<Shortcut> newShortcuts = new ArrayList<Shortcut>(Arrays.asList(this.getShortcuts()));
        newShortcuts.add(shortcut);
        this.setShortcuts(newShortcuts.toArray(new Shortcut[0]));
    }
}

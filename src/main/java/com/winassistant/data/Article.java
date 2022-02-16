package com.winassistant.data;

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
}

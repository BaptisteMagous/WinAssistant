package com.winassistant.widget;

public class Article {
    private String title;
    private String description;
    private boolean ctrl;
    private boolean shift;
    private boolean alt;
    private char key;

    public Article(String title, String description, boolean ctrl, boolean shift, boolean alt, char key) {
        this.title = title;
        this.description = description;
        this.ctrl = ctrl;
        this.shift = shift;
        this.alt = alt;
        this.key = key;
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

    public boolean isCtrl() {
        return ctrl;
    }

    public void setCtrl(boolean ctrl) {
        this.ctrl = ctrl;
    }

    public boolean isShift() {
        return shift;
    }

    public void setShift(boolean shift) {
        this.shift = shift;
    }

    public boolean isAlt() {
        return alt;
    }

    public void setAlt(boolean alt) {
        this.alt = alt;
    }

    public char getKey() {
        return key;
    }

    public void setKey(char key) {
        this.key = key;
    }
}

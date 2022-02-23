package com.winassistant.data;

public class Shortcut {

    private boolean ctrl;
    private boolean shift;
    private boolean alt;
    private String key;

    public Shortcut(boolean ctrl, boolean shift, boolean alt, String key) {
        this.ctrl = ctrl;
        this.shift = shift;
        this.alt = alt;
        this.key = key;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return (isCtrl()?"Ctrl + ":"")
             + (isAlt()?"Alt + ":"")
             + (isShift()?"Shift + ":"")
             + getKey() + "\n";
    }
}

package com.winassistant.data;

public class SettingsData {
    private String saveFile, windowsName;

    public SettingsData(String saveFile, String windowsName) {
        this.saveFile = saveFile;
        this.windowsName = windowsName;
    }

    public String getSaveFile() {
        return saveFile;
    }

    public String getWindowsName() {
        return windowsName;
    }

    public void setSaveFile(String saveFile) {
        this.saveFile = saveFile;
    }

    public void setWindowsName(String windowsName) {
        this.windowsName = windowsName;
    }
}

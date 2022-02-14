package com.winassistant.data;

import com.winassistant.widget.Article;

import java.util.HashMap;
import java.util.Map;

public class Data {

    public Map<Integer, Article> articles = new HashMap<Integer, Article>();

    public Map<Integer, Integer[]> preferences = new HashMap<Integer, Integer[]>();

    public SettingsData settings = new SettingsData("test.json", "nouveau 2 - Notepad++");
}

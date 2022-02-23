package com.winassistant.data;

import com.google.gson.Gson;
import com.winassistant.windowsFinder.WindowsFinder;
import javafx.scene.control.TextField;
import org.apache.commons.io.IOUtils;

import java.io.*;


public class DataManager
{
    private static Data data = new Data();

    private static String save_file = data.settings.getSaveFile();

    public static Article[] getPreferences(Integer code){
        Integer[] userPrefences = data.preferences.get(code);
        if(userPrefences == null) return new Article[0];

        Article[] userArticles = new Article[userPrefences.length];
        for (int i = 0; i < userPrefences.length; i++) {
            userArticles[i] = data.articles.get(userPrefences[i]);
        }

        return userArticles;
    }

    public static Article[] getArticles(){
        return data.articles.values().toArray(new Article[0]);
    }

    public static Integer[] getArticlesId(){
        return data.articles.keySet().toArray(new Integer[0]);
    }

    public static Article get(Integer id){
        return data.articles.get(id);
    }

    public static void set(Integer id, Article article){
        data.articles.put(id, article);
    }

    public static void load(){
        try {
            String jsonData = IOUtils.toString(new FileInputStream( new File(save_file)));
            data = new Gson().fromJson(jsonData, Data.class);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save(){
        try {
            FileWriter writer = new FileWriter(save_file);
            writer.write(new Gson().toJson(data, Data.class));
            writer.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFile(){
        try {
            File myObj = new File(save_file);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                //System.out.println("File already exists.");
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Article[] getDefaultArticles() {
        return new Article[]{
                get(1),
                get(2),
                get(4),
                get(5)
        };
    }

    public static SettingsData getSettings(){
        return data.settings;
    }

    public static Article[] searchArticles(String search) {
        return data.articles.entrySet()
                .stream()
                .filter(e -> e.getValue().getTitle().contains(search))
                .map(e -> e.getValue())
                .toArray(Article[]::new);
    }

    public static void addArticle(Article article) {
        //The new id is :
        //                 (the maximum found id) + 1
        int id = data.articles.keySet().stream().max(Integer::compare).get() + 1;

        data.articles.put(id, article);

        save();
    }

    public static void deleteArticle(int articleId) {
        data.articles.remove(articleId);

        save();
    }
}

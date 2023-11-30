package com.akbar27.crud_laravel;

import com.google.gson.annotations.SerializedName;

public class Skill {
    private Integer id;
    private String name;

    @SerializedName("slug")
    private String Text;

    public Skill(String name, String text) {
        this.name = name;
        Text = text;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return Text;
    }

//    private int userId;
//    private int id;
//    private String title;
//
//    @SerializedName("body")
//    private String text;
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getText() {
//        return text;
//    }
}

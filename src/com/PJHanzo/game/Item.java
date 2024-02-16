package com.PJHanzo.game;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
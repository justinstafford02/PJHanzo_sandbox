package com.PJHanzo.util;

import com.PJHanzo.game.Item;
import com.PJHanzo.game.Location;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

class GsonUtil {

    public static final Gson gson;

    static {
        gson = new GsonBuilder()
                // Add any Gson configuration options if needed
                .create();
    }

    private static void readLocationJson() {
        try {
            Type LocationListType = new TypeToken<List<Location>>() {
            }.getType();
            List<Location> locationList = gson.fromJson(new FileReader("PC4-ProjectHanzo/resources/Location.json"), LocationListType);

            for (Location location : locationList) {
                System.out.println(location.getName());
                System.out.println(location.getDescription());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readItemJson() {
        try {
            Type itemListType = new TypeToken<List<Item>>() {
            }.getType();
            List<Item> itemList = gson.fromJson(new FileReader("PC4-ProjectHanzo/resources/Item.json"), itemListType);

            for (Item item : itemList) {
                System.out.println(item.getName());
                System.out.println(item.getDescription());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
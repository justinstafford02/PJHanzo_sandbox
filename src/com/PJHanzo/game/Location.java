package com.PJHanzo.game;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Location {

    @SerializedName("loc_name")
    private String name;

    @SerializedName("loc_description")
    private String description;

    @SerializedName("loc_items")
    private List<String> items;

    @SerializedName("exits")
    private Map<String, String> exits;

    @SerializedName("loc_npc")
    private List<String> NPCs;



    private Location(String name, String description, List<String> items, Map<String, String> exits, List<String> NPCs) {
        this.name = name;
        this.description = description;
        this.items = items;
        this.exits = exits;
        this.NPCs = NPCs;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getItemNames() {
        return items;
    }

    public Map<String, String> getExits() {
        return exits;
    }

    public List<String> getNPCNames() {
        return NPCs;
    }

    //method was built for checking if List<String> contains the NPC name passed into it as arg
    public boolean containsNPC(String name) {
        for (String npc : NPCs) {
            if (npc.equalsIgnoreCase(name)) {
//          returns true if npc name matches any case of npc name entered as arg
                return true;
            }

        }
        return false;
    }

//    take instance of game as arguement
    public void printNPCs(Game game) {
//        string builder to append
        StringBuilder result = new StringBuilder();
        for (String npcName : NPCs) {
//          instance of npc name from game
            NPC npc = game.getNPC(npcName);
            if (npc != null) {
                result.append("\n").append(npc.getName()).append(", ").append(npc.getImpression());
            }
        }
        System.out.println("People: " + result.toString());
    }



    //method *Prints* the List<String> Items from the json location
    public void printItems() {
        String result = "";
        for (String item : items) {
//      for each over the list of items
            result += item + ", ";
//      appends  each String item name to result string and adds a "," after each.
        }
        result = result.substring(0, result.length() - 2);
//      takes result and takes off the last 2 chars to there isnt an extra "," after the last String item name
        System.out.println("Items: " + result);
    }

    public void printPrettyExits() {
        for (Map.Entry<String, String> exit : exits.entrySet()) {
            System.out.println(exit.getKey() + ": " + exit.getValue());
        }
    }
}
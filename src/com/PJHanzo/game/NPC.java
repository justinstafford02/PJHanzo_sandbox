package com.PJHanzo.game;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NPC {

    @SerializedName("npc_name")
    private String name;

    @SerializedName("npc_impression")
    private String impression;

    @SerializedName("dialog")
    private String dialog;

    @SerializedName("gear")
    private Map<String, String> gear;

    //  constructor
    //  TODO:   dialog will change to List
    private NPC(String name, String impression, String dialog, Map<String, String> gear) {
        this.name = name;
        this.impression = impression;
        this.dialog = dialog;
        this.gear = getGear();
    }

    // business methods
    public static void printNpc(List<NPC> Npcs) {
        for (NPC npc : Npcs) {
            System.out.println("\nPeople:");
            System.out.printf("%s \n %s\n", npc.getName(), npc.getImpression());
        }
    }

    public void printNpcGear() {
        System.out.println("\tNPC is wearing:");
        for (Map.Entry<String, String> gear : gear.entrySet()) {
            System.out.printf("%s: %s\n", gear.getKey(), gear.getValue());
        }
    }

//    public void printDialog(String name) {
//        int randomNum = (int) (Math.random() * (dialog.size()));
//        System.out.println(dialog.get(randomNum));
//
//    }

    //  accessor methods
    public String getName() {
        return name;
    }

    public String getImpression() {
        return impression;
    }

    public String getDialog() {
        return dialog;
    }

    public Map<String, String> getGear() {
        return gear;
    }

}
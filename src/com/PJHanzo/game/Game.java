package com.PJHanzo.game;

import com.PJHanzo.render.Banner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.Map;

public class Game {
    private static List<Location> locations = new ArrayList<>();
    private static List<Item> items = new ArrayList<>();
    private static List<NPC> npcs = new ArrayList<>();
    private static Gson gson = new Gson();
    private final Player player; //js - current player object
    private final TextParser parser;
    public Scanner scanner = new Scanner(System.in); //js - handles player input from console.
    boolean gameState;  //js- keeps track if game's running.
    String reset = "\u001B[0m";  //js - escape code for manipulating console text color.
    String red = "\u001B[31m";
    String green = "\u001B[32m";
    String yellow = "\u001B[33m";
    String blue = "\u001B[34m";
    private Banner banner; //js - instance of render/Banner.java for cool visual...not needed
    // after Sprint1.


    public Game() {
        //loads location, item and NPC from JSON; initializes player, parser, and banner.
        readLocationJson();
        readItemJson();
        readNpcJson();
        this.player = new Player("Anon");
        this.parser = new TextParser();
        this.banner = new Banner();
        player.updateLocation(locations.get(0));
        //player.viewInventory();
    }
// js - reads from resources/Location.json and adds it to locations (List<Location>).
    //once we label resources at resources root...will need to change ("./resources/Location
// .json") to resources/Location.json.
    private static void readLocationJson() {
        try {
            Type LocationListType = new TypeToken<List<Location>>() {
            }.getType();
            List<Location> locationList = gson
                    .fromJson(new FileReader
                            ("./resources/Location.json"), LocationListType);
            locations.addAll(locationList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // js - reads from resources/Item.json and .
    //once we label resources at resources root...will need to change ("./resources/Location
// .json") to resources/Location.json.
    private static void readItemJson() {
        try {
            Type itemListType = new TypeToken<List<Item>>() {
            }.getType();
            List<Item> itemList = gson
                    .fromJson(new FileReader
                            ("./resources/Item.json"), itemListType);
            items.addAll(itemList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readNpcJson() {
        try {
            Type NpcType = new TypeToken<List<NPC>>() {
            }.getType();
            List<NPC> npcList = gson
                    .fromJson(new FileReader
                            ("./resources/NPC.json"), NpcType);
            npcs.addAll(npcList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
// method takes the String name of an NPC and returns the specific NPC name of that NPC object
     NPC getNPC(String name) {
//        for each NPC object in npcs (List<NPC>) get the name.
        for (NPC npc : npcs) {
            if (npc.getName().equalsIgnoreCase(name)) {
                return npc;
            }
        }
        return null;
    }

    private void clearConsole() {
        pause();
        System.out.print("\033c");
        System.out.flush();
    }

    public void gameLoop() {
        clearConsole();
        displayInfo();
        updateInfo(parser.processInput());
    }

    public void play() {
        welcomeScreen();
        newGame();
        while (gameState) {

            // TODO: Console clear()
            gameLoop();
        }
    }
// js- displays the current location and the player's health in the game.
    private void displayInfo() {
        System.out.println(reset + "Location: " + player.getCurrentLocation().getName());
        System.out.println(player.getName() + " : " + player.getHealth());
//        System.out.println("Inventory: ");
//        player.viewInventory();

    }

    private void pause() {
        System.out.print(green + "Please hit enter to continue:");
        scanner.nextLine();
        System.out.println();
    }

    public void quit() {
        System.out.println(yellow + "If you want to leave - type [Y/N]");
        String text = parser.simpleParse();
        if ("Y".equals(text)) {
            banner.printBanner("Gameover");
            gameState = false;

        } else {
            System.out.println(reset + "Then, we will keep playing");
        }
    }

    private void newGame() {

        System.out.println(yellow + "Are you ready to start a new game? [Y/N]");
        String text = parser.simpleParse();
        if ("Y".equals(text)) {
            gameState = true;

        } else {
            banner.printBanner("Gameover");

        }
    }

    private void updateInfo(List<String> words) {
        String verb;
        String noun = null;

        if (words.size() > 1) {
            verb = words.get(0);
            noun = words.get(1);
        } else {
            verb = words.get(0);
        }

        if (verb != "look") {
            verb = parser.getSynonym(verb);
        }

        if (verb != null) {
            switch (verb.toLowerCase()) {
                case "look":
                    lookAt(noun);
                    break;
                case "go":
                    changeLocation(noun);
                    break;
                case "quit":
                    quit();
                    break;
                case "help":
                    help();
                    break;
                case "grab":
                    items = player.getItem(noun, items);
                    break;
                case "drop":
                    player.dropItem(noun);
                    break;
                case "talk":
                    talk(noun);
                    break;
                case "bag":
                    player.viewInventory();
                    break;
                default:
                    System.out.println(yellow + "I don't understand that command. Try [look], [go] or a common synonym. Also, verify " +
                            "that location exist!");
            }
        } else {
            System.out.println("Invalid word(s). Please enter valid verbs and nouns.");
        }
    }

    private void lookAt(String noun) {
        // use streams to filter and return item / location details

        if (items.stream().anyMatch(item -> item.getName().equalsIgnoreCase(noun))) {
            Item selectedItem = items.stream()
                    .filter(item -> item.getName().equalsIgnoreCase(noun))
                    .findAny()
                    .orElse(null);

            if (selectedItem != null) {
                System.out.println(selectedItem.getDescription());
            } else {
                System.out.println("This item is so awesome it can't be described!");
            }
        }
        if (locations.stream().anyMatch(location -> location.getName().equalsIgnoreCase(noun))) {
            Location selectedLocation = locations.stream()
                    .filter(location -> location.getName().equalsIgnoreCase(noun))
                    .findFirst()
                    .orElse(null);

            if (selectedLocation != null) {
                if (selectedLocation == player.getCurrentLocation()) {
                    System.out.println(player.getCurrentLocation().getDescription());
                    selectedLocation.printPrettyExits();  //TODO: js- remove in Sprint2
                    selectedLocation.printNPCs(this);
                    selectedLocation.printItems();
                } else {
                    System.out.println("You are too far away!");
                }
            }
        }
    }

    //TODO talkTo() method to interact with NPC
    // js - checks if there's an NPC with given name @ current location.
    private void talk(String name) {
//        checks if NPC name is at current location
        if (player.getCurrentLocation().containsNPC(name)){

//            takes the input NPC name and assigns it selectedNPC
            NPC selectedNPC = getNPC(name);
//            prints the selectedNPC dialog
            System.out.println(selectedNPC.getDialog());
        } else {
            System.out.println("No one by that name is here");
        }
    }


    private void help() {
        banner.printBanner("Help");

    }

    private void welcomeScreen() {
        banner.printBanner("Welcome2");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        banner.printBanner("Tutorial");

        System.out.println(reset + "Welcome to Project - Hanzo");
        System.out.println();
    }

    // TODO: js- check player and location class to ensure it doesn't pass null pointer exception
    private void changeLocation(String noun) {
        Location currentLocation = player.getCurrentLocation();
        Map<String, String> exits = currentLocation.getExits();

        // Check if the provided direction is a valid exit
        if (exits.containsKey(noun.toLowerCase())) {
            String nextLocationName = exits.get(noun.toLowerCase());

            // Check if the next location is found
            Location nextLocation = findLocationByName(nextLocationName);
            if (nextLocation != null) {
                player.updateLocation(nextLocation);
                System.out.println("Going to: " + nextLocation.getName());
                displayLocationInfo(nextLocation);
            } else {
                System.out.println("Invalid location. Please enter a valid location name.");
            }
        }
    }
    // TODO: js- check player and location class to ensure it doesn't pass null pointer exception'
    private Location findLocationByName(String name) {
        return locations.stream()
                .filter(location -> location.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    private void displayLocationInfo(Location location) {
        System.out.println("Location: " + location.getName());
        System.out.println(location.getDescription());

    }

}

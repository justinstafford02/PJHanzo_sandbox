package com.PJHanzo.game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    int health;
    Location currentLocation;
    private List<Item> inventory;

    public Player(String name) {
        setName(name);
        this.health = 100;
        this.inventory = new ArrayList<>();
//        this.inventory.add(new Item("Potion", "Restores a small amount of health."));
//        this.inventory.add(new Item("Sword", "A sharp blade for combat."));
//        this.inventory.add(new Item("Cheese", "A tasty snack for health recovery."));
    }

    public void viewInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (Item item : inventory) {
                System.out.println(item.getName());
            }
        }
    }

    public void dropItem(String itemName) {
        if (inventory.stream().anyMatch(item -> item.getName().equalsIgnoreCase(itemName))) {
            Item matchedItem = inventory.stream()
                    .filter(item -> item.getName().equalsIgnoreCase(itemName))
                    .findAny()
                    .orElse(null);
            if (matchedItem != null) {
                inventory.remove(matchedItem);
                System.out.println(itemName + " dropped.");
            }
        } else {
            System.out.println("Item not found in inventory.");
        }
    }

    public List<Item> getItem(String itemName, List<Item> locItems) {
        if (locItems.stream().anyMatch(item -> item.getName().equalsIgnoreCase(itemName))) {
            Item matchedItem = locItems.stream()
                    .filter(item -> item.getName().equalsIgnoreCase(itemName))
                    .findAny()
                    .orElse(null);
            if (matchedItem != null) {
                inventory.add(matchedItem);
                locItems.remove(matchedItem);
                System.out.println(itemName + " picked up.");
                return locItems;
            }
        } else {
            System.out.println("Item not found in area.");
        }
        return locItems;

    }

    private int attackDamage = 7;

    public void attack(Enemy enemy){
        enemy.takeDamage(attackDamage);
        System.out.println("You attacked " + enemy.getName() + " for " + attackDamage + " damage!");
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if(this.health < 0) this.health = 0;
        System.out.println(name + " takes " + damage + " damage!");
    }

    public int getHealth() {
        return health;
    }

    public void updateLocation(Location location) {
        this.currentLocation = location;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

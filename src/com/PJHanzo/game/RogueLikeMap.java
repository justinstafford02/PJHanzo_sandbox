package com.PJHanzo.game;

import java.util.Scanner;

public class RogueLikeMap {
    private char[][] map;
    private int playerX;
    private int playerY;

    public RogueLikeMap(int width, int height) {
        map = new char[width][height];
        initializeMap();
        placePlayer(width / 2, height / 2);
    }

    private void initializeMap() {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                map[x][y] = '.';
            }
        }
    }

    private void placePlayer(int x, int y) {
        playerX = x;
        playerY = y;
        map[playerX][playerY] = '@';
    }

    private void displayMap() {
        for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map.length; x++) {
                System.out.print(map[x][y] + " ");
            }
            System.out.println();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            displayMap();
            System.out.print("Enter your move (w/a/s/d): ");
            input = scanner.nextLine();

            map[playerX][playerY] = '.';    // last player's position removal

            // Move
            if (input.equalsIgnoreCase("w")) {
                playerY--;
            } else if (input.equalsIgnoreCase("a")) {
                playerX--;
            } else if (input.equalsIgnoreCase("s")) {
                playerY++;
            } else if (input.equalsIgnoreCase("d")) {
                playerX++;
            } else {
                System.out.println("Invalid input. Use w/a/s/d to move.");
            }

            // wall check
            if (-1 == playerX || playerX == map.length) {
                System.out.println("You hit the wall");
                playerX = Math.max(0, Math.min(map.length - 1, playerX));
            }
            if (-1 == playerY || playerY == map[0].length) {
                System.out.println("You hit the wall");
                playerY = Math.max(0, Math.min(map[0].length - 1, playerY));
            }


            // new player's position
            map[playerX][playerY] = '@';
        }
    }
}

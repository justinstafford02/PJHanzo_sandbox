package com.PJHanzo.game;

public class Map {
    private int width;
    private int height;
    private char[][] simpleMap;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.simpleMap = new char[width][height];
        initializeMap();
    }

    private void initializeMap() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                simpleMap[i][j] = '_';
            }
        }
    }

    public void setCell(int x, int y, char value) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            simpleMap[x][y] = value;
        } else {
            System.out.println("Invalid coordinates.");
        }
    }

    public char getCell(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return simpleMap[x][y];
        } else {
            System.out.println("Invalid coordinates.");
            return ' ';
        }
    }

    public void displayMap() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(simpleMap[j][i] + " ");
            }
            System.out.println();
        }
    }

}
package com.PJHanzo.game;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TextParser {
    String reset = "\u001B[0m";
    List<String> lookSyn = Arrays.asList("examine", "inspect", "observe", "survey", "view", "peer", "gaze", "scan", "study", "investigate");
    List<String> goSyn = Arrays.asList("move", "travel", "walk", "run", "head", "journey", "roam", "proceed", "trek", "navigate");
    private Scanner scanner = new Scanner(System.in);


    public String simpleParse() {
        System.out.print(reset + "Enter your command: \n>");
        return scanner.nextLine().toUpperCase();

    }

    public List<String> processInput() {
        System.out.print(reset + "Enter your command: \n>");
        String userInput = scanner.nextLine().toLowerCase();
        userInput = preprocessInput(userInput);
        return Arrays.asList(userInput.split(" "));
    }

    private String preprocessInput(String input) {
//        System.out.println(input);
        String roughInput = input.replaceAll("\\b(?:the|a|an|in|on|at|to)\\b", "").trim();
        return roughInput.replaceAll("\\s+", " ").trim();

    }


    public String getSynonym(String word) {
        // If word is synonym of keyword assign the proper keyword instead of synonym.
        if (lookSyn.contains(word)) {
            return "look";
        } else if (goSyn.contains(word)) {
            return "go";
        }
        return word;
    }


    // Other methods as needed...
}

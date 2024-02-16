package com.PJHanzo.main;

import com.PJHanzo.game.Game;

/*
 * #AB292
 * Player is presented with the option to play a New Game
 * When the option to play a new game is chosen, the screen clears and the game begins.
 * The cursor will end up on the Games command prompt, which should be a "> ".
 */
public class MainClass {
    public static void main(String[] args) {
        Game game = new Game();
        game.play();


    }
}
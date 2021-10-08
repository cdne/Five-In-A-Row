package com.game;

public class Main {
    public static void main(String[] args) {
        Main application = new Main();
        application.play(args);
    }

    void play(String[] args){
        Game game = new Game();
        game.play();
    }
}

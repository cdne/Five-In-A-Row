package com.company;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
//	    game.setBoard(player1.setMove(game.getBoard(), player1.getMove(game.getBoard())));

        player1.getMove(game.getBoard());
        player2.getMove(game.getBoard());
        player1.getMove(game.getBoard());
        player2.getMove(game.getBoard());
        player1.getMove(game.getBoard());
        player2.getMove(game.getBoard());
        player1.getMove(game.getBoard());


    }
}

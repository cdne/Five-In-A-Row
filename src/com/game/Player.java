package com.game;

import java.util.Arrays;
import java.util.Scanner;

public class Player {
    private String playerName;
    private Scanner scanner;
    private String[] validMoves;
    private int[][] playerBoard;

    public Player(String playerName) {
        this.playerName = playerName;
        scanner = new Scanner(System.in);
        validMoves = new String[]{"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "B1", "B2", "B3", "B4", "B5", "B6",
                "B7", "B8", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8"};
    }

    /**
     * Get player name
     * @return player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Get player board
     * @return player board
     */
    public int[][] getPlayerBoard() {
        return playerBoard;
    }

    /**
     * Set player board
     * @param board game board
     */
    public void setPlayerBoard(int[][] board) {
        playerBoard = board;
    }

    /**
     * Get player move
     */
    void getMove() {
        mark();
    }

    /**
     * Mark on board the move only if is valid
     */
    private void mark() {
        validMove();
    }

    /**
     * Check if player move is valid
     */
    private void validMove() {
        while(true){
            System.out.print("Enter coordinates: ");
            String input = scanner.next();
            playerHasQuitTheGame(input);
            boolean validCoordinates = Arrays.stream(validMoves).anyMatch(input::equals);

            if(!validCoordinates) {
                System.out.println("Invalid coordinates. Try again.");
                continue;
            }

            int firstIndex = getFirstIndex(input);
            int secondIndex = getSecondIndex(input);

            int valueFromPlayerBoard = playerBoard[firstIndex][secondIndex];
            if(valueFromPlayerBoard == 1 || valueFromPlayerBoard == 2 || !validCoordinates) {
                System.out.println("Invalid coordinates. Try again.");
                continue;
            } else {
                markMoveOnPlayerBoard(firstIndex, secondIndex);
                break;
            }
        }
    }

    /**
     * Check if the player has quit the game.
     * Display the appropriate message and make the other player the winner
     * @param input player input
     */
    private void playerHasQuitTheGame(String input) {
        if(input.equalsIgnoreCase("quit")){
            if(playerName.equals("Player 1")){
                System.out.println("\n" + playerName + " quit the game.\n\n\n\n**** Player 2 is the winner ****\t\t\t");
            } else {
                System.out.println("\n" + playerName + " quit the game.\n\n\n\n**** Player 1 is the winner ****\t\t\t");
            }
            System.exit(0);
        }
    }

    /**
     * Mark the player on the board. 1 - player 1, 2 - player 2
     * Based on the player and the condition that the place is not marked add value 1 or value 2
     */
    private void markMoveOnPlayerBoard(int firstIndex, int secondIndex) {
        if (playerName == "Player 1") {
            if (!(playerBoard[firstIndex][secondIndex] == 2)) {
                playerBoard[firstIndex][secondIndex] = 1;
            }
        } else {
            if (!(playerBoard[firstIndex][secondIndex] == 1)) {
                playerBoard[firstIndex][secondIndex] = 2;
            }
        }
    }

    /**
     * Set the column index
     * When letters are A, B or C set the following values: 0, 1 or 2
     * @param input player input
     */
    private int getFirstIndex(String input) {
        if (input.charAt(0) == 'A') {
            return 0;
        } else if (input.charAt(0) == 'B') {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * Set the row index
     * When player provides a value for example A8 the function returns the row 7 (8 - 1)
     * @param input player input
     */
    private int getSecondIndex(String input) {
        return Character.getNumericValue(input.charAt(1)) - 1;
    }
}

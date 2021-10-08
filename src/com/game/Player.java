package com.game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Player {
    private int[][] playerBoard;
    private String playerName;
    private String playerCoordinates;
    private Scanner scanner;

    private String[] validMoves = {"A1", "A2", "A3"};

    public Player(String playerName){
        this.playerName = playerName;
        scanner = new Scanner(System.in);
    }

    public int[][] getPlayerBoard() {
        return playerBoard;
    }

    public void setPlayerBoard(int[][] playerBoard) {
        this.playerBoard = playerBoard;
    }

    public void invalidCoordinates(String input, boolean containsInput){
        if(!containsInput){
            for(int i = 0; i< 999; i++){
                System.out.println("Invalid coordinates, try again.");
                input = scanner.next();
                if(Arrays.stream(validMoves).anyMatch(input::equals)){
                    break;
                }
            }
        }
    }

    public String getMove(int[][] board){
        System.out.println(this.playerName + " you can find the following valid coordinates: \n");
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[row].length; col++){

                if(board[row][col] == 1 || board[row][col] == 2){
                    System.out.print(" " + "\t");
                }

                if(row == 0 && board[row][col] == 0){
                    System.out.print("A" + (col + 1) + "\t");
                }
                if(row == 1 && board[row][col] == 0){
                    System.out.print("B" + (col + 1) + "\t");
                }
                if(row == 2 && board[row][col] == 0){
                    System.out.print("C" + (col + 1) + "\t");
                }

            }
            System.out.println();
        }

        System.out.println("Enter your desired option: ");



        String input = scanner.next() + "\n";
        boolean containsInput = Arrays.stream(validMoves).anyMatch(input::equals);
        invalidCoordinates(input, containsInput);



        boolean A1 = input.charAt(0) == 'A' & input.charAt(1) == '1';
        boolean A2 = input.charAt(0) == 'A' & input.charAt(1) == '2';
        boolean A3 = input.charAt(0) == 'A' & input.charAt(1) == '3';
        boolean A4 = input.charAt(0) == 'A' & input.charAt(1) == '4';
        boolean A5 = input.charAt(0) == 'A' & input.charAt(1) == '5';
        boolean A6 = input.charAt(0) == 'A' & input.charAt(1) == '6';
        boolean A7 = input.charAt(0) == 'A' & input.charAt(1) == '7';
        boolean A8 = input.charAt(0) == 'A' & input.charAt(1) == '8';


        if(playerName == "Player 1"){
            if(A1){
                board[0][0] = 1;
            }
        } else {
            if(A1){
                board[0][0] = 2;
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

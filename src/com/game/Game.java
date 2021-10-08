package com.game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private int[][] board;
    private String[] boardRows;
    private String[] boardCols;
    private Player player1;
    private Player player2;
    private int winCondition;
    private String playerName;

    public Game() {
        board = new int[3][8];
        boardRows = new String[]{"A", "B", "C"};
        boardCols = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
    }

    /**
     * Set win condition
     */
    private void setWinCondition() {
        System.out.print("Set the win condition: ");
        Scanner scanner = new Scanner(System.in);
        while(true){
            try {
                winCondition = scanner.nextInt();
            if(winCondition > 8 || winCondition < 2) {
                System.out.println("Win condition must be less than 8 and higher than 1.");
                System.out.print("Set the win condition: ");
                continue;
            }
                break;
            } catch(InputMismatchException e) {
                scanner.next();
                System.out.print("That’s not an integer. Try again: ");
            }
        }

    }

    /**
     * Get board
     * @return game board
     */
    private int[][] getBoard() {
        return board;
    }

    /**
     * Set the player board as the game board
     * When a player makes a move the board is populated with his moves
     * @param board game board
     */
    private void setBoard(int[][] board) {
        this.board = board;
    }

    /**
     * Print the board with player 1 and player 2 moves
     */
    public void printBoard() {
        for(String boardCol : boardCols){
            System.out.print("\t" + boardCol);
        }
        System.out.println();
        for (int col = 0; col < board.length; col++) {
            System.out.print(boardRows[col] + "\t");
            for (int row = 0; row < board[col].length; row++) {
                if (board[col][row] == 1) {
                    System.out.print("X" + "\t");
                } else if (board[col][row] == 2) {
                    System.out.print("O" + "\t");
                } else {
                    System.out.print("." + "\t");
                }
            }
            System.out.println();
        }

    }

    /**
     * Play the game
     */
    public void play() {
        setWinCondition();
        printBoard();
        while(true) {
            checkIfBoardIsFull();
            player1.setPlayerBoard(getBoard());
            displayValidCoordinates(getBoard(), player1.getPlayerName());
            player1.getMove();
            setBoard(player1.getPlayerBoard());
            printBoard();
            showPlayerHasWon();

            player2.setPlayerBoard(getBoard());
            displayValidCoordinates(getBoard(), player2.getPlayerName());
            player2.getMove();
            setBoard(player2.getPlayerBoard());
            showPlayerHasWon();
            printBoard();
        }
    }

    /**
     * Check the board if there are any empty moves left
     * @return return true when board is full and false when is not
     */
    private boolean isFull() {
        boolean boardIsFull = true;
        for(int col = 0; col < board.length; col++){
            for(int row = 0; row < board[col].length;row++){
                System.out.print(board[col][row]);
                if(board[col][row] == 0){
                    boardIsFull = false;
                }
            }
        }
        return boardIsFull;
    }

    /**
     * EXIT game when there are no valid moves on board
     */
    private void checkIfBoardIsFull() {
        if(isFull()) {
            System.out.println("Board is full. The game is a tie.");
            System.exit(0);
        }
    }

    /**
     * Display the valid coordinates that a player can find on board
     * @param board game board
     * @param playerName name of the player that needs to make the next move
     */
    void displayValidCoordinates(int[][] board, String playerName) {
        this.playerName = playerName;
        System.out.println("\n" + playerName + " you can find the following valid coordinates: \n");
        for (int col = 0; col < board.length; col++) {
            for (int row = 0; row < board[col].length; row++) {
                if (board[col][row] == 1 || board[col][row] == 2) {
                    System.out.print(" " + "\t");
                } else if (col == 0 && board[col][row] == 0) {
                    System.out.print("A" + (row + 1) + "\t");
                } else if (col == 1 && board[col][row] == 0) {
                    System.out.print("B" + (row + 1) + "\t");
                } else if (col == 2 && board[col][row] == 0) {
                    System.out.print("C" + (row + 1) + "\t");
                }
            }
            System.out.println();
        }
    }

    /**
     * Display the player has won the game
     */
    void showPlayerHasWon() {
        if(hasWon()){
            System.out.println("\n" + playerName + " has won\n" );
            printBoard();
            System.exit(0);
        }
    }

    /**
     * Check if one of the player has won
     * Player 1 win condition is the same as the winCondition variable and player 2 is winCondition x2
     * @return boolean value that one of the player has won
     */
    private boolean hasWon() {
        int playerOneWinCondition = winCondition;
        int playerTwoWinCondition = winCondition * 2;
        int playerOneSum = 0;
        int playerTwoSum = 0;
        for(int col = 0; col < board.length; col++){
            for(int row = 0; row < board[col].length; row++){
                if(board[col][row] == 1) {
                    playerOneSum++;
                    playerTwoSum = 0;
                    if(playerOneWinCondition == playerOneSum) return true;
                }
                else if (board[col][row] == 2){
                    playerTwoSum += 2;
                    playerOneSum = 0;
                    if(playerTwoWinCondition == playerTwoSum) return true;
                }
                else if(board[col][row] == 0) {
                    playerOneSum = 0;
                    playerTwoSum = 0;
                }
            }

        }
        return false;
    }

}

package com.game;

public class Game {
    private int[][] board;

    public Game() {
        board = new int[3][8];
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }


}

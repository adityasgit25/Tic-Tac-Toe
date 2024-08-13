package com.aditya;

import java.util.Scanner;

public class TicTacToeGame {
    private static final int BOARD_SIZE = 3;
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static final char EMPTY_CELL = ' ';

    private char[][] board;
    private char currentPlayer;
    private boolean gameOver;
    private Scanner scanner;

    public TicTacToeGame() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = PLAYER_X;
        gameOver = false;
        scanner = new Scanner(System.in);
        initializeBoard();
    }

    public void play() {
        while (!gameOver) {
            printBoard();
            makeMove();
            if (checkWin(currentPlayer)) {
                gameOver = true;
                System.out.println("Player " + currentPlayer + " has won!");
            } else if (isBoardFull()) {
                gameOver = true;
                System.out.println("It's a draw!");
            } else {
                switchPlayer();
            }
        }
        printBoard();
        scanner.close();
    }

    private void initializeBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = EMPTY_CELL;
            }
        }
    }

    private void makeMove() {
        int row, col;
        do {
            System.out.print("Player " + currentPlayer + " enter row (0-2) and column (0-2): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
        } while (!isValidMove(row, col));
        
        board[row][col] = currentPlayer;
    }

    private boolean isValidMove(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            System.out.println("Invalid move. Row and column must be between 0 and 2.");
            return false;
        }
        if (board[row][col] != EMPTY_CELL) {
            System.out.println("Invalid move. Cell is already occupied.");
            return false;
        }
        return true;
    }

    private boolean checkWin(char player) {
        // Check rows, columns and diagonals
        for (int i = 0; i < BOARD_SIZE; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    private boolean isBoardFull() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }

    private void printBoard() {
        System.out.println("-------------");
        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.print("| ");
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        game.play();
    }
}
package tictactoe.Model;

public class Board {

    public static final int row = 3;
    public static final int column = 3;
    public static final char PLAYER_X = 'X';
    public static final char PLAYER_O = 'O';

    private char[][] board;

    public Board() {
        board = new char[row][column];
    }

    public static int getRow() {
        return row;
    }

    public static int getColumn() {
        return column;
    }

    public static char getPlayerX() {
        return PLAYER_X;
    }

    public static char getPlayerO() {
        return PLAYER_O;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }
}

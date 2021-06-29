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

    public char[][] getBoard() {
        return board;
    }

}

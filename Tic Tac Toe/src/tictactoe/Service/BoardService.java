package tictactoe.Service;
import tictactoe.Model.Board;
public class BoardService {

    private final InputService input;
    private final Board board;
    private boolean isRunning = true;
    private String result = "";


    /**
     * Creates an instance of Board and InputService.
     */
    public BoardService() {
        board = new Board();
        input = new InputService();
    }

    /**
     * Returns the state of the game
     *
     * @return isRunning
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Returns the board
     *
     * @return board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Returns the Scanner object "input"
     *
     * @return input
     */
    public InputService getInput() {
        return input;
    }

    /**
     * Checks every condition in order for the game to stop running.
     */
    public void checkResult() {
        if ( isWinning(Board.PLAYER_X) ) {
            result = "X wins";
            isRunning = false;
        } else if ( isWinning(Board.PLAYER_O) ) {
            result = "O wins";
            isRunning = false;
        } else if ( isDraw() ) {
            result = "Draw";
            isRunning = false;
        } else {
            isRunning = true;
        }
    }

    /**
     * Prints the result of the game.
     */
    public void printResult() {
        System.out.println(result);
    }

    /**
     * @return returns true if the sum of the counters equals 9.
     */
    public boolean isDraw() {
        int xCounter = countCharOccurrences(Board.PLAYER_X);
        int oCounter = countCharOccurrences(Board.PLAYER_O);
        return xCounter + oCounter == 9;
    }

    /**
     * Returns the winning condition
     * @param player
     * @return boolean winningCondition
     */
    public boolean isWinning(char player) {
        boolean topRow = board.getBoard()[0][0] == player && board.getBoard()[0][1] == player
                && board.getBoard()[0][2] == player;
        boolean midRow = board.getBoard()[1][0] == player && board.getBoard()[1][1] == player
                && board.getBoard()[1][2] == player;
        boolean botRow = board.getBoard()[2][0] == player && board.getBoard()[2][1] == player
                && board.getBoard()[2][2] == player;
        boolean leftColumn = board.getBoard()[0][0] == player && board.getBoard()[1][0] == player
                && board.getBoard()[2][0] == player;
        boolean midColumn = board.getBoard()[0][1] == player && board.getBoard()[1][1] == player
                && board.getBoard()[2][1] == player;
        boolean rightColumn = board.getBoard()[0][2] == player && board.getBoard()[1][2] == player
                && board.getBoard()[2][2] == player;
        boolean diagonalOne = board.getBoard()[0][0] == player && board.getBoard()[1][1] == player
                && board.getBoard()[2][2] == player;
        boolean diagonalTwo = board.getBoard()[0][2] == player && board.getBoard()[1][1] == player
                && board.getBoard()[2][0] == player;

        return topRow || midRow || botRow || leftColumn || midColumn || rightColumn || diagonalOne || diagonalTwo;
    }

    /**
     * Returns the number of ocurrences of a player in the board
     * @param player
     * @return charCounter
     */
    public int countCharOccurrences(char player) {
        int charCounter = 0;
        for ( int i = 0; i < 3; i++ ) {
            for ( int j = 0; j < 3; j++ ) {
                if ( board.getBoard()[i][j] == player ) {
                    charCounter++;
                }
            }
        }
        return charCounter;
    }

    /**
     * Checks if the current cell is occupied
     * @param x
     * @param y
     * @return boolean if cell is occupied
     */
    public boolean isCellOccupied(int x, int y) {
        return board.getBoard()[x - 1][y - 1] == Board.PLAYER_O || board.getBoard()[x - 1][y - 1] == Board.PLAYER_X;
    }

    /**
     * Checks if the coordinate is within range
     * @param x
     * @param y
     * @return boolean if the coordinate is within range
     */
    public boolean isCoordinateWithinRange(int x, int y) {
        return ((x < 1 || x > 3 || y < 1 || y > 3));
    }

    /**
     * Checks who plays first so that the next player isn't the same one
     * @return a player
     */
    public char setPlayer() {
        int oCount = countCharOccurrences(Board.PLAYER_O);
        int xCount = countCharOccurrences(Board.PLAYER_X);

        if ( xCount > oCount ) {
            return Board.PLAYER_O;
        } else {
            return Board.PLAYER_X;
        }
    }

}

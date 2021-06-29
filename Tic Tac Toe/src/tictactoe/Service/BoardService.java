package tictactoe.Service;


import tictactoe.Interfaces.IBoardService;
import tictactoe.Model.Board;

public class BoardService implements IBoardService {

    private Board board;
    InputService input;
    private boolean isRunning = true;
    private String result = "";


    public BoardService() {
        board = new Board();
        input = new InputService();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public Board getBoard() {
        return board;
    }

    public InputService getInput() {
        return input;
    }

    public String getResult() {
        return result;
    }

    public void checkResult() {
        if(isWinning(Board.PLAYER_X)){
            result = "X wins";
            isRunning = false;
        } else if(isWinning(Board.PLAYER_O)) {
            result = "O wins";
            isRunning = false;
        } else if(isDraw()) {
            result = "Draw";
            isRunning = false;
        } else {
            isRunning = true;
        }
    }

    public boolean isDraw() {
        int xCounter = countX();
        int oCounter = countO();
        if (xCounter + oCounter == 9) {
            return true;
        }
        return false;
    }


    public boolean isImpossible() {
        int xCounter = countX();
        int oCounter = countO();
        if (Math.abs(xCounter - oCounter) > 1 || Math.abs(oCounter - xCounter) > 1
                || isWinning(Board.PLAYER_O) && isWinning(Board.PLAYER_X)) {
            return true;
        }
        return false;
    }


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


    public int countX() {
        int xcounter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getBoard()[i][j] == 'X') {
                    xcounter++;
                }
            }
        }
        return xcounter;
    }


    public int countO() {
        int ocounter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getBoard()[i][j] == 'O') {
                    ocounter++;
                }
            }
        }
        return ocounter;
    }


    public boolean isCellOccupied(int x, int y) {
        return board.getBoard()[x - 1][y - 1] == Board.PLAYER_O || board.getBoard()[x - 1][y - 1] == Board.PLAYER_X;
    }


    public boolean isCoordinateWithinRange(int x, int y) {
        return ((x < 1 || x > 3 || y < 1 || y > 3));
    }


    public char setPlayer() {
        int oCount = countO();
        int xCount = countX();

        if (xCount > oCount) {
            return Board.PLAYER_O;
        } else {
            return Board.PLAYER_X;
        }
    }

    public boolean isMoveValid(int x, int y) {
        if (isCoordinateWithinRange(x, y) && !isCellOccupied(x, y)) {
            return true;
        } else {
            return false;
        }
    }

    public String printGameState() {
        if (isImpossible()) {
            return "Impossible";
        } else {
            if (isWinning(Board.PLAYER_X)) {
                return "X wins";
            } else if (isWinning(Board.PLAYER_O)) {
                return "O wins";
            } else if (isDraw()) {
                return "Draw";
            } else {
                return "Game not finished";
            }
        }
    }


}

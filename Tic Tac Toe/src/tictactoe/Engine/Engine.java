package tictactoe.Engine;

import tictactoe.Service.BoardService;

import java.util.InputMismatchException;

public class Engine {

    public static int turn = 0;
    private final BoardService service;

    /**
     * Instances the service class, populates an empty board and draws it for the user.
     * While the game is running, displays the turn count, makes a move, draws the updated board and checks the result.
     * When the game ends, it prints the ending result.
     */
    public Engine() {
        service = new BoardService();
        populateBoard();
        drawBoard();
        while (service.isRunning()) {
            displayTurnCount();
            move();
            drawBoard();
            service.checkResult();
        }
        service.printResult();

    }

    /**
     * Draws the board
     */
    public void drawBoard() {

        System.out.println("---------");
        System.out.println("|" + ' ' + service.getBoard().getBoard()[0][0] + ' '
                + service.getBoard().getBoard()[0][1] + ' '
                + service.getBoard().getBoard()[0][2] + ' ' + "|");
        System.out.println("|" + ' ' + service.getBoard().getBoard()[1][0] + ' '
                + service.getBoard().getBoard()[1][1] + ' '
                + service.getBoard().getBoard()[1][2] + ' ' + "|");
        System.out.println("|" + ' ' + service.getBoard().getBoard()[2][0] + ' '
                + service.getBoard().getBoard()[2][1] + ' '
                + service.getBoard().getBoard()[2][2] + ' ' + "|");
        System.out.println("---------");

    }

    /**
     * Populates an empty board.
     */
    public void populateBoard() {
        for ( int i = 0; i < 3; i++ ) {
            for ( int j = 0; j < 3; j++ ) {
                service.getBoard().getBoard()[i][j] = '_';
            }
        }
    }

    /**
     * If the input is correct, it makes a move and updates the turn count.
     */
    public void move() {
        int x;
        int y;
        while (true) {
            try {
                System.out.print("Enter the coordinates: ");
                x = Integer.parseInt(service.getInput().getStringInput());
                y = Integer.parseInt(service.getInput().getStringInput());
            } catch (NumberFormatException | InputMismatchException ex) {
                System.out.println("You should enter numbers!");
                continue;
            }
            if ( service.isCoordinateWithinRange(x, y) ) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if ( service.isCellOccupied(x, y) ) {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                service.getBoard().getBoard()[x - 1][y - 1] = service.setPlayer();
                turn++;
                break;
            }

        }
    }

    /**
     * Displays the turn count.
     */
    public void displayTurnCount() {
        System.out.println("Turn: " + turn);
    }
}

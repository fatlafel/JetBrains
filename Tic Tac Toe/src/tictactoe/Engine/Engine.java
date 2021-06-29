package tictactoe.Engine;

import java.util.InputMismatchException;

import tictactoe.Interfaces.IEngine;
import tictactoe.Service.BoardService;


public class Engine implements IEngine {

    BoardService controller = new BoardService();
    public static int turn = 0;

    public Engine() {
        populateBoard();
        drawBoard();
        while (controller.isRunning()) {
            move();
            drawBoard();
            controller.checkResult();
        }
        System.out.println(controller.getResult());

    }

    public void drawBoard() {

        System.out.println("---------");
        System.out.println("|" + ' ' + controller.getBoard().getBoard()[0][0] + ' '
                + controller.getBoard().getBoard()[0][1] + ' '
                + controller.getBoard().getBoard()[0][2] + ' ' + "|");
        System.out.println("|" + ' ' + controller.getBoard().getBoard()[1][0] + ' '
                + controller.getBoard().getBoard()[1][1] + ' '
                + controller.getBoard().getBoard()[1][2] + ' ' + "|");
        System.out.println("|" + ' ' + controller.getBoard().getBoard()[2][0] + ' '
                + controller.getBoard().getBoard()[2][1] + ' '
                + controller.getBoard().getBoard()[2][2] + ' ' + "|");
        System.out.println("---------");

    }

    public void populateBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                controller.getBoard().getBoard()[i][j] = '_';
            }
        }
    }

    public void move() {

        int x;
        int y;
        while (true) {

            try {

                System.out.print("Enter the coordinates: ");
                x = Integer.parseInt(controller.getInput().getStringInput());
                y = Integer.parseInt(controller.getInput().getStringInput());

            } catch (NumberFormatException | InputMismatchException ex) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (controller.isCoordinateWithinRange(x, y)) {
                System.out.println("Coordinates should be from 1 to 3!");

            } else if (controller.isCellOccupied(x, y)) {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                controller.getBoard().getBoard()[x - 1][y - 1] = controller.setPlayer();
                turn++;
                break;

            }

        }
    }
}

package cinema;

import java.util.Arrays;
import java.util.Scanner;

class Seat {
    int row;
    int col;

    Seat(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class SeatAlreadyTakenException extends Exception {
}

class SeatDoesNotExistException extends Exception {
}

public class CinemaService {

    char[][] cinemaRoom;
    private final int cols;
    private final int rows;
    private int ticketPurchased = 0;
    private int currentIncome = 0;


    protected CinemaService(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        cinemaRoom = populateCinemaRoom(rows, cols);
    }

    private static char[][] populateCinemaRoom(int rows, int cols) {
        char[][] cinemaRoom = new char[rows + 1][cols + 1];
        for ( char[] chars : cinemaRoom ) {
            Arrays.fill(chars, 'S');
        }
        for ( int i = 0; i < cinemaRoom.length; i++ ) {
            cinemaRoom[i][0] = (char) ('0' + i);
            for ( int j = 0; j < cinemaRoom[i].length; j++ ) {
                cinemaRoom[0][j] = (char) ('0' + j);
            }
        }
        cinemaRoom[0][0] = ' ';
        return cinemaRoom;
    }


    private int getTotalIncome() {
        if ( totalSeats() <= 60 ) {
            return rows * cols * 10;
        } else {
            return (rows / 2) * cols * 10 + (rows - rows / 2) * cols * 8;
        }
    }

    private String getPercentage() {
        double percent = (double) ticketPurchased * 100 / totalSeats();
        return String.format("%.2f", percent);
    }

    private void buyTicket() {
        try {
            Seat seat = selectSeat();
            setUserCoords(seat);
            int ticketPrice = getTicketPrice(seat);

            currentIncome += ticketPrice;
            ticketPurchased++;

            printTicketPrice(ticketPrice);
        } catch (SeatAlreadyTakenException e1) {
            System.out.println("That ticket has already been purchased!");
            System.out.println();
            buyTicket();
        } catch (SeatDoesNotExistException e2) {
            System.out.println("Wrong input!");
            System.out.println();
            buyTicket();
        }

    }

    private Seat selectSeat() throws SeatDoesNotExistException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number: ");
        int rowCord = scanner.nextInt();
        System.out.println("Enter a seat number in that row: ");
        int colCord = scanner.nextInt();
        System.out.println();

        if ( rowCord < 1 || rowCord > rows || colCord < 1 || colCord > cols ) {
            throw new SeatDoesNotExistException();
        }
        return new Seat(rowCord, colCord);
    }

    private int totalSeats() {
        return cols * rows;
    }

    private int getTicketPrice(Seat seat) {
        int ticketPrice;
        if ( isUserSittingInFrontRow(seat) || totalSeats() <= 60 ) {
            ticketPrice = 10;
        } else {
            ticketPrice = 8;
        }
        return ticketPrice;
    }

    private boolean isUserSittingInFrontRow(Seat seat) {
        return seat.row <= rows / 2;
    }

    public void setUserCoords(Seat seat) throws SeatAlreadyTakenException {
        if ( isSeatTaken(seat) ) {
            throw new SeatAlreadyTakenException();
        } else {
            cinemaRoom[seat.row][seat.col] = setCustomer();
        }
    }

    private boolean isSeatTaken(Seat seat) {
        return cinemaRoom[seat.row][seat.col] == 'B';
    }

    private char setCustomer() {
        return 'B';
    }

    public void displayCinemaRoom() {

        System.out.println("Cinema:");
        for ( char[] chars : cinemaRoom ) {
            System.out.print(' ');
            for ( char aChar : chars ) {
                System.out.print(aChar);
                System.out.print(' ');
            }
            System.out.println();
        }

    }

    public static CinemaService readInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int cols = scanner.nextInt();

        System.out.println();

        return new CinemaService(rows, cols);
    }

    private void printTicketPrice(int ticketPrice) {
        System.out.printf("Ticket price: $%s\n", ticketPrice);
        System.out.println();
    }

    private void printStatistics() {
        System.out.printf("Number of purchased tickets: %d\n", ticketPurchased);
        System.out.printf("Percentage: %s%%\n", getPercentage());
        System.out.printf("Current income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d\n", getTotalIncome());
    }

    public void printMenu() {
        Scanner scanner = new Scanner(System.in);
        int optionMenu;
        do {
            System.out.println("1. Show the seats" + "\n" +
                    "2. Buy a ticket" + "\n" +
                    "3. Statistics" + "\n" +
                    "0. Exit");

            optionMenu = scanner.nextInt();
            System.out.println();
            switch (optionMenu) {
                case 0:
                    return;
                case 1:
                    displayCinemaRoom();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    printStatistics();
                    break;
                default:
                    break;
            }
        } while (optionMenu > 0);


    }

}

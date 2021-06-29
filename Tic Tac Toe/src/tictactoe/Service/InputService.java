package tictactoe.Service;

import java.util.Scanner;

public class InputService {

    public Scanner scanner;

    public InputService(Scanner scanner) {
        this.scanner = scanner;
    }

    public InputService() {
        this.scanner = new Scanner(System.in);
    }

    public String getStringInput() {
        return this.scanner.next();
    }

    public int getIntInput(){
        return this.scanner.nextInt();
    }

}

package tictactoe.Interfaces;

public interface IBoardService {

    public abstract boolean isDraw();

    public abstract boolean isImpossible();

    public abstract boolean isWinning(char player);

    public abstract boolean isCoordinateWithinRange(int x, int y);

    public int countX();

    public int countO();

}

import java.util.Scanner;

public class HumanPlayer implements Player {
    public Scanner scanner = new Scanner(System.in); // TODO close this scanner

    @Override
    // THe method from the Player interface
    public Player.MoveCoords nextMove(Game game, Game.Mark assignedMark) {
        var row = 0;
        var col = 0;

        while (true) {
            try {
                System.out.print(assignedMark.prettyStringBG() + "'s turn > ");
                row = Integer.parseInt(scanner.next());
                col = Integer.parseInt(scanner.next());
                if (row < 1 || row > 3) {
                    throw new IllegalArgumentException();
                }
                if (col < 1 || col > 3) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (NumberFormatException e) {
                // for when Integer.parseInt() fails
                System.out.println("Please input two valid numbers, in format \"row column\"");
            } catch (IllegalArgumentException e) {
                // for when row or col are invalid
                System.out.println("Row and column must be between 1 and 3");
            }
        }

        return new MoveCoords(row - 1, col - 1);
    }
}

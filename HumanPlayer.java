import java.util.Scanner;

public class HumanPlayer extends BasePlayer {
    Scanner scanner = new Scanner(System.in);
    String name;

    public HumanPlayer(String name) {
        this.name = name;
    }

    @Override
    // THe method from the Player interface
    public Player.Move nextMove(Game game) {
        var row = 0;
        var col = 0;

        while (true) {
            try {
                System.out.print(this.mark.prettyStringBG() + "'s [" + this.name + "] turn > ");

                var input = scanner.next().strip().toLowerCase();
                if (input.equals("surrender")) {
                    return new Move(0, 0, true);
                }
                row = Integer.parseInt(input);

                input = scanner.next().strip().toLowerCase();
                if (input.equals("surrender")) {
                    return new Move(0, 0, true);
                }
                col = Integer.parseInt(input);

                if (row < 1 || row > 3) {
                    System.out.println("Row and column must be between 1 and 3");
                    continue;
                }
                if (col < 1 || col > 3) {
                    System.out.println("Row and column must be between 1 and 3");
                    continue;
                }
                if (game.grid[row - 1][col - 1] != Mark.EMPTY) {
                    System.out.println("Cell is already occupied, pick another position!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                // for when Integer.parseInt() fails
                System.out.println("Please input two valid numbers, in format \"row column\"");
            }
        }

        return new Move(row - 1, col - 1, false);
    }

    @Override
    public void handleSituation(Game game, Move move, Game.Situation situation, boolean myTurn) {
        // we don't need to do anything here.
    }

    @Override
    public String getName() {
        return this.name;
    }
}

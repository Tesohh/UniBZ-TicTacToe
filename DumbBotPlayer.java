import java.util.Random;

// this bot doesn't have any strategy.
// it just chooses a random empty cell.
public class DumbBotPlayer implements Player {
    Random random = new Random();

    @Override
    public Player.Move nextMove(Game game, Mark assignedMark) {
        int row;
        int col;
        do {
            row = random.nextInt(0, 3);
            col = random.nextInt(0, 3);
        } while (game.grid[row][col] != Mark.EMPTY);

        System.out.println(assignedMark.prettyStringBG() + "'s [BOT] turn > " + row + " " + col);
        return new Move(row, col, false);
    }
}

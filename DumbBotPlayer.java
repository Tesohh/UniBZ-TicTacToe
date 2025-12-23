import java.util.Random;

// this bot doesn't have any strategy.
// it just chooses a random empty cell.
public class DumbBotPlayer extends BasePlayer {
    Random random = new Random();

    @Override
    public Player.Move nextMove(Game game) {
        int row;
        int col;
        do {
            row = random.nextInt(0, 3);
            col = random.nextInt(0, 3);
        } while (game.grid[row][col] != Mark.EMPTY);

        System.out.println(this.mark.prettyStringBG() + "'s [BOT] turn > " + row + " " + col);
        return new Move(row, col, false);
    }

    @Override
    public void handleSituation(Game game, Move move, Game.Situation situation, boolean myTurn) {
        // we don't need to do anything here.
    }

    @Override
    public String getName() {
        return "normal bot";
    }
}

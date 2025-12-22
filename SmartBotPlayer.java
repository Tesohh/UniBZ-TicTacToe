import java.util.ArrayList;
import java.util.Random;

// this bot chooses a random empty cell, 
// and checks whether it is a combination that led to a loss in the past.
public class SmartBotPlayer extends BasePlayer {
    Random random = new Random();

    ArrayList<Mark[][]> losing_combinations = new ArrayList<Mark[][]>();

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
    public void handleSituation(Game game, Game.Situation situation, boolean myTurn) {
        var someoneWon = situation == Game.Situation.PLAYER1_WIN || situation == Game.Situation.PLAYER2_WIN;
        if (someoneWon && !myTurn) {
            // i lost. add the combination as a losing combination
            System.out.println("dbg: HI IM " + this.mark + " AND I LOST");
        }
    }
}

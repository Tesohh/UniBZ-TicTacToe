import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// this bot chooses a random empty cell, 
// and checks whether it is a combination that led to a loss in the past.
public class SmartBotPlayer extends BasePlayer {
    Random random = new Random();

    ArrayList<Mark[][]> losingGrids = new ArrayList<Mark[][]>(); // list of losing grids

    @Override
    public Player.Move nextMove(Game game) {
        int row;
        int col;
        boolean hasWinningCombination = false;
        outer: while (true) {
            row = random.nextInt(0, 3);
            col = random.nextInt(0, 3);

            // is the selected cell empty? if not, try another move
            if (game.grid[row][col] != Mark.EMPTY) {
                continue;
            }

            // simulate the grid, would this move get you to a losing grid?
            // in that case, try another move
            var simulation = cloneGrid(game);
            simulation[row][col] = this.mark;
            for (var grid : losingGrids) {
                if (Arrays.deepEquals(simulation, grid)) {
                    // we need to use deepEqual as `==` would just check if the addresses are
                    // the same...
                    continue outer;
                }
            }

            hasWinningCombination = true;
            break;
        }

        if (!hasWinningCombination) {
            // surrender!
            System.out.println(this.mark.prettyStringBG() + " [BOT] has surrendered!");
            return new Move(0, 0, true);
        }

        System.out.println(this.mark.prettyStringBG() + "'s [BOT] turn > " + row + " " + col);
        return new Move(row, col, false);
    }

    @Override
    public void handleSituation(Game game, Move move, Game.Situation situation, boolean myTurn) {
        var someoneWon = situation == Game.Situation.PLAYER1_WIN || situation == Game.Situation.PLAYER2_WIN;
        if (someoneWon && !myTurn) {
            // i lost. add the combination as a losing combination
            var newGrid = cloneGrid(game);
            newGrid[move.row()][move.col()] = Mark.EMPTY;
            losingGrids.add(newGrid);
        }
    }

    Mark[][] cloneGrid(Game game) {
        Mark[][] newGrid = new Mark[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                newGrid[row][col] = game.grid[row][col];
            }
        }
        return newGrid;
    }

    void debugGrid(Mark[][] grid) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(grid[row][col]);
            }
            System.out.println();
        }
    }

    @Override
    public String getName() {
        return "smart bot";
    }
}

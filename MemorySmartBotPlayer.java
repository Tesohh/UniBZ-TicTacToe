import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Behaves exactly like SmartBotPlayer, but it remembers past losses from past executions using a file .loses.dat
public class MemorySmartBotPlayer extends SmartBotPlayer {
    final String filename = ".loses.dat";

    @Override
    public void handleSituation(Game game, Move move, Game.Situation situation, boolean myTurn) {
        var someoneWon = situation == Game.Situation.PLAYER1_WIN || situation == Game.Situation.PLAYER2_WIN;
        if (someoneWon && !myTurn) {
            // i lost. add the combination as a losing combination
            var newGrid = cloneGrid(game);
            newGrid[move.row()][move.col()] = Mark.EMPTY;
            losingGrids.add(newGrid);

            // AND save it to a file..
            // based on reference from: https://www.baeldung.com/java-write-to-file
            // code written by me
            try {
                // will write the grid in the format ???|???|???
                var writer = new BufferedWriter(new FileWriter(filename, true));
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        writer.append(newGrid[row][col].toString());
                    }
                    if (row < 2) {
                        writer.append("|");
                    }
                }
                writer.append("\n");
                writer.close();
            } catch (IOException e) {
                System.out.println("dbg: error while writing file: " + e);
            }
        }
    }
}

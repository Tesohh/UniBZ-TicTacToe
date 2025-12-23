import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// Behaves exactly like SmartBotPlayer, but it remembers past losses from past executions using a file .loses.dat
public class MemorySmartBotPlayer extends SmartBotPlayer {
    final String filename = ".loses.dat";

    public MemorySmartBotPlayer() {
        try {
            var scanner = new Scanner(new File(filename));

            lineloop: while (scanner.hasNextLine()) {
                var grid = new Mark[3][3];
                try {
                    var line = scanner.nextLine();
                    for (int row = 0; row < 3; row++) {
                        for (int col = 0; col < 3; col++) {
                            var ch = line.charAt((3 * row) + col);
                            if (ch == 'X') {
                                grid[row][col] = Mark.X;
                            } else if (ch == 'O') {
                                grid[row][col] = Mark.O;
                            } else if (ch == ' ') {
                                grid[row][col] = Mark.EMPTY;
                            } else {
                                continue lineloop; // an invalid character is found, ignore the whole line
                            }
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    continue; // ignore the invalid line
                }

                this.losingGrids.add(grid);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // no need to do anything. just move on..
        }
    }

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
                // will write the grid on a single line, every 3 chars its a new row in the grid
                var writer = new BufferedWriter(new FileWriter(filename, true));
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        writer.append(newGrid[row][col].toString());
                    }
                }
                writer.append("\n");
                writer.close();
            } catch (IOException e) {
                // hide the error
            }
        }
    }

    @Override
    public String getName() {
        return "memory smart bot";
    }
}

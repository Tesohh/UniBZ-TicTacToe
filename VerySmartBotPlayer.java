import java.util.Random;

class VerySmartBotPlayer extends BasePlayer {
    Random random = new Random();
    float difficulty;

    /** difficulty is a float from 0-1 */
    public VerySmartBotPlayer(float difficulty) {
        this.difficulty = difficulty;
    }

    public VerySmartBotPlayer() {
        this.difficulty = 1;
    }

    @Override
    public Player.Move nextMove(Game game) {
        // FIRST: see if the difficulty allows us to do a good move
        // if a random float is bigger than the difficulty,
        // pick a random move, in DumbBotPlayer fashion
        if (random.nextFloat() > this.difficulty) {
            System.out.println("dbg: picked EASY");
            return randomMove(game);
        }

        // NEXT: check if me or opponent has winning condition
        // check all rows and determine if there is a winning condition
        for (int row = 0; row < 3; row++) {
            int winCondition = checkWinningCondition(game.grid[row][0], game.grid[row][1], game.grid[row][2]);
            if (winCondition == -1)
                continue;
            else {
                System.out.println("dbg: picked ROW win condition");
                displayMove(row, winCondition);
                return new Move(row, winCondition, false);
            }
        }

        // check all cols and determine if there is a winning condition
        for (int col = 0; col < 3; col++) {
            int winCondition = checkWinningCondition(game.grid[0][col], game.grid[1][col], game.grid[2][col]);
            if (winCondition == -1)
                continue;
            else {
                System.out.println("dbg: picked COL win condition");
                displayMove(winCondition, col);
                return new Move(winCondition, col, false);
            }
        }

        // check diagonals and determine if there is a winning condition
        int winCondition = checkWinningCondition(game.grid[0][0], game.grid[1][1], game.grid[2][2]);
        if (winCondition != -1) {
            System.out.println("dbg: picked DIAG TOPLEFT win condition");
            displayMove(winCondition, winCondition);
            return new Move(winCondition, winCondition, false);
        }

        winCondition = checkWinningCondition(game.grid[0][2], game.grid[1][1], game.grid[2][0]);
        if (winCondition != -1) {
            System.out.println("dbg: picked DIAG TOPRIGHT win condition");
            displayMove(winCondition, 2 - winCondition);
            return new Move(winCondition, 2 - winCondition, false);
        }

        // NEXT: was there no winning condition?
        // check if the center is empty, if it is place there
        if (game.grid[1][1] == Mark.EMPTY) {
            System.out.println("dbg: picked CENTER");
            displayMove(1, 1);
            return new Move(1, 1, false);
        }

        // NEXT: try the corners
        for (var pair : new int[][] { { 0, 0 }, { 0, 2 }, { 2, 0 }, { 2, 2 } }) {
            System.out.println("dbg: picked CORNER");
            if (game.grid[pair[0]][pair[1]] == Mark.EMPTY) {
                return new Move(pair[0], pair[1], false);
            }
        }

        // NEXT: was the center cell, and all corners marked?
        // if it was, pick a random spot

        var move = randomMove(game);
        displayMove(move.row(), move.col());
        return move;
    }

    /**
     * returns -1 if there is no win condition, an integer 0..3 if there is a win
     */
    int checkWinningCondition(Mark m0, Mark m1, Mark m2) {
        var xCount = 0;
        var oCount = 0;
        var emptyCount = 0;
        var emptyPos = 0;

        var marks = new Mark[] { m0, m1, m2 };
        for (int i = 0; i < 3; i++) {
            switch (marks[i]) {
                case EMPTY:
                    emptyCount += 1;
                    emptyPos = i;
                    break;
                case O:
                    oCount += 1;
                    break;
                case X:
                    xCount += 1;
                    break;
                default:
                    break;
            }
        }

        var hasWin = (xCount == 2 && emptyCount == 1) || (oCount == 2 && emptyCount == 1);
        if (hasWin) {
            return emptyPos;
        } else {
            return -1;
        }
    }

    Move randomMove(Game game) {
        int row;
        int col;
        do {
            row = random.nextInt(0, 3);
            col = random.nextInt(0, 3);
        } while (game.grid[row][col] != Mark.EMPTY);

        displayMove(row, col);
        return new Move(row, col, false);
    }

    void displayMove(int row, int col) {
        System.out.println(this.mark.prettyStringBG() + "'s [BOT] turn > " + row + " " + col);
    }

    @Override
    public void handleSituation(Game game, Player.Move move, Game.Situation situation, boolean myTurn) {
        // don't need to do anything here
    }

    @Override
    public String getName() {
        return "very smart bot";
    }
}

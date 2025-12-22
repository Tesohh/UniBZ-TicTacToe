public class Game {
    // describes situations that can occur after a turn
    public enum Situation {
        NOTHING,
        PLAYER1_WIN,
        PLAYER2_WIN,
        DRAW;
    }

    public Mark[][] grid;
    int turns = 0; // when even = player 1's turn, when odd = player 2's turn

    Player player1;
    Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        this.player1.setMark(Mark.X);
        this.player2.setMark(Mark.O);

        this.grid = new Mark[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.grid[i][j] = Mark.EMPTY;
            }
        }
    }

    public Situation nextTurn() {
        Player.Move move;
        Mark mark;
        if (turns % 2 == 0) {
            // turns is even, so its player 1 (X) turn
            move = this.player1.nextMove(this);
            mark = this.player1.getMark();
            if (move.surrender()) {
                return Situation.PLAYER2_WIN;
            }
        } else {
            // turns is odd, so its player 2 (O) turn
            move = this.player2.nextMove(this);
            mark = this.player2.getMark();
            if (move.surrender()) {
                return Situation.PLAYER1_WIN;
            }
        }

        grid[move.row()][move.col()] = mark;

        // tell the players the current situation and if it's their turn
        var situation = checkWin(mark);
        player1.handleSituation(this, situation, turns % 2 == 0);
        player2.handleSituation(this, situation, turns % 2 != 0);

        this.turns += 1;
        return situation;
    }

    Situation checkWin(Mark mark) throws IllegalArgumentException {
        // if a win is detected, who would it be?
        Situation candidateWinner;
        if (mark == Mark.X) {
            candidateWinner = Situation.PLAYER1_WIN;
        } else if (mark == Mark.O) {
            candidateWinner = Situation.PLAYER2_WIN;
        } else {
            throw new IllegalArgumentException(
                    "invalid argument passed to checkWin. Can either be Mark.X or Mark.O. Should be unreachable.");
        }

        // check wins for `mark` (we don't need to check the only mark.)
        // check for all rows, if one has all cells == mark
        for (int row = 0; row < 3; row++) {
            var allMark = true;
            for (int col = 0; col < 3; col++) {
                if (this.grid[row][col] != mark) {
                    allMark = false;
                    break;
                }
            }
            if (allMark) {
                return candidateWinner;
            }
        }
        // check for all columns, if one has all cells == mark
        for (int col = 0; col < 3; col++) {
            var allMark = true;
            for (int row = 0; row < 3; row++) {
                if (this.grid[row][col] != mark) {
                    allMark = false;
                    break;
                }
            }
            if (allMark) {
                return candidateWinner;
            }
        }

        // check diagonal case
        // X..
        // .X.
        // ..X
        if (this.grid[0][0] == mark && this.grid[1][1] == mark && this.grid[2][2] == mark) {
            return candidateWinner;
        }

        // ..X
        // .X.
        // X..
        if (this.grid[0][2] == mark && this.grid[1][1] == mark && this.grid[2][0] == mark) {
            return candidateWinner;
        }

        // check draw
        var noEmptys = true;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (this.grid[row][col] == Mark.EMPTY) {
                    noEmptys = false;
                    break;
                }
            }
        }

        // if there are no empty cells, it means that all cells are full.
        // if there was a win at this point we would have already caught it, so this
        // means that there was a draw
        if (noEmptys) {
            return Situation.DRAW;
        }

        // nothing happens. just go on with the game
        return Situation.NOTHING;
    }

    // prints out a beautiful version of the grid
    // using the characters defined in Box class
    // TODO fix formatting1
    public void display() {
        System.out.println(ANSI.DIM + "    1   2   3" + ANSI.RESET);
        System.out.println(
                "  " + Box.TOP_LEFT + Box.HORIZONTAL.repeat(3) + Box.TOP_DOWN + Box.HORIZONTAL.repeat(3) + Box.TOP_DOWN
                        + Box.HORIZONTAL.repeat(3) + Box.TOP_RIGHT);
        System.out.println(
                ANSI.DIM + "1 " + ANSI.RESET + Box.VERTICAL + this.grid[0][0].prettyStringFGSurround() + Box.VERTICAL
                        + this.grid[0][1].prettyStringFGSurround() + Box.VERTICAL
                        + this.grid[0][2].prettyStringFGSurround() + Box.VERTICAL);
        System.out.println(
                "  " + Box.VERT_RIGHT + Box.HORIZONTAL.repeat(3) + Box.CROSS + Box.HORIZONTAL.repeat(3) + Box.CROSS
                        + Box.HORIZONTAL.repeat(3)
                        + Box.VERT_LEFT);
        System.out.println(
                ANSI.DIM + "2 " + ANSI.RESET + Box.VERTICAL + this.grid[1][0].prettyStringFGSurround() + Box.VERTICAL
                        + this.grid[1][1].prettyStringFGSurround() + Box.VERTICAL
                        + this.grid[1][2].prettyStringFGSurround() + Box.VERTICAL);
        System.out.println(
                "  " + Box.VERT_RIGHT + Box.HORIZONTAL.repeat(3) + Box.CROSS + Box.HORIZONTAL.repeat(3) + Box.CROSS
                        + Box.HORIZONTAL.repeat(3)
                        + Box.VERT_LEFT);
        System.out.println(
                ANSI.DIM + "3 " + ANSI.RESET + Box.VERTICAL + this.grid[2][0].prettyStringFGSurround() + Box.VERTICAL
                        + this.grid[2][1].prettyStringFGSurround() + Box.VERTICAL
                        + this.grid[2][2].prettyStringFGSurround() + Box.VERTICAL);
        System.out.println("  " +
                Box.BOTTOM_LEFT + Box.HORIZONTAL.repeat(3) + Box.BOTTOM_UP + Box.HORIZONTAL.repeat(3) + Box.BOTTOM_UP
                + Box.HORIZONTAL.repeat(3) + Box.BOTTOM_RIGHT);
    }
}

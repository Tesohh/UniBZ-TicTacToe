public class Game {
    public enum Mark {
        EMPTY, // no mark placed
        X, // player 1
        O; // player 2

        // properly formats the mark when printing etc.
        public String toString() {
            switch (this) {
                case EMPTY:
                    return " ";
                case X:
                    return "X";
                case O:
                    return "O";
                default:
                    return "?";
            }
        }

        public String prettyStringBG() {
            switch (this) {
                case X:
                    return ANSI.FG_BLACK + ANSI.BG_BLUE + " X " + ANSI.RESET;
                case O:
                    return ANSI.FG_BLACK + ANSI.BG_RED + " O " + ANSI.RESET;
                default:
                    return "?";
            }
        }
    }

    // describes situations that can occur after a turn
    public enum Situation {
        Nothing,
        Player1Win,
        Player2Win,
        Draw;
    }

    public Mark[][] grid;
    public int turns = 0; // when even = player 1's turn, when odd = player 2's turn

    public Player player1;
    public Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        this.grid = new Mark[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.grid[i][j] = Mark.EMPTY;
            }
        }
    }

    public Situation nextTurn() {
        Mark mark;
        Player.Move move;
        if (turns % 2 == 0) {
            // turns is even, so its player 1 (X) turn
            mark = Mark.X;
            move = this.player1.nextMove(this, mark);
            if (move.surrender()) {
                return Situation.Player2Win;
            }
        } else {
            // turns is odd, so its player 2 (O) turn
            mark = Mark.O;
            move = this.player2.nextMove(this, mark);
            if (move.surrender()) {
                return Situation.Player1Win;
            }
        }

        this.turns += 1;

        var noEmptys = true;
        for (var row : this.grid) {
            for (var cell : row) {
                if (cell == Mark.EMPTY) {
                    noEmptys = false;
                    break;
                }
            }
        }

        // if there are no empty cells, it means that all cells are full.
        // if there was a win at this point we would have already caught it, so this
        // means that there was a draw
        if (noEmptys) {
            return Situation.Draw;
        }

        return Situation.Nothing;
    }

    // prints out an ugly version of the grid for debug purposes
    public void debug() {
        for (var row : this.grid) {
            for (var cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}

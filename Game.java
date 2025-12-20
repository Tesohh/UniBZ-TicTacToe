public class Game {
    public enum Mark {
        None, // the cell is empty
        X, // player 1
        O; // player 2

        // properly formats the mark when printing etc.
        public String toString() {
            switch (this) {
                case None:
                    return " ";
                case O:
                    return "O";
                case X:
                    return "X";
                default:
                    return "?";
            }
        }
    }

    public Mark[][] grid;
    public int turns = 0; // when even = player 1's turn, when odd = player 2's turn

    public Game() {
        this.grid = new Mark[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = Mark.None;
            }
        }
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

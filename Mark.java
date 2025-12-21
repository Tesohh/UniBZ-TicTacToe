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

    public String prettyStringFG() {
        switch (this) {
            case X:
                return ANSI.FG_BLUE + "X" + ANSI.RESET;
            case O:
                return ANSI.FG_RED + "O" + ANSI.RESET;
            case EMPTY:
                return " ";
            default:
                return "?";
        }
    }

    // prettyStringFG() surrounded by spaces. used when printing the grid
    public String prettyStringFGSurround() {
        return " " + this.prettyStringFG() + " ";
    }
}

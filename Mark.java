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
                return ANSI.style(ANSI.FG_BLACK, ANSI.BG_BLUE, " X ");
            case O:
                return ANSI.style(ANSI.FG_BLACK, ANSI.BG_RED, " O ");
            default:
                return "?";
        }
    }

    public String prettyStringFG() {
        switch (this) {
            case X:
                return ANSI.style(ANSI.FG_BLUE, " X ");
            case O:
                return ANSI.style(ANSI.FG_RED, " O ");
            case EMPTY:
                return "   ";
            default:
                return "?";
        }
    }
}

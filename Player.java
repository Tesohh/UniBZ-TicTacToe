/*
 * i decided to use an interface so that i can write my code for the different
 * kinds of players, without having to care which kind of player it is in the game class.
 * - human
 * - computer with no strategy,
 * - computer that learns
 * - BONUS smart algorithm
 *
 * for example the human player would ask for the coordinates in input,
 * the computers would pick a move automatically etc.
 * this way, we can also do experiments to let two bots play against eachother
 */

interface Player {
    // record reference used: https://www.baeldung.com/java-record-keyword
    public record Move(int row, int col, boolean surrender) {
    }

    /**
     * implementers shall return a move
     * implementers can also use `game` to check the current state of the game
     * WARNING: it's up to implementers to check if the coordinates provided by
     * users are valid!
     */
    public Move nextMove(Game game);

    public void handleSituation(Game game, Move move, Game.Situation situation, boolean myTurn);

    public void setMark(Mark mark);

    public Mark getMark();
}

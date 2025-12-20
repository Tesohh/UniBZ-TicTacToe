/**
 * i decided to use an interface so that i can write my code for the different
 * kinds of players
 * (human, computer with no strategy,
 * computer that learns, +BONUS smart algorithm)
 */
interface Player {
    // record reference used: https://www.baeldung.com/java-record-keyword
    public record MoveCoords(int row, int col) {
    }

    /**
     * implementers shall return a move as (row, col) pair representing the cell to
     * place a mark on
     * implementers can also use `game` to check the current state of the game
     */
    public MoveCoords nextMove(Game game);
}

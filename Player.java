/**
 * i decided to use an interface so that i can write my code for the different
 * kinds of players
 * (human, computer with no strategy,
 * computer that learns, +BONUS smart algorithm)
 * for example the human player would ask for the coordinates in input,
 * the computers would pick a move automatically etc.
 * this way, we can also do experiments to let two bots play against eachother
 */
interface Player {
    // record reference used: https://www.baeldung.com/java-record-keyword
    public record MoveCoords(int row, int col) {
    }

    /**
     * implementers shall return a move as (row, col) pair representing the cell to
     * place a mark on.
     * implementers can also use `game` to check the current state of the game,
     * and `assignedMark` for display purposes.
     * WARNING: it's up to implementers to check if the coordinates are valid!
     */
    public MoveCoords nextMove(Game game, Game.Mark assignedMark);
}

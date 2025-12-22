/*
Student Code Ethics
Students are expected to maintain the highest standards of
academic integrity. Work that is not of the student's own
creation will receive no credit. Remember that you cannot
give or receive unauthorized aid on any assignment, quiz, or
exam. A student cannot use the ideas of another and declare
it as his or her own. Students are required to properly cite
the original source of the ideas and information used in his
or her work.
*/

/* USAGE:
* no parameters: human vs human
* -n: human vs (normal / dull / dumb bot)
* -s: human vs (smart bot)
* -m: human vs (smart memory bot)
* -S: human vs (very smart bot)
* --{flag1}-{flag2}: {flag} vs {flag} 
    * eg. --S-s: (very smart) vs smart
    * or  --S-S: (very smart) vs (very smart)
*/
class Main {
    public static void main(String[] args) {
        System.out.println("programmed by: " + ANSI.FG_BLACK + ANSI.BG_PURPLE + " Tesini Simone " + ANSI.RESET);
        var player1 = new HumanPlayer();
        var player2 = new DumbBotPlayer();

        var game = new Game(player1, player2);

        while (true) {
            game.display();
            var situation = game.nextTurn();
            if (situation != Game.Situation.NOTHING) {
                System.out.println("dbg: " + situation);
                break;
            }
            System.out.println();
        }

    }
}

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

        Player player1;
        Player player2;

        String player1name;
        String player2name;
        if (args.length < 1) {
            player1 = new HumanPlayer();
            player2 = new HumanPlayer();
            player1name = "human";
            player2name = "human";
        } else if (args[0].equals("-n")) {
            player1 = new HumanPlayer();
            player2 = new DumbBotPlayer();
            player1name = "human";
            player2name = "normal bot";
        } else {
            player1 = new HumanPlayer();
            player2 = new HumanPlayer();
            player1name = "human";
            player2name = "human";
            System.out.println("unknown argument: " + args[0]);
        }

        System.out.println("programmed by " + ANSI.FG_BLACK + ANSI.BG_PURPLE + " Tesini Simone " + ANSI.RESET);
        System.out.printf("started in    %s %s %s vs %s %s %s mode\n", ANSI.FG_BLACK + ANSI.BG_BLUE, player1name,
                ANSI.RESET,
                ANSI.FG_BLACK + ANSI.BG_RED, player2name, ANSI.RESET);
        System.out.println();

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

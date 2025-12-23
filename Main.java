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
* -S-{percentage}: human vs (very smart bot) with specified 0-100 difficulty (0% difficulty = dumb bot) 
*
* specify two flags to pick both players (eg. smart bot vs very smart bot at 20% difficulty = -s -S-20)
*/
class Main {
    public static void main(String[] args) {
        Player player1;
        Player player2;

        if (args.length == 1) {
            player1 = new HumanPlayer();
            player2 = playerFromFlag(args[0]);
        } else if (args.length == 2) {
            player1 = playerFromFlag(args[0]);
            player2 = playerFromFlag(args[1]);
        } else {
            player1 = new HumanPlayer();
            player2 = new HumanPlayer();
        }

        System.out.println("programmed by " + ANSI.FG_BLACK + ANSI.BG_PURPLE + " Tesini Simone " + ANSI.RESET);
        System.out.printf("started in    %s %s %s vs %s %s %s mode\n", ANSI.FG_BLACK + ANSI.BG_BLUE, player1.getName(),
                ANSI.RESET,
                ANSI.FG_BLACK + ANSI.BG_RED, player2.getName(), ANSI.RESET);
        System.out.println();

        int player1Wins = 0;
        int player2Wins = 0;

        while (true) { // every iteration is a new game
            var game = new Game(player1, player2);
            // TODO ask for input...
            while (true) { // every iteration is a new turn
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

    static Player playerFromFlag(String flag) {
        if (flag.equals("-n")) {
            return new DumbBotPlayer();
        } else if (flag.equals("-s")) {
            return new SmartBotPlayer();
        } else if (flag.equals("-m")) {
            return new MemorySmartBotPlayer();
        } else if (flag.equals("-S")) {
            return new VerySmartBotPlayer();
        } else if (flag.startsWith("-S-")) {
            var maybePercentage = flag.substring(3);
            try {
                int percentage = Integer.parseInt(maybePercentage);
                if (percentage < 0) {
                    percentage = 0;
                } else if (percentage > 100) {
                    percentage = 100;
                }
                return new VerySmartBotPlayer((float) percentage / 100);
            } catch (NumberFormatException e) {
                return new VerySmartBotPlayer();
            }
        } else {
            return new HumanPlayer();
        }
    }
}

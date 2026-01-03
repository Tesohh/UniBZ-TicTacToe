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
* -S-{percentage}: human vs (very smart bot) with specified 0-100 difficulty 
* (0% difficulty = dumb bot, 100% difficulty = almost unbeatable)
*
* specify two flags to pick both players (eg. smart bot vs very smart bot at 20% difficulty = -s -S-20)
*/

import java.util.Random;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        System.out.printf("welcome to tic tac toe!\n\nprogrammed by %s\n",
                ANSI.style(ANSI.FG_BLACK, ANSI.BG_PURPLE, " Tesini Simone "));
        var scanner = new Scanner(System.in);

        var players = parsePlayers(args, scanner);
        Player player1 = players[0];
        Player player2 = players[1];

        System.out.printf("started in    %s vs %s mode\n",
                ANSI.style(ANSI.FG_BLACK, ANSI.BG_BLUE, " " + player1.getName() + " "),
                ANSI.style(ANSI.FG_BLACK, ANSI.BG_RED, " " + player2.getName() + " "));
        System.out.println();

        int player1Wins = 0;
        int player2Wins = 0;

        while (true) { // every iteration is a new game
            var game = new Game(player1, player2);
            // to randomly select which mark starts, randomly choose if it should increment
            // turns by 1

            if (player1Wins > 0 || player2Wins > 0) {
                System.out.printf("Score:  %s %d : %d %s\n",
                        Mark.X.prettyStringBG(),
                        player1Wins, player2Wins,
                        Mark.O.prettyStringBG());
            }
            System.out.printf("%slay  %suit  %snstructions\n",
                    ANSI.style(ANSI.FG_BLACK, ANSI.BG_WHITE, " P "),
                    ANSI.style(ANSI.FG_BLACK, ANSI.BG_WHITE, " Q "),
                    ANSI.style(ANSI.FG_BLACK, ANSI.BG_WHITE, " I "));
            System.out.print("> ");

            // use a full line instead of just a byte so that users can also type "play" or
            // "instructions" or "quit" or whatever
            var input = scanner.nextLine().toLowerCase();

            if (input.length() >= 1) {
                if (input.charAt(0) == 'q') {
                    scanner.close();
                    return; // close the game..
                } else if (input.charAt(0) == 'i') {
                    System.out.println(INSTRUCTIONS);
                    continue;
                } else if (input.charAt(0) != 'p') {
                    System.out.println("invalid command, try again!");
                    continue;
                }
            }

            // in any other case (user just pressed enter, or pressed p), play the game..

            while (true) { // every iteration is a new turn
                game.display();
                var situation = game.nextTurn();
                if (situation == Game.Situation.PLAYER1_WIN) {
                    game.display();
                    player1Wins += 1;
                    System.out.printf("%s wins!\n\n", Mark.X.prettyStringBG());
                    break;
                } else if (situation == Game.Situation.PLAYER2_WIN) {
                    game.display();
                    player2Wins += 1;
                    System.out.printf("%s wins!\n\n", Mark.O.prettyStringBG());
                    break;
                } else if (situation == Game.Situation.DRAW) {
                    game.display();
                    System.out.printf("%s\n\n", ANSI.style(ANSI.FG_BLACK, ANSI.BG_WHITE, "Draw!"));
                    break;
                }
                System.out.println();
            }
        }
    }

    static Player[] parsePlayers(String[] args, Scanner scanner) {
        Player player1;
        Player player2;

        if (args.length == 1) {
            player1 = new HumanPlayer("you");
            player2 = playerFromFlag(args[0]);
        } else if (args.length == 2 && !(args[0].equals("-h") && args[1].equals("-h"))) {
            player1 = playerFromFlag(args[0]);
            player2 = playerFromFlag(args[1]);
        } else {
            System.out.print("First player's name: ");
            var nameA = scanner.nextLine();
            System.out.print("Second player's name: ");
            var nameB = scanner.nextLine();

            var playerA = new HumanPlayer(nameA);
            var playerB = new HumanPlayer(nameB);

            var random = new Random();
            if (random.nextBoolean()) {
                player1 = playerA;
                player2 = playerB;
            } else {
                player1 = playerB;
                player2 = playerA;
            }
        }

        return new Player[] { player1, player2 };
    }

    static Player playerFromFlag(String flag) {
        if (flag.equals("-n")) {
            return new NormalBotPlayer();
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
            return new HumanPlayer("human");
        }
    }

    static final String INSTRUCTIONS = String.format("""
            This is a simple %s game.
            To play, start the program, press %s, or %s then %s.
            On every turn, you must type the %s, from 1 to 3, where you want to place your mark, and press %s.
            You can also type "surrender" to surrender.

            If you haven't specified any flags when starting the program, this will make 2 humans play together.
            You can use flags when running the program to choose a bot to play against:
            * %s: human vs normal bot %s
            * %s: human vs smart bot %s
            * %s: human vs smart memory bot %s
            * %s: human vs very smart bot %s

            You can also specify a percentage when playing against the very smart bot,
            by following the %s with a percentage like: %s to get 40%% difficulty.

            You can also let two bots play against eachother, by specifying two flags, like %s.
            If you need to force two humans to play against eachother, use %s.
                            """,
            ANSI.style(ANSI.FG_PURPLE, "Tic Tac Toe"),
            ANSI.style(ANSI.FG_PURPLE, "[Enter]"),
            ANSI.style(ANSI.FG_PURPLE, "[P]"),
            ANSI.style(ANSI.FG_PURPLE, "[Enter]"),
            ANSI.style(ANSI.FG_PURPLE, "row and column"),
            ANSI.style(ANSI.FG_PURPLE, "[Enter]"),
            ANSI.style(ANSI.FG_PURPLE, "-n"),
            ANSI.style(ANSI.DIM, "(with no strategy)"),
            ANSI.style(ANSI.FG_PURPLE, "-s"),
            ANSI.style(ANSI.DIM, "(that learns from past matches)"),
            ANSI.style(ANSI.FG_PURPLE, "-m"),
            ANSI.style(ANSI.DIM, "(that learns from past matches and remembers them after quitting the game)"),
            ANSI.style(ANSI.FG_PURPLE, "-S"),
            ANSI.style(ANSI.DIM, "(which has a custom unbeatable algorithm)"),
            ANSI.style(ANSI.FG_PURPLE, "-S"),
            ANSI.style(ANSI.FG_PURPLE, "-S-40"),
            ANSI.style(ANSI.FG_PURPLE, "-S-40 -m"),
            ANSI.style(ANSI.FG_PURPLE, "-h -h"));
}

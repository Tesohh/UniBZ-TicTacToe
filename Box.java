// special characters for drawing boxes
// based on reference from: https://en.wikipedia.org/wiki/Box-drawing_characters
// code written by me
public class Box {
    public static final String TOP_LEFT = "╭";
    public static final String TOP_RIGHT = "╮";
    public static final String BOTTOM_LEFT = "╰";
    public static final String BOTTOM_RIGHT = "╯";

    public static final String VERT_RIGHT = "├";
    public static final String VERT_LEFT = "┤";
    public static final String TOP_DOWN = "┬";
    public static final String BOTTOM_UP = "┴";
    public static final String CROSS = "┼";

    public static final String HORIZONTAL = "─";
    public static final String VERTICAL = "│";

    // displays an empty tictactoe grid for testing purposes
    public static void debug() {
        System.out.println(
                TOP_LEFT + HORIZONTAL.repeat(3) + TOP_DOWN + HORIZONTAL.repeat(3) + TOP_DOWN
                        + HORIZONTAL.repeat(3) + TOP_RIGHT);
        System.out.println(VERTICAL + "   " + VERTICAL + "   " + VERTICAL + "   " + VERTICAL);
        System.out.println(VERT_RIGHT + HORIZONTAL.repeat(3) + CROSS + HORIZONTAL.repeat(3) + CROSS
                + HORIZONTAL.repeat(3)
                + VERT_LEFT);
        System.out.println(VERTICAL + "   " + VERTICAL + "   " + VERTICAL + "   " + VERTICAL);
        System.out.println(VERT_RIGHT + HORIZONTAL.repeat(3) + CROSS + HORIZONTAL.repeat(3) + CROSS
                + HORIZONTAL.repeat(3)
                + VERT_LEFT);
        System.out.println(VERTICAL + "   " + VERTICAL + "   " + VERTICAL + "   " + VERTICAL);
        System.out.println(
                BOTTOM_LEFT + HORIZONTAL.repeat(3) + BOTTOM_UP + HORIZONTAL.repeat(3) + BOTTOM_UP
                        + HORIZONTAL.repeat(3) + BOTTOM_RIGHT);
    }
}

// ANSI Codes to use for coloring text
// based on reference from: https://gist.github.com/JBlond/2fea43a3049b38287e5e9cefc87b2124
public class ANSI {
    public static final String RESET = "\033[0m";

    // foregrounds
    public static final String FG_BLACK = "\033[0;30m";
    public static final String FG_RED = "\033[0;31m";
    public static final String FG_GREEN = "\033[0;32m";
    public static final String FG_YELLOW = "\033[0;33m";
    public static final String FG_BLUE = "\033[0;34m";
    public static final String FG_PURPLE = "\033[0;35m";
    public static final String FG_CYAN = "\033[0;36m";
    public static final String FG_WHITE = "\033[0;37m";

    // backgrounds
    public static final String BG_BLACK = "\033[40m";
    public static final String BG_RED = "\033[41m";
    public static final String BG_GREEN = "\033[42m";
    public static final String BG_YELLOW = "\033[43m";
    public static final String BG_BLUE = "\033[44m";
    public static final String BG_PURPLE = "\033[45m";
    public static final String BG_CYAN = "\033[46m";
    public static final String BG_WHITE = "\033[47m";
}

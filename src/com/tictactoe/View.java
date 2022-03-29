package com.tictactoe;

/**
 * класс рисующий доску и ходы играков.
 */
public class View {

    public StringBuilder printBoard(char[][] chars) {
        int len = chars.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                sb.append(chars[i][j] + "|");
            }
            sb.replace(sb.length() - 1, sb.length(), System.lineSeparator());
            sb.append("-+".repeat(len));
            sb.replace(sb.length() - 1, sb.length(), System.lineSeparator());
        }
        sb.delete(sb.length() - 2 * len - 1, sb.length());
        return sb;
    }
}

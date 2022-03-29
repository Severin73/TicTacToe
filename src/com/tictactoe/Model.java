package com.tictactoe;

import java.util.ArrayList;
import java.util.List;

/**
 * класс описывающий общую модель игры.
 * Хранится ширина поля, ходы двух играков.
 * А так же все возможные проверки на валидность введенных параметров.
 * Проверка на окончание игры по строчкам, столбцам и диагоналям
 */
public class Model {
    private int n;
    private char[][] moves;
    private List<int[]> coordinateMoves = new ArrayList<>();
    private String winner;

    public Model() {
    }

    public Model(int n) {
        this.n = n;
        this.moves = setEmptyMoves(n);
    }

    public char[][] setEmptyMoves(int n) {
        char[][] charsArray = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                charsArray[i][j] = ' ';
            }
        }
        return charsArray;
    }

    public void setMove(int x, int y, char ch) {
        this.moves[x][y] = ch;
        this.coordinateMoves.add(new int[]{x, y});
    }

    public boolean isValid(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= n) {
            System.out.printf("Не верные координаты. Выберете число от 0 до %d%n", (n - 1));
            System.out.println();
            return false;
        }
        if (moves[x][y] != ' ') {
            System.out.println("Поле уже занято. Введите другие координаты");
            System.out.println();
            return false;
        }
        return true;
    }

    public boolean checkRow(int index) {
        int counterX = 0;
        int counterO = 0;
        for (int i = 0; i < n; i++) {
            if (moves[index][i] == 'X') {
                counterX++;
            }
            if (moves[index][i] == 'O') {
                counterO++;
            }
        }
        return counterO == n || counterX == n;
    }

    public boolean checkColumn(int index) {
        int counterX = 0;
        int counterO = 0;
        for (int i = 0; i < n; i++) {
            if (moves[i][index] == 'X') {
                counterX++;
            }
            if (moves[i][index] == 'O') {
                counterO++;
            }
        }
        return counterO == n || counterX == n;
    }

    public boolean checkDiagonal() {
        int counterXMain = 0;
        int counterOMain = 0;
        int counterXSecondary = 0;
        int counterOSecondary = 0;
        for (int i = 0; i < n; i++) {
            if (moves[i][i] == 'X') {
                counterXMain++;
            }
            if (moves[i][i] == 'O') {
                counterOMain++;
            }
            if (moves[i][n - i - 1] == 'X') {
                counterXSecondary++;
            }
            if (moves[i][n - i - 1] == 'O') {
                    counterOSecondary++;
            }
        }
        return counterXMain == n || counterOMain == n
                || counterXSecondary == n || counterOSecondary == n;
    }

    public boolean isWin() {
        if (checkDiagonal()) {
            return true;
        }
        for (int i = 0; i < n; i++) {
            if (checkRow(i) || checkColumn(i)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidDimension(int dimension) {
        return dimension > 2;
    }

    public int getN() {
        return n;
    }

    public char[][] getMoves() {
        return moves;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public List<int[]> getCoordinateMoves() {
        return coordinateMoves;
    }

    public void setN(int n) {
        this.n = n;
    }
}
package com.tictactoe;

import java.util.Scanner;

/**
 * Класс который запускает всю игру и собирает ее целиком.
 * В игре можно выбрать размер от 3 (классические крестики нолики)
 * до любого значения 4х4, 5х5, NxN
 */
public class TicTacToe {

    public static void main(String[] args) {
        int totalGames = 0;
        Player player1 = new Player();
        Player player2 = new Player();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Вы хотите сыграть в игру? (y or n): ");
            if (scanner.nextLine().equals("n")) {
                Statistics.printValue(player1, player2, totalGames);
                return;
            }
            int boardSize = IOhelper.boardValidSize();

            IOhelper.playerNameInput(player1, player2, totalGames);

            System.out.println("Для преждевременного выхода из игры введите одну из координат -2");

            Model model = new Model(boardSize);
            View board = new View();

            String x;
            String y;
            totalGames++;
            IOhelper.playTicTacToe(player1, player2, model, board, totalGames);

        }
    }
}

package com.tictactoe;

import java.util.Scanner;

/**
 * класс позволяющий выводить разную информацию и
 * ведудущий диалог с игроками.
 */
public class IOhelper {

    public static int boardValidSize() {
        Scanner scanner = new Scanner(System.in);
        int boardSize;
        while (true) {
            try {
                System.out.print("Введите размер поля (от 3 до N): ");
                boardSize = Integer.parseInt(scanner.nextLine());
                if (Model.isValidDimension(boardSize)) {
                    break;
                }
            } catch (NumberFormatException exception) {
                System.out.println("Введите цифру от 3 до N!\n");
            }
        }
        return boardSize;
    }

    public static void playerNameInput(Player player1, Player player2, int totalGames) {
        Scanner scanner = new Scanner(System.in);
        if (totalGames == 0) {
            System.out.print("Введите имя первого игрока: ");
            player1.setName(scanner.nextLine());
            System.out.print("Введите имя второго игрока: ");
            player2.setName(scanner.nextLine());
        } else {
            player1.swapPlayer(player2);
        }
    }

    public static void printResult(Player player1, Player player2, int counter, Model model, View board) {
        if (model.isWin()) {
            String string = counter % 2 == 0 ? ("Нолик - " + player2.getName()) : ("Крестик - " + player1.getName());
            if (counter % 2 == 0) {
                player2.setWin();
                model.setWinner(player2.getName());
            } else {
                player1.setWin();
                model.setWinner(player1.getName());
            }
            System.out.println(board.printBoard(model.getMoves()));
            System.out.println("Игра окончена, выйграл: " + string);
        } else {
            System.out.println(board.printBoard(model.getMoves()));
            System.out.println("Ничья");
            model.setWinner("DRAW!");
        }
        System.out.printf("Общий счет: %s - %d : %d - %s", player1.getName(), player1.getWin(), player2.getWin(), player2.getName());
        System.out.println("\n======================================\n");
    }

    public static void playTicTacToe(Player player1, Player player2, Model model, View board, int totalGames) {
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        int xIntValue = -1;
        int yIntValue = -1;
        boolean flag = true;
        char ch;
        while (!model.isWin() && counter < model.getN() * model.getN()) {
            while (true) {
                System.out.println(board.printBoard(model.getMoves()));
                ch = counter % 2 == 0 ? 'X' : 'O';
                try {
                    System.out.println("Сейчас ходит: " + (counter % 2 == 0 ? ("Крестик - " + player1.getName()) : ("Нолик - " + player2.getName())));
                    System.out.print("Введите x: ");
                    xIntValue = Integer.parseInt(scanner.nextLine());
                    System.out.print("Введите y: ");
                    yIntValue = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException exception) {
                    System.out.printf("Введите цифру от 0 до %d!\n\n", (model.getN() - 1));
                    flag = false;
                }
                if (xIntValue == -2 || yIntValue == -2) {
                    System.out.println("Вы прервали игру!");
                    Statistics.printValue(player1, player2, totalGames);
                    return;
                }
                if (flag) {
                    if (model.isValid(xIntValue, yIntValue)) {
                        break;
                    }
                }
                flag = true;
            }
            model.setMove(xIntValue, yIntValue, ch);
            counter++;
        }
        printResult(player1, player2, counter, model, board);

        History history = new History();
        history.createXML(player1, player2, model);
    }
}

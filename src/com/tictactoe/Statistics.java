package com.tictactoe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

/**
 * класс для записи результатов в файл
 */
public class Statistics {

    public static void printValue(Player player1, Player player2, int totalGames) {
        if (totalGames != 0) {
            File file = Path.of("record.txt").toFile();
            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, true))) {
                String stringValue = String.format("Общий счет: %s - %d : %d - %s",
                        player1.getName(), player1.getWin(), player2.getWin(), player2.getName());
                fileWriter.write(stringValue);
                fileWriter.newLine();
                System.out.println("Результаты вашей игры записаны в файл record.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

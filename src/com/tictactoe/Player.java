package com.tictactoe;

/**
 * Класс для хранения данных игрока.
 * Имя игрока и количество побед.
 */
public class Player {
    private String name;
    private int win = 0;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWin() {
        return win;
    }

    public void setWin() {
        this.win++;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public void swapPlayer(Player player) {
        String string = this.name;
        int winValue = this.win;
        this.name = player.getName();
        this.win = player.getWin();
        player.setName(string);
        player.setWin(winValue);
    }
}

package com.tictactoe;

public class Replayer {

    public static void main(String[] args) {
        HistoryParser historyParser = new HistoryParser();
        historyParser.tictactoeParser();

        Model model = historyParser.getModelXML();
        View view = new View();
        String[] players = historyParser.getPlayersNamesArr();
        int dimension = model.getN();

        System.out.println("Повтор игры.");
        System.out.println(players[0] + " - Крестик, " + players[1] + " - Нолик");
        System.out.println();
        if (model.getWinner().equals("DRAW!")) {
            System.out.println("Эта партия была сыграна в ничью.");
        } else {
            System.out.println("В этой партии выйграл(а) " + model.getWinner());
        }
        System.out.println();

        char[][] moves = model.setEmptyMoves(dimension);
        int counter = 0;
        for (int[] coordinateMove : model.getCoordinateMoves()) {
            if (counter++ % 2 == 0) {
                moves[coordinateMove[0]][coordinateMove[1]] = 'X';
                System.out.println("Ход делает " + players[0] + " - X" + " (" + coordinateMove[0] + ", " + coordinateMove[1] + ")");
            } else {
                System.out.println("Ход делает " + players[1] + " - 0" + " (" + coordinateMove[0] + ", " + coordinateMove[1] + ")");
                moves[coordinateMove[0]][coordinateMove[1]] = '0';
            }
            System.out.println(view.printBoard(moves));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

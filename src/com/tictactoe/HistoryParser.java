package com.tictactoe;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class HistoryParser {

    private Model modelXML;
    private String[] playersNamesArr;

    public void tictactoeParser() {
        File file = Path.of("history").toFile();
        JFileChooser fileOpen = new JFileChooser(file);
        int ret = fileOpen.showDialog(null, "Открыть файл");
        File currFile = fileOpen.getSelectedFile();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(currFile);
            //Document document = builder.parse(new File("history/0-game.xml"));
            Element element = document.getDocumentElement();

            NodeList player = document.getElementsByTagName("Player");
            playersNamesArr = getPlayerName(player);

            NodeList gamePlay = document.getElementsByTagName("GamePlay");
            int dimension = getDimensionBoard(gamePlay);

            modelXML = new Model(dimension);

            NodeList step = document.getElementsByTagName("Step");
            getAllSteps(modelXML, step);

            NodeList gameResult = document.getElementsByTagName("GameResult");
            getGameResult(modelXML, gameResult);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public String[] getPlayerName(NodeList nodeList) {
        String[] playerNamesArr = new String[2];
        for (int i = 0; i < 2; i++) {
            if (((Element) nodeList.item(i)).hasAttribute("name")) {
                playerNamesArr[i] = ((Element) nodeList.item(i)).getAttribute("name");
            }
        }
        return playerNamesArr;
    }

    public int getDimensionBoard(NodeList nodeList) {
        String str = ((Element) nodeList.item(0)).getAttribute("board_dimension");
        return Integer.parseInt(str);
    }

    public void getAllSteps(Model model, NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Text text = (Text) nodeList.item(i).getFirstChild();
            int coordinateX = Integer.parseInt(String.valueOf(text.getData().charAt(0)));
            int coordinateY = Integer.parseInt(String.valueOf(text.getData().charAt(1)));
            model.getCoordinateMoves().add(new int[]{coordinateX, coordinateY});
        }
    }

    public void getGameResult(Model model, NodeList nodeList) {
        if (nodeList.item(0).getTextContent().trim().isEmpty()) {
            Node item = nodeList.item(0).getChildNodes().item(1);
            model.setWinner(((Element) item).getAttribute("name"));
        } else {
            model.setWinner("DRAW!");
            System.out.println("DRAW!");
        }
    }

    public Model getModelXML() {
        return modelXML;
    }

    public String[] getPlayersNamesArr() {
        return playersNamesArr;
    }
}

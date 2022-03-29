package com.tictactoe;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.List;

public class History {

    public void createXML(Player playerFirst, Player playerSecond, Model model) {
        try {
            String name1 = playerFirst.getName();
            String name2 = playerSecond.getName();
            int dimension = model.getN();
            List<int[]> playersSteps = model.getCoordinateMoves();
            String result = model.getWinner();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element root = document.createElement("GamePlay");
            Element game = document.createElement("Game");
            Element gameResult = document.createElement("GameResult");

            document.appendChild(root);
            root.setAttribute("board_dimension", String.valueOf(dimension));

            Element player1 = document.createElement("Player");
            root.appendChild(player1);
            player1.setAttribute("id", "1");
            player1.setAttribute("name", name1);
            player1.setAttribute("mark", "X");

            Element player2 = document.createElement("Player");
            root.appendChild(player2);
            player2.setAttribute("id", "2");
            player2.setAttribute("name", name2);
            player2.setAttribute("mark", "0");

            root.appendChild(game);
            for (int i = 0; i < playersSteps.size(); i++) {
                Element step = document.createElement("Step");
                game.appendChild(step);
                String currentPlayerName = i % 2 == 0 ? name1 : name2;
                step.setAttribute("num", String.valueOf(i + 1));
                String id = (i % 2 == 0) ? "1" : "2";
                step.setAttribute("player_id", id);
                Text text1 = document.createTextNode(String.valueOf(playersSteps.get(i)[0]) + String.valueOf(playersSteps.get(i)[1]));
                step.appendChild(text1);
            }

            root.appendChild(gameResult);
            Text resultText = document.createTextNode(result);
            if (result.equals("DRAW!")) {
                gameResult.appendChild(resultText);
            } else {
                if (result.equals(name1)) {
                    Element winPlayer =  getElementPlayer(document, name1, "1", "X");
                    gameResult.appendChild(winPlayer);
                } else {
                    Element winPlayer =  getElementPlayer(document, name2, "2", "0");
                    gameResult.appendChild(winPlayer);
                }
            }

            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            String fileName = "history/" + getFileName();
            t.transform(new DOMSource(document), new StreamResult(new FileOutputStream(fileName)));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getFileName() {
        File file = Path.of("history").toFile();
        File[] files = file.listFiles();
        String str = String.valueOf(files.length);
        return str + "-game.xml";
    }

    public Element getElementPlayer(Document document, String name, String id, String mark) {
        Element elem = document.createElement("Player");
        elem.setAttribute("id", id);
        elem.setAttribute("name", name);
        elem.setAttribute("mark", mark);
        return elem;
    }

}

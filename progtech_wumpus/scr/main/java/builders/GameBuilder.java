package builders;

import controllers.BoardController;
import controllers.PlayerController;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import models.Position;

interface IGameBuilderConstants {

    String INPUT_FILE_PATH = "wumpluszinput.txt";
}

public class GameBuilder implements IGameBuilderConstants {

    private BoardController board;
    private PlayerController player;

    public GameBuilder() {
        loadDataFromFile();
    }

    public BoardController getBoard() {
        return board;
    }

    public PlayerController getPlayer() {
        return player;
    }

    private void loadDataFromFile() {

        try {
            File inputFile = new File(INPUT_FILE_PATH);
            Scanner scanner = new Scanner(inputFile);

            String firstLine = scanner.nextLine();
            String[] parts = firstLine.split(" ");

            int playerColumnPosition = translatePlayerColumn(parts[1].charAt(0));
            int boardSize = Integer.parseInt(parts[0]);

            if (playerColumnPosition < 0 || playerColumnPosition > boardSize - 1) {
                System.err.println("The player's column position points out of the board.");
                System.exit(0);
            }

            int playerRowPosition = Integer.parseInt(parts[2]) - 1;
            Position playerPosition = new Position(playerRowPosition, playerColumnPosition);

            char playerDirection = parts[3].charAt(0);

            board = new BoardController(boardSize, playerPosition);

            for (int i = 0; i < boardSize; i++) {
                String line = scanner.nextLine();
                board.setRow(i, line);
            }

            if (board.getCell(playerPosition) != 'H') {
                System.err.println("The starting position of the player cannot be occupied by other elements!");
                System.exit(0);
            }

            player = new PlayerController(board.getNumberOfWumpus(), playerDirection);
        } catch (FileNotFoundException | NumberFormatException e) {
            System.err.println("The input file is not found in the resource folder or illagel player row input!");
            System.exit(0);
        }
    }

    private int translatePlayerColumn(char columnInChar) {
        return (int) columnInChar - 65;
    }
}

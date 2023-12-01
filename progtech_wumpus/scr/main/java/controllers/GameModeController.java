package controllers;

import java.util.Scanner;
import models.Position;

public class GameModeController {

    private String username = "";
    private final BoardController board;
    private final PlayerController player;
    private boolean gameInProgress = true;
    private boolean isPlayerOwnTheGold = false;

    public GameModeController(BoardController board, PlayerController player) {
        this.board = board;
        this.player = player;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        // request username
        System.out.print("Please enter your username: ");
        setUsername(sc.nextLine().toUpperCase());

        while (gameInProgress) {
            System.out.println("-----------------------------------------------");
            printOutGameStatus(board, player);
            System.out.println("-----------------------------------------------");
            printOutPlayerActions();
            System.out.println("-----------------------------------------------");
            System.out.print("Your move: ");
            String action = sc.nextLine().toUpperCase();
            System.out.println("-----------------------------------------------");

            if ("M".equals(action)) {
                move();
                continue;
            }

            if ("R".equals(action)) {
                player.turnRight();
                continue;
            }

            if ("L".equals(action)) {
                player.turnLeft();
                continue;
            }

            if ("S".equals(action)) {
                shot();
                continue;
            }

            if ("P".equals(action)) {
                pickUpGold();
                continue;
            }

            if ("E".equals(action)) {
                setGameInProgress(false);
            }
        }
    }    

    public String getUsername() {
        return username;
    }

    public BoardController getBoard() {
        return board;
    }

    public PlayerController getPlayer() {
        return player;
    }

    public boolean isGameInProgress() {
        return gameInProgress;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setGameInProgress(boolean gameInProgress) {
        this.gameInProgress = gameInProgress;
    }

    public boolean isIsPlayerOwnTheGold() {
        return isPlayerOwnTheGold;
    }

    public void setIsPlayerOwnTheGold(boolean isPlayerOwnTheGold) {
        this.isPlayerOwnTheGold = isPlayerOwnTheGold;
    }

    public void pickUpGold() {
        if (this.board.getCell(this.board.getPlayerPosition()) != 'G') {
            System.out.println("There is no gold in the current field, the Wumpus must be playing with your mind.");
            return;
        }
        System.out.println("You have successfully picked up the gold, all you have to do is return it to the starting position.");
        setIsPlayerOwnTheGold(true);
    }

    private void printOutGameStatus(BoardController board, PlayerController player) {
        final StringBuffer sb = new StringBuffer("Game status: \n");
        sb.append("Position (row x column): ").append(board.getPlayerPosition().getRow() + 1).append(" x ").append(board.getPlayerPosition().getColumn() + 1);
        sb.append("\nCurrent direction: ").append(player.getCurrentDirection());
        sb.append("\nNumber of arrows: ").append(player.getNumberOfArrows());
        sb.append("\nAre you own the gold: ").append(this.isPlayerOwnTheGold);
        System.out.println(sb.toString());
        System.out.println("-----------------------------------------------");
        System.out.print(board);
    }

    private void printOutPlayerActions() {
        final StringBuffer sb = new StringBuffer("Possible moves: \n");
        sb.append("Move (M) \n");
        sb.append("Turn right (R) \n");
        sb.append("Turn left (L) \n");
        sb.append("Shot (S) \n");
        sb.append("Pick up the gold (P) \n");
        sb.append("I give up the game (E) \n");
        System.out.print(sb.toString());
    }

    private void win() {
        DatabaseController db = new DatabaseController();
        db.incrementScore(username);
        final StringBuffer sb = new StringBuffer("Congratulations, you've earned the gold! \n");
        sb.append("You have taken ").append(player.getNumberOfSteps()).append(" steps to reach your goal.\n");
        System.out.println(sb.toString());

    }

    private void lose() {
        final StringBuffer sb = new StringBuffer("Game Over! \n");
        sb.append("You stepped carelessly and unfortunately got killed by a Wumpus.");
        System.out.println(sb.toString());
    }

    private void move() {
        Position position = nextPosition();
        board.isValidMove(position);
        char objectOnTheCell = board.getCell(position);

        if (objectOnTheCell == 'W') {
            System.out.println("You cannot go in that direction because there is a wall on it.");
            return;
        }

        if (objectOnTheCell == 'P') {
            System.out.println("You stepped carelessly, so unfortunately you found yourself in a pile where you lost an arrow.");
            player.move();
            player.reduceTheNumberOfArrows();
            board.setCell(board.getPlayerPosition(), '_');
            board.setPlayerPosition(position);
            return;
        }

        if (objectOnTheCell == 'U') {
            lose();
            setGameInProgress(false);
            return;
        }

        if (objectOnTheCell == 'G') {
            player.move();
            board.setCell(board.getPlayerPosition(), '_');
            board.setPlayerPosition(position);
            return;
        }

        player.move();

        if (this.isPlayerOwnTheGold && board.getStartPosition().equals(position)) {
            win();
            setGameInProgress(false);
            return;
        }

        System.out.println("Peace and quiet for now, but watch your step.");
        board.setCell(board.getPlayerPosition(), '_');
        board.setCell(position, 'H');
        board.setPlayerPosition(position);

    }

    private void shot() {
        char direction = player.getCurrentDirection();
        player.reduceTheNumberOfArrows();
        System.out.println("Shot started with direction: " + direction);

        if (direction == 'N') {
            verticalShotDirection(-1);
            return;
        }

        if (direction == 'E') {
            horizontalShotDirection(1);
            return;
        }

        if (direction == 'S') {
            verticalShotDirection(1);
            return;
        }

        horizontalShotDirection(-1);
    }

    private void horizontalShotDirection(int step) {
        Position playerPosition = board.getPlayerPosition();
        Position nextPosition = new Position(playerPosition.getRow(), playerPosition.getColumn() + step);
        char objectOnNextCell = board.getCell(nextPosition);

        while (true) {
            if (objectOnNextCell == 'U') {
                killWumpus(nextPosition);
                return;
            }
            if (objectOnNextCell == 'W') {
                System.out.println("Missed shot! Unfortunately, your arrow pierced a wall.");
                return;
            }
            nextPosition = new Position(nextPosition.getRow(), nextPosition.getColumn() + step);
            objectOnNextCell = board.getCell(nextPosition);
        }
    }

    private void verticalShotDirection(int step) {
        Position playerPosition = board.getPlayerPosition();
        Position nextPosition = new Position(playerPosition.getRow() + step, playerPosition.getColumn());
        char objectOnNextCell = board.getCell(nextPosition);

        while (true) {
            if (objectOnNextCell == 'U') {
                killWumpus(nextPosition);
                return;
            }
            if (objectOnNextCell == 'W') {
                System.out.println("Missed shot! Unfortunately, your arrow pierced a wall.");
                return;
            }
            nextPosition = new Position(nextPosition.getRow() + step, nextPosition.getColumn());
            objectOnNextCell = board.getCell(nextPosition);
        }
    }

    private void killWumpus(Position position) {
        board.setCell(position, '_');
        board.decreaseNumberOfWumpus();
        System.out.println("Congratulation! You have successfully hit a Wumpus!");
    }

    private Position nextPosition() {
        char direction = player.getCurrentDirection();
        int currentRowPosition = board.getPlayerPosition().getRow();
        int currentColumnPosition = board.getPlayerPosition().getColumn();

        if (direction == 'N') {
            return new Position(currentRowPosition - 1, currentColumnPosition);
        }

        if (direction == 'E') {
            return new Position(currentRowPosition, currentColumnPosition + 1);
        }

        if (direction == 'S') {
            return new Position(currentRowPosition + 1, currentColumnPosition);
        }

        return new Position(currentRowPosition, currentColumnPosition - 1);
    }

}

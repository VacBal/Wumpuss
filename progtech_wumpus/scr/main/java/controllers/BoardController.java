package controllers;

import java.util.Objects;
import models.Board;
import models.Position;

public class BoardController {

    private final Board board;

    public BoardController(Board board) {
        this.board = board;
    }
    
    public Position getStartPosition() {
        return this.board.getStartPosition();
    }

    public int getSize() {
        return this.board.getSize();
    }

    public Position getPlayerPosition() {
        return this.board.getPlayerPosition();
    }

    public BoardController(int size, Position playerPosition) {
        this.board = new Board(size, playerPosition);
    }

    public int getNumberOfWumpus() {
        return this.board.getNumberOfWumpus();
    }

    public void decreaseNumberOfWumpus() {
        int currentNumberOfWumpus = this.board.getNumberOfWumpus();
        
        if(currentNumberOfWumpus < 1) {
            return;
        }
        
        this.board.setNumberOfWumpus(currentNumberOfWumpus - 1);
    }

    public void setPlayerPosition(Position playerPosition) {
        this.board.setPlayerPosition(playerPosition);
    }

    public char getCell(Position position) {
        return this.board.getMap()[position.getRow()][position.getColumn()];
    }

    public void setCell(Position position, char value) {
        this.board.changeBoardCell(position, value);
    }

    public void setRow(int rowIndex, String row) {
        int size = this.board.getSize();

        if (rowIndex < 0 || rowIndex > size || row.length() != size) {
            System.out.println("Invalid row or row size provided!");
            throw new RuntimeException("Invalid input file!");
        }

        // Set the row from the file, count wumpus number, to player arrow number
        for (int i = 0; i < size; i++) {
            Position position = new Position(rowIndex, i);
            this.board.changeBoardCell(position, row.charAt(i));
            if (row.charAt(i) == 'U') {
                this.board.setNumberOfWumpus(this.board.getNumberOfWumpus() + 1);
            }
        }
    }

    public boolean isValidMove(Position position) {
        int row = position.getRow();
        int col = position.getColumn();

        // if the provided row is invalid return false
        if (row < 0 || row > this.board.getSize()) {
            return false;
        }

        // if the provided col is invalid return false
        if (col < 0 || col > this.board.getSize()) {
            return false;
        }

        // If the specified cell has a wall, return with a False else with a True value
        return this.board.getMap()[row][col] != 'W';
    }

    @Override
    public String toString() {
        return board.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BoardController other = (BoardController) obj;
        return Objects.equals(this.board, other.board);
    }

}

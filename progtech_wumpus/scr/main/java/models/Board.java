package models;

import java.util.Arrays;
import java.util.Objects;

public class Board {

    private final int size;
    private final char[][] map;
    private Position playerPosition;
    private final Position startPosition;
    private int numberOfWumpus;

    public Board(int size, Position startPosition) {
        this.size = size;
        this.map = new char[size][size];
        this.startPosition = startPosition;
        this.playerPosition = startPosition;        
    }

    public int getSize() {
        return size;
    }

    public char[][] getMap() {
        return map;
    }

    public void changeBoardCell(Position position, char value) {
        map[position.getRow()][position.getColumn()] = value;
    }

    public Position getStartPosition() {
        return startPosition;
    }    

    public Position getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(Position playerPosition) {
        this.playerPosition = playerPosition;
    }

    public int getNumberOfWumpus() {
        return numberOfWumpus;
    }

    public void setNumberOfWumpus(int numberOfWumpus) {
        this.numberOfWumpus = numberOfWumpus;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.size;
        hash = 29 * hash + Arrays.deepHashCode(this.map);
        hash = 29 * hash + Objects.hashCode(this.playerPosition);
        hash = 29 * hash + Objects.hashCode(this.startPosition);
        hash = 29 * hash + this.numberOfWumpus;
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
        final Board other = (Board) obj;
        if (this.size != other.size) {
            return false;
        }
        if (this.numberOfWumpus != other.numberOfWumpus) {
            return false;
        }
        if (!Objects.equals(this.playerPosition, other.playerPosition)) {
            return false;
        }
        return Objects.equals(this.startPosition, other.startPosition);
    }
        
    @Override
    public String toString() {

        StringBuilder boardString = new StringBuilder();

        for (char[] row : map) {
            boardString.append(Arrays.toString(row)).append("\n");
        }

        return boardString.toString();
    }
}

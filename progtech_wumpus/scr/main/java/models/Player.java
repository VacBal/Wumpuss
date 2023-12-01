package models;

public class Player {

    private int numberOfArrows;
    private int numberOfSteps = 0;
    private char currentDirection;

    public Player(int numberOfArrows, char currentDirection) {
        this.numberOfArrows = numberOfArrows;
        this.currentDirection = currentDirection;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public void setNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

    public int getNumberOfArrows() {
        return numberOfArrows;
    }

    public void setNumberOfArrows(int numberOfArrows) {
        this.numberOfArrows = numberOfArrows;
    }

    public char getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(char currentDirection) {
        this.currentDirection = currentDirection;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player {");
        sb.append("Number Of Arrows = ").append(numberOfArrows);
        sb.append(", Number Of Steps = ").append(numberOfSteps);
        sb.append(", Current Direction = ").append(currentDirection);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Player other = (Player) obj;
        if (this.numberOfArrows != other.numberOfArrows) {
            return false;
        }
        if (this.numberOfSteps != other.numberOfSteps) {
            return false;
        }
        return this.currentDirection == other.currentDirection;
    }
    
    
}

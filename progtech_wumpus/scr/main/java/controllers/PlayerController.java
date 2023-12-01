package controllers;

import java.util.Objects;
import models.Player;

public class PlayerController {

    private final Player player;

    public PlayerController(int numberOfArrows, char currentDirection) {
        this.player = new Player(numberOfArrows, currentDirection);
    }

    public char getCurrentDirection() {
        return this.player.getCurrentDirection();
    }

    public int getNumberOfArrows() {
        return this.player.getNumberOfArrows();
    }

    public int getNumberOfSteps() {
        return this.player.getNumberOfSteps();
    }

    public void move() {
        player.setNumberOfSteps(player.getNumberOfSteps() + 1);
    }

    public void turnRight() {
        System.out.println("The player has successfully turned right.");
        player.setNumberOfSteps(player.getNumberOfSteps() + 1);

        if (this.player.getCurrentDirection() == 'N') {
            this.player.setCurrentDirection('E');
            return;
        }

        if (this.player.getCurrentDirection() == 'E') {
            this.player.setCurrentDirection('S');
            return;
        }

        if (this.player.getCurrentDirection() == 'S') {
            this.player.setCurrentDirection('W');
            return;
        }

        this.player.setCurrentDirection('N');
    }

    public void turnLeft() {
        System.out.println("The player has successfully turned left.");
        player.setNumberOfSteps(player.getNumberOfSteps() + 1);

        if (this.player.getCurrentDirection() == 'N') {
            this.player.setCurrentDirection('W');
            return;
        }

        if (this.player.getCurrentDirection() == 'E') {
            this.player.setCurrentDirection('N');
            return;
        }

        if (this.player.getCurrentDirection() == 'S') {
            this.player.setCurrentDirection('E');
            return;
        }

        this.player.setCurrentDirection('S');
    }

    public void shoot() {
        if (player.getNumberOfArrows() < 1) {
            System.out.println("You have no arrows to shoot!");
        }

        reduceTheNumberOfArrows();
    }

    public void reduceTheNumberOfArrows() {
        if (player.getNumberOfArrows() < 1) {
            return;
        }
        this.player.setNumberOfArrows(this.player.getNumberOfArrows() - 1);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.player);
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
        final PlayerController other = (PlayerController) obj;
        return Objects.equals(this.player, other.player);
    }
    
    
}

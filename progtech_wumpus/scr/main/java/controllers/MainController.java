package controllers;

import builders.GameBuilder;
import java.util.Scanner;

public class MainController {

    private final DatabaseController database;
    private final BoardController board;
    private final PlayerController player;    
    private int selectedItem = 0;
    private boolean gameRunning = true;
    
    

    public MainController() {
        GameBuilder gameBuilder = new GameBuilder();
        this.database = new DatabaseController();
        this.board = gameBuilder.getBoard();
        this.player = gameBuilder.getPlayer();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        // loop the game with menu
        while (gameRunning) {
            // list the menu items and read a number to select one of them            
            listMenuItems();
            System.out.println("-----------------------------------------------");
            System.out.print("Please select a menu item: ");
            // from the console the scenner read strings, so we have to parse it to int safely
            try {
                selectedItem = Integer.parseInt(sc.nextLine());
                System.out.println("-----------------------------------------------");
            } catch (NumberFormatException e) {
                // when the user provided value is not a number, we have to reload the loop
                System.out.println("Please enter a number from range 1-3.");                
                continue;
            }

            // when the user provided value is number, we need to check if it is within the range
            if (selectedItem < 1 || selectedItem > 3) {
                System.out.println("Please enter a number from range 1-3.");
                selectedItem = 0;
                continue;
            }

            // Play
            if (selectedItem == 1) {
                GameModeController gameMode = new GameModeController(board, player);
                gameMode.start();
                selectedItem = 0;
                continue;
            }

            // Show high score table            
            if (selectedItem == 2) {
                database.printOutHighScoreTable();
                //System.out.println("");
                selectedItem = 0;
                continue;
            }

            // Exit (we used guard if so last possible when user input was 3)
            exitProgram();
        }
    }

    public boolean isGameRunning() {
        return gameRunning;
    }    

    public int getSelectedItem() {
        return selectedItem;
    }

    private void listMenuItems() {
        String[] items = {
            "Play",
            "Show high score table",
            "Exit"
        };

        System.out.println("-----------------------------------------------");
        System.out.println("Main Menu");
        System.out.println("-----------------------------------------------");
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i] + " (" + (i + 1) + ")");

        }
    }
    
    
    private void exitProgram() {
        this.gameRunning = false;
    }
}

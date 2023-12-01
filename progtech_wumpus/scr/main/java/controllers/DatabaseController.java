package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.Database;

public class DatabaseController {

    private final Connection db;

    public DatabaseController() {
        this.db = new Database().getConnection();
    }

    public void printOutHighScoreTable() {
        System.out.println("");
        System.out.println("-----------------------------------------------");
        System.out.println("High score table");
        System.out.println("-----------------------------------------------");
        String selectQuery = "SELECT * FROM scores";
        try (PreparedStatement ps = db.prepareStatement(selectQuery)) {
            ResultSet resultSet = ps.executeQuery();
            boolean haveNext = resultSet.next();

            if (!haveNext) {
                System.out.println("There is no saved score yet.");
                return;
            }

            while (haveNext) {
                System.out.println(resultSet.getString("username") + ":  " + resultSet.getInt("games_won"));
                haveNext = resultSet.next();
            }

        } catch (Exception e) {
            System.out.println("Error at querying user scores");
            System.exit(0);
        }
    }

    public void incrementScore(String username) {
        boolean isExisting = isExistingUser(username);

        if (!isExisting) {
            createUser(username);
            return;
        }
        int currentUserScore = getUserScore(username);
        updateScore(username, currentUserScore + 1);
    }

    private void updateScore(String username, int games_won) {
        String updateQuery = "UPDATE scores SET games_won=? WHERE username = ?";

        try (PreparedStatement ps = db.prepareStatement(updateQuery)) {
            ps.setInt(1, games_won);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error at incrementing user with username: " + username);
            System.exit(0);
        }
    }

    private boolean isExistingUser(String username) {
        String selectQuery = "SELECT * FROM scores WHERE username = ?";
        try (PreparedStatement ps = db.prepareStatement(selectQuery)) {
            ps.setString(1, username);
            ResultSet resultSet = ps.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            System.out.println("Error at querying user with username: " + username);
            System.exit(0);
            return false;
        }
    }

    private void createUser(String username) {
        String insertQuery = "INSERT INTO scores (username, games_won) VALUES (?, ?)";
        try (PreparedStatement ps = db.prepareStatement(insertQuery)) {
            ps.setString(1, username);
            ps.setInt(2, 1);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error at creating user with username: " + username);
            System.exit(0);
        }
    }

    private int getUserScore(String username) {
        String selectQuery = "SELECT games_won FROM scores WHERE username = ?";
        try (PreparedStatement ps = db.prepareStatement(selectQuery)) {
            ps.setString(1, username);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("games_won");
            }
        } catch (Exception e) {
            System.out.println("Error at querying user");
            System.exit(0);
            return 0;
        }
        return 0;
    }
}

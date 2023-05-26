package ro.netvoip.voteapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ro.netvoip.voteapp.StartingMenuController;
import ro.netvoip.voteapp.VoteAppController;

import java.io.IOException;
import java.util.List;

public class VoteAppApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the StartingMenu.fxml file
        FXMLLoader startingMenuLoader = new FXMLLoader(getClass().getResource("starting_menu.fxml"));
        Parent startingMenuRoot = startingMenuLoader.load();

        // Get the controller instance for the starting menu
        StartingMenuController startingMenuController = startingMenuLoader.getController();

        // Set up the scene for the starting menu
        Scene startingMenuScene = new Scene(startingMenuRoot);
        primaryStage.setScene(startingMenuScene);
        primaryStage.setTitle("Starting Menu");

        // Show the primary stage
        primaryStage.show();

        // Set up an event handler for the "START" button in the starting menu
        startingMenuController.getStartButton().setOnAction(event -> {
            // Retrieve the number of voters from the starting menu controller
            int numberOfVoters = startingMenuController.getNumberOfVoters();

            // Retrieve the list of voter names from the starting menu controller
            List<String> voterNames = startingMenuController.getVoterNames();

            // Close the starting menu stage
            primaryStage.close();

            // Load the VoteApp-view.fxml file
            FXMLLoader voteAppLoader = new FXMLLoader(getClass().getResource("VoteApp-view.fxml"));
            Parent voteAppRoot = null;
            try {
                voteAppRoot = voteAppLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Get the controller instance for the VoteApp-view
            VoteAppController voteAppController = voteAppLoader.getController();

            // Set up the scene for the VoteApp-view
            Scene voteAppScene = new Scene(voteAppRoot);
            Stage voteAppStage = new Stage();
            voteAppStage.setScene(voteAppScene);
            voteAppStage.setTitle("Voting App");

            // Initialize the VoteApp-controller with the number of voters and their names
            voteAppController.initialize(numberOfVoters, voterNames);

            // Show the VoteApp stage
            voteAppStage.show();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

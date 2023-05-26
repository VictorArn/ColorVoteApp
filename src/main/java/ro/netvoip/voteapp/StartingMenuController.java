package ro.netvoip.voteapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartingMenuController {

    @FXML
    private TextField numberOfVotersTextField;
    @FXML
    private Button startButton;

    private int numberOfVotersIndex;



    public int getNumberOfVoters() {
        try {
            return Integer.parseInt(numberOfVotersTextField.getText());
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid integer
            return 0; // Return a default value or show an error message
        }
    }

    public List<String> getVoterNames() {
        List<String> voterNames = new ArrayList<>();
        int numberOfVoters = getNumberOfVoters();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VoterNames.fxml"));
            try{

                Parent root = loader.load();
                Scene scene = new Scene(root);

                // Get the controller instance
                VoterNamesController voterNamesController = loader.getController();

                // Initialize the controller with the voters and choices


                // Show the scene on the stage
                Stage voterNamesStage = new Stage();
                voterNamesController.setStage(voterNamesStage);
                voterNamesController.initialize(numberOfVoters);
                voterNamesStage.setScene(scene);
                voterNamesStage.showAndWait();

                voterNames = voterNamesController.getVoterNames();
                numberOfVotersIndex = getNumberOfVoters();
                if(numberOfVotersIndex==numberOfVoters)
                    voterNamesStage.close();
            } catch (IOException e){
                e.printStackTrace();
            }

        return voterNames;
    }

    public Button getStartButton() {
        return startButton;
    }
}

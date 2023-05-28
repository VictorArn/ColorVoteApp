package ro.netvoip.voteapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class VoterNamesController {

    @FXML
    private TextField voterNameTextField;
    @FXML
    private Button addVoterButton;
    @FXML
    private Label voterNameLabel;
    private List<String> voterNames = new ArrayList<>();
    private int numberOfVoters;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize(int numberOfVoters){
        this.numberOfVoters = numberOfVoters;
        voterNameLabel.setText("Enter the Voter 1's name");

    }

    public void handleAddVoterButtonAction(ActionEvent actionEvent) {


        if(voterNameTextField.getText().isBlank()) {
            String errorMessage = "Please enter a valid name!";
            Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.OK);
            alert.showAndWait();
            return;
        }
        // Get the voter name from the text field
        String voterName = voterNameTextField.getText().trim();
        voterNameLabel.setText("Enter the Voter "+ (voterNames.size()+2) + "'s name");
        // Add the voter name to the list
        voterNames.add(voterName);

        // Clear the text field
        voterNameTextField.clear();

        if (voterNames.size() == numberOfVoters) {
            // Close the stage

            stage.close();
        }
    }

    public List<String> getVoterNames() {
        return voterNames;
    }




}

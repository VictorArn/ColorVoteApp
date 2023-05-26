package ro.netvoip.voteapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class VoterNamesController {

    @FXML
    private TextField voterNameTextField;
    @FXML
    private Button addVoterButton;
    private List<String> voterNames = new ArrayList<>();
    private int numberOfVoters;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize(int numberOfVoters){
        this.numberOfVoters = numberOfVoters;


    }

    public void handleAddVoterButtonAction(ActionEvent actionEvent) {


        // Get the voter name from the text field
        String voterName = voterNameTextField.getText();

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

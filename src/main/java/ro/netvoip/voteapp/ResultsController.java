package ro.netvoip.voteapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.List;

public class ResultsController {

    @FXML
    private ListView<String> votersListView;

    public void initialize(List<Voter> voters ){
        // Iterate over the voters and their choices
        for (int i = 0; i < voters.size(); i++) {
            Voter voter = voters.get(i);


            // Create a label for the voter's name and choices
            String voterInfo = "Voter: " + voter.getName() + " | Choices: " + voter.getVotes().toString();

            // Add the voter info to the ListView
            votersListView.getItems().add(voterInfo);
        }
    }
}

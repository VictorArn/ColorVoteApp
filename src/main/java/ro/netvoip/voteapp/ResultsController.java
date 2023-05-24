package ro.netvoip.voteapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class ResultsController {

    @FXML
    private ListView<String> votersListView;

    @FXML
    private Button exitButton;

    @FXML
    private Pane rootPane;

    private Stage primaryStage;


    public void initialize(VotingSystem votingSystem, Stage primaryStage) {
        this.primaryStage = primaryStage;
        // Iterate over the voters and their choices
        for (int i = 0; i < votingSystem.getListVoters().size(); i++) {
            Voter voter = votingSystem.getListVoters().get(i);
            List<Integer> choices = voter.getVotes();
            for(int choice:choices){
                int updatedValue = choice + 1;
                choices.set(choices.indexOf(choice),updatedValue);
            }


            // Create a label for the voter's name and choices
            String voterInfo = "Voter: " + voter.getName() + " | Choices: " + choices.toString();

            // Add the voter info to the ListView
            votersListView.getItems().add(voterInfo);
        }

        Map<Integer, Integer> results = new HashMap<>();
        for (int i = 0; i < votingSystem.getListVoters().get(0).getBallot().getCandidates().length ; i++) {
            results.put(i, 0);
        }
        List<List<Integer>> listChoices = new ArrayList<>();
        for (Voter voter : votingSystem.getListVoters()) {
            listChoices.add(voter.getVotes());
        }
        for (List<Integer> choices : listChoices) {
            for (int choice : choices){
                if (results.containsKey(choice-1)) {
                    results.put(choice-1, results.get(choice-1) + 1);
                } else {
                    results.put(choice-1, 1);
                }
            }
        }
        String winnerIndex = votingSystem.getListVoters().get(0).getBallot().getCandidates()[Collections.max(results.entrySet(), Map.Entry.comparingByValue()).getKey()];
        int winnerIntIndex;
        winnerIntIndex = Integer.parseInt(winnerIndex.substring(6));
        int anotherWinner = 0;
        String tie = "There are multiple winners: " + winnerIndex + " " ;
        //display the winner
            String winner = "The winner is: " + winnerIndex;
        for(int i = 0; i < votingSystem.getListVoters().get(0).getBallot().getCandidates().length; i++)
        {
            if(results.get(winnerIntIndex-1) == results.get(i))
            {
                for (int j = i + 1; j < votingSystem.getListVoters().get(0).getBallot().getCandidates().length; j++) {
                    if (results.get(i) == results.get(j)) {
                        anotherWinner = winnerIntIndex;
                        tie = tie+ votingSystem.getListVoters().get(0).getBallot().getCandidates()[j] + " ";
                    }
                }
                break;
            }

        }
        if(winnerIntIndex != anotherWinner)
        {
            votersListView.getItems().add(winner);
        }
        else
        {
            votersListView.getItems().add(tie);
        }

        // now iterate over the  candidates and display their votes
        for(int i = 0; i< votingSystem.getListVoters().get(0).getBallot().getCandidates().length; i++)
        {
            if(results.get(i) != 0)
            {
            String resultsInfo = (i + 1) + ". " + "Color " + (i + 1) + " has " + results.get(i) + " votes.";
            votersListView.getItems().add(resultsInfo);
            }
        }
        String Ovotes = "The rest of the colors have 0 votes.";
        votersListView.getItems().add(Ovotes);

        //use a lambda expression to close the window



        /*List <Integer> results = new ArrayList<>(Collections.nCopies(votingSystem.getListVoters().get(0).getBallot().getCandidates().length, 0));
        for(Voter voter : votingSystem.getListVoters()){
            List<Integer> choices = voter.getVotes();
            for (int i = 0; i < choices.size(); i++) {
                int value = results.get(choices.get(i) );
                value++;
                results.set(choices.get(i), value);
            }
        }
            List <Integer> resultsCopy = results;
            Collections.sort(results, Collections.reverseOrder());
            for(int i = 0; i < results.size(); i++) {
                String s = "Color: " + resultsCopy.indexOf(results.get(i));
                String resultsInfo = (i+1) + ". " + "Color " + resultsCopy.indexOf(results.get(i)) + " has " + results.get(i) + " votes.";
                votersListView.getItems().add(resultsInfo);
            }*/


    }


    @FXML
    private void handleExitButton(ActionEvent event) {
        // Close the final results form
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

        // Close the initial form
        Stage initialStage = (Stage) primaryStage.getScene().getWindow();
        initialStage.close();
    }
}

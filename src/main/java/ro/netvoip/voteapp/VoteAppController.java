package ro.netvoip.voteapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class VoteAppController {

    @FXML
    private ImageView control1;
    @FXML
    private ImageView control2;
    @FXML
    private ImageView control3;
    @FXML
    private ImageView control4;
    @FXML
    private ImageView control5;
    @FXML
    private ImageView control6;
    @FXML
    private ImageView control7;
    @FXML
    private ImageView control8;
    @FXML
    private ImageView control9;
    @FXML
    private ImageView control10;
    @FXML
    private ImageView control11;
    @FXML
    private ImageView control12;

    @FXML
    private Label voterLabel;

    @FXML
    private Button nextButton;

    @FXML
    private Button discardVoteButton;
    @FXML
    private ListView<String> userVotesListView;

    @FXML
    private Button previousButton;


    private VotingSystem votingSystem;
    private static int voterIndex = 0;

    private Color [] candidateColors ;

    private Ballot ballot;
    private Voter voter;

    private List<Voter> listOfVoters;

    private List<Integer> choices = new ArrayList<>();

    private List<Integer> colorIndexes = new ArrayList<>();





    public void addVoters(int numberOfVoters ,List<String> votersNames) {
        listOfVoters = new ArrayList<>();
        for(int i = 0 ; i < numberOfVoters ; i++){
            Voter voter = new Voter(votersNames.get(i), i+1);
            listOfVoters.add(voter);
        }
      /*  Voter voter1 = new Voter("John Doe", 1);
        Voter voter2 = new Voter("Jane Doe", 2);
        Voter voter3 = new Voter("Jack Doe", 3);

        listOfVoters.add(voter1);
        listOfVoters.add(voter2);
        listOfVoters.add(voter3);*/
    }



    // Map to associate color names with their Color objects
    private Map<Color, String> colorNameMap;

    public void initialize(int numberOfVoters ,List<String> votersNames) {
        addVoters( numberOfVoters , votersNames);
        String voterName = listOfVoters.get(voterIndex).getName();
        voterLabel.setText("Voter: " + voterName);
        // Create a Ballot with candidate names
        String[] candidateNames = {"Color 1", "Color 2", "Color 3", "Color 4", "Color 5", "Color 6", "Color 7", "Color 8", "Color 9", "Color 10", "Color 11", "Color 12",};

        // Generate random colors for the candidates
        candidateColors = generateRandomColors(candidateNames.length);

        // Create a Ballot with the candidate names
         ballot = new Ballot(candidateNames);

        // Initialize the VotingSystem
        votingSystem = new VotingSystem(ballot);

        // Associate color names with their Color objects in the map
        colorNameMap = new HashMap<>();
        for (int i = 0; i < candidateColors.length; i++) {
            colorNameMap.put(candidateColors[i], candidateNames[i]);
        }

        control1.setImage(generateImageFromColor(candidateColors[0]));
        control2.setImage(generateImageFromColor(candidateColors[1]));
        control3.setImage(generateImageFromColor(candidateColors[2]));
        control4.setImage(generateImageFromColor(candidateColors[3]));
        control5.setImage(generateImageFromColor(candidateColors[4]));
        control6.setImage(generateImageFromColor(candidateColors[5]));
        control7.setImage(generateImageFromColor(candidateColors[6]));
        control8.setImage(generateImageFromColor(candidateColors[7]));
        control9.setImage(generateImageFromColor(candidateColors[8]));
        control10.setImage(generateImageFromColor(candidateColors[9]));
        control11.setImage(generateImageFromColor(candidateColors[10]));
        control12.setImage(generateImageFromColor(candidateColors[11]));

        if(numberOfVoters == 1){
            nextButton.setText("Display results");
        }
        else {
            nextButton.setText("Next voter");
        }

        nextButton.setOnAction(this::handleNextButton);
        previousButton.setOnAction(this::handlePreviousButton);


    }


    @FXML
    private void handleNextButton(ActionEvent event) {

        // handle the situation where the first voter is null
        if(voter == null){
            voter = listOfVoters.get(voterIndex);
            voter.setBallot(votingSystem.getBallot());

        }
    // add the voter to the voting system after the mouse event
        if(!votingSystem.getListVoters().contains(voter) )
        votingSystem.addExistingVoter(voter);
        for(Voter voter : votingSystem.getListVoters()){
            System.out.println(voter.getName() + " " + voter.getId());
        }



        // create a new voter based on the list of voters
        if (voterIndex < listOfVoters.size()) {
            // handle the label and the button
            if (voterIndex < listOfVoters.size() - 1) {
                voterIndex++;
                voter = listOfVoters.get(voterIndex);

                String voterName = listOfVoters.get(voterIndex).getName();
                voterLabel.setText("Voter: " + voterName);

                System.out.println("Voter: " + voterName);
                if (voterIndex == listOfVoters.size() - 1) {
                    nextButton.setText("Display results");
                }
                else {
                    nextButton.setText("Next voter");
                }
            } else {
                // Last voter reached, display the results
                Stage primaryStage = (Stage) nextButton.getScene().getWindow();
                displayResults(primaryStage);
                nextButton.setDisable(true); // Disable the button after displaying results
            }
        }

        // Update the userVotesListView
        userVotesListView.getItems().clear();
        List<Integer> votes = voter.getVotes();
        for (int vote : votes) {
            String colorName = votingSystem.getBallot().getCandidates()[vote - 1];
            String voteInfo = "You voted for: " + colorName;
            userVotesListView.getItems().add(voteInfo);
        }
        }





    /* public void initialize() {
        // Generate random colors
        Color color1 = generateRandomColor();
        Color color2 = generateRandomColor();
        Color color3 = generateRandomColor();
        Color color4 = generateRandomColor();
        Color color5 = generateRandomColor();
        Color color6 = generateRandomColor();
        Color color7 = generateRandomColor();
        Color color8 = generateRandomColor();
        Color color9 = generateRandomColor();
        Color color10 = generateRandomColor();
        Color color11 = generateRandomColor();
        Color color12 = generateRandomColor();



        // Generate corresponding images for the colors
        Image image1 = generateImageFromColor(color1);
        Image image2 = generateImageFromColor(color2);
        Image image3 = generateImageFromColor(color3);
        Image image4 = generateImageFromColor(color4);
        Image image5 = generateImageFromColor(color5);
        Image image6 = generateImageFromColor(color6);
        Image image7 = generateImageFromColor(color7);
        Image image8 = generateImageFromColor(color8);
        Image image9 = generateImageFromColor(color9);
        Image image10 = generateImageFromColor(color10);
        Image image11 = generateImageFromColor(color11);
        Image image12 = generateImageFromColor(color12);

        // Set the generated images to the ImageViews
        control1.setImage(image1);
        control2.setImage(image2);
        control3.setImage(image3);
        control4.setImage(image4);
        control5.setImage(image5);
        control6.setImage(image6);
        control7.setImage(image7);
        control8.setImage(image8);
        control9.setImage(image9);
        control10.setImage(image10);
        control11.setImage(image11);
        control12.setImage(image12);
        // Set images and perform other operations for additional ImageViews
    }*/
    @FXML
    private void displayResults(Stage primaryStage) {
        // Retrieve the voters and choices from the voting system
        //List<Voter> voters = votingSystem.getListVoters();


        // Load the Results.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Results.fxml"));

        try {
            // Load the root node and create the scene
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Get the controller instance
            ResultsController resultsController = loader.getController();

            // Initialize the controller with the voters and choices
            resultsController.initialize(votingSystem, primaryStage);

            // Show the scene on the stage
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addChoices(Voter voter, List<Integer> choices){
        votingSystem.addVoter(voter, choices);
    }

    @FXML
    private void handleVote(MouseEvent event) {
        ImageView selectedImageView = (ImageView) event.getSource();

        // Get the ID of the selected ImageView
        String imageViewId = selectedImageView.getId();

        // Extract the number from the imageViewId (e.g., control1 -> 1)
        int colorIndex = Integer.parseInt(imageViewId.substring(7));
        colorIndexes.add(colorIndex);


        // Get the candidate color from the Ballot
        String candidateColor = votingSystem.getBallot().getCandidates()[colorIndex - 1];

        // Get the corresponding Color object for the selected ImageView
        Color selectedColor = ((Color) selectedImageView.getImage().getPixelReader().getColor(0, 0));

        // Get the color name from the colorNameMap
        String colorName = getColorName(selectedColor);
        // Add the choices to the list of choices

        choices.add(colorIndex);
        // Create a Voter and cast the vote
        if (voter == null) {


            voter = listOfVoters.get(voterIndex);

        }
        if (voter.getVotes().contains(colorIndex-1)) {
            // Display an error message indicating that the voter has already voted for this color
            String errorMessage = "You have already voted for this color!";
            Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.OK);
            alert.showAndWait();
            return; // Exit the method to prevent registering the duplicate vote
        }



        voter.castVote(votingSystem.getBallot(), colorIndex);






        String voteInfo = "You voted for: " + colorName;

// Add the vote info to the userVotesListView
        userVotesListView.getItems().add(voteInfo);





        // Show a confirmation message to the user
        String message = "Vote registered for " + colorName + "!";
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    private void handleDiscardVote(ActionEvent event) {
        if (voter != null && !voter.getVotes().isEmpty()) {
            // Remove the last vote from the voter's votes
            int lastIndex = voter.getVotes().size() - 1;
            voter.getVotes().remove(lastIndex);

            // Remove the last vote info from the userVotesListView
            if (!userVotesListView.getItems().isEmpty()) {
                userVotesListView.getItems().remove(userVotesListView.getItems().size() - 1);
            }

            // Show a confirmation message to the user
            String message = "Last vote discarded.";
            Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void handlePreviousButton(ActionEvent event) {
        if (voterIndex > 0) {
            // Decrement the voter index to go to the previous voter
            voterIndex--;
            nextButton.setText("Next voter");
            voter = listOfVoters.get(voterIndex) ;

            // Update the voter label
            String voterName = listOfVoters.get(voterIndex).getName();
            voterLabel.setText("Voter: " + voterName);

            // Clear the userVotesListView
            userVotesListView.getItems().clear();

            // Show a confirmation message to the user
            String message = "Previous voter selected.";
            Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
            alert.showAndWait();
        }
    }


    private String getColorName(Color color) {
        for (Map.Entry<Color, String> entry : colorNameMap.entrySet()) {
            if (entry.getKey().equals(color)) {
                return entry.getValue();
            }
        }
        return "Unknown";
    }

    private Color[] generateRandomColors(int numColors) {
        Color[] colors = new Color[numColors];
        Random random = new Random();
        for (int i = 0; i < numColors; i++) {
            // Generate random RGB values
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            colors[i] = Color.rgb(red, green, blue);
        }
        return colors;
    }

    private Image generateImageFromColor(Color color) {
        // Create a transparent image of 100x100 pixels and set the specified color
        int width = 334;
        int height = 200;
        WritableImage image = new WritableImage(width, height);
        PixelWriter pixelWriter = image.getPixelWriter();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixelWriter.setColor(x, y, color);
            }
        }
        return image;
    }
}

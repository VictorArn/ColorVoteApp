package ro.netvoip.voteapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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

    private VotingSystem votingSystem;

    // Map to associate color names with their Color objects
    private Map<Color, String> colorNameMap;

    public void initialize() {
        String voterName = "John Doe";
        voterLabel.setText("Voter: " + voterName);
        // Create a Ballot with candidate names
        String[] candidateNames = {"Color 1", "Color2", "Color 3", "Color 4", "Color 5", "Color 6", "Color 7", "Color 8", "Color 9", "Color 10", "Color 11", "Color 12",};

        // Generate random colors for the candidates
        Color[] candidateColors = generateRandomColors(candidateNames.length);

        // Create a Ballot with the candidate names
        Ballot ballot = new Ballot(candidateNames);

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
    private void handleVote(MouseEvent event) {
        ImageView selectedImageView = (ImageView) event.getSource();

        // Get the ID of the selected ImageView
        String imageViewId = selectedImageView.getId();

        // Extract the number from the imageViewId (e.g., control1 -> 1)
        int colorIndex = Integer.parseInt(imageViewId.substring(7));

        // Get the candidate color from the Ballot
        String candidateColor = votingSystem.getBallot().getCandidates()[colorIndex - 1];

        // Get the corresponding Color object for the selected ImageView
        Color selectedColor = ((Color) selectedImageView.getImage().getPixelReader().getColor(0, 0));

        // Get the color name from the colorNameMap
        String colorName = getColorName(selectedColor);

        // Create a Voter and cast the vote
        Voter voter = new Voter("John Doe", 1);
        voter.castVote(votingSystem.getBallot(), colorIndex);

        // Add the Voter to the VotingSystem
        List<Integer> choices = new ArrayList<>();
        choices.add(colorIndex);
        votingSystem.addVoter(voter, choices);

        // Show a confirmation message to the user
        String message = "Vote registered for " + colorName + "!";
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.showAndWait();
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

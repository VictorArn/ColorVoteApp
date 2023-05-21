package ro.netvoip.voteapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.Random;

public class VoteAppController {

    @FXML
    private ImageView control1;
    @FXML
    private ImageView control2;

    public void initialize() {
        // Generate random colors
        Color color1 = generateRandomColor();
        Color color2 = generateRandomColor();
        // Generate corresponding images for the colors
        Image image1 = generateImageFromColor(color1);
        Image image2 = generateImageFromColor(color2);

        // Set the generated images to the ImageViews
        control1.setImage(image1);
        control2.setImage(image2);
        // Set images and perform other operations for additional ImageViews
    }

    private Color generateRandomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return Color.rgb(red, green, blue);
    }

    private Image generateImageFromColor(Color color) {
        // Create a transparent image of 100x100 pixels and set the specified color
        int width = 100;
        int height = 100;
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

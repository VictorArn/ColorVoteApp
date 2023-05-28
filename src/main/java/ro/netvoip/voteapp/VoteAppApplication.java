package ro.netvoip.voteapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import ro.netvoip.voteapp.StartingMenuController;
import ro.netvoip.voteapp.VoteAppController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;


public class VoteAppApplication extends Application {
    private ServerSocket serverSocket;
    private boolean serverRunning;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // server-related
        //------------------------------------------------------------------------------------------
        // Start the server on port 5000 in a separate thread
        serverRunning = true;
        Thread serverThread = new Thread(this::startServer);
        serverThread.start();

        //------------------------------------------------------------------------------------------


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
            if(numberOfVoters == 0){
                String errorMessage = "Please enter an integer greater than 0!";
                Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.OK);
                alert.showAndWait();
                return;
            }
            if(numberOfVoters == -1)
            {
                return;
            }

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

//------------------------------------------------------------------------------------------
    @Override
    public void stop() throws Exception {
        super.stop();
        stopServer();
    }

    //------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        launch(args);
    }


//------------------------------------------------------------------------------------------
private void startServer() {
    int portNumber = 5000;

    try {
        serverSocket = new ServerSocket(portNumber);
        System.out.println("Server started on port " + portNumber);

        while (serverRunning) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client connected: " + clientSocket);

            // Create a new thread to handle the client connection
            Thread thread = new Thread(() -> handleClient(clientSocket));
            thread.start();
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

    private void handleClient(Socket clientSocket) {
        try {
            // Get the input and output streams for the client connection
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            // Read the request from the client
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String request = reader.readLine();

            // Process the request and generate the response
            String response = processRequest(request);

            // Send the response back to the client
            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.println(response);

            // Close the streams and the client connection
            writer.close();
            reader.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processRequest(String request) {

        return "Response from server: " + request;
    }

    private void stopServer() {
        serverRunning = false;
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




}

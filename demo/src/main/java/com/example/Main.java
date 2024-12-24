package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private db conn;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene.fxml"));
            Parent root = loader.load();

            // Create the scene
            Scene scene = new Scene(root, 900, 900);
            
            // Add CSS
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            
            // Configure the stage
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint(""); // Optional: hide the exit hint
            primaryStage.setResizable(true);
            primaryStage.setWidth(900);
            primaryStage.setHeight(800);
            primaryStage.setTitle("Reservation Dashboard");
            primaryStage.setResizable(false);
            
            // Show the stage
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        // Close database connection when application stops
        if (conn != null) {
            conn.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
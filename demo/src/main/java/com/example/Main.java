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
         // charger fichier fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 900, 900);
            
            scene.getStylesheets().add(getClass().getResource("style2.css").toExternalForm());
            
            
           
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
        
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
       
        if (conn != null) {
            conn.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
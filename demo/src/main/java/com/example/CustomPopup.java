package com.example;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomPopup {

    public static void showUserAddedPopup(User user) {
        // Create a new Stage for the popup
        Stage stage = new Stage();
        stage.setTitle("User Added Successfully");

        // Create the content for the popup
        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-padding: 20; -fx-background-color: #ffffff;");
        
        // Create labels for user details
        vbox.getChildren().add(new javafx.scene.control.Label("User Added Successfully!"));
        vbox.getChildren().add(new javafx.scene.control.Label("ID: " + user.getId()));
        vbox.getChildren().add(new javafx.scene.control.Label("Name: " + user.getNom() + " " + user.getPrenom()));
        vbox.getChildren().add(new javafx.scene.control.Label("Email: " + user.getEmail()));
        vbox.getChildren().add(new javafx.scene.control.Label("Type: " + user.getType()));
        
        // Create a close button
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());
        vbox.getChildren().add(closeButton);

        // Create and show the scene
        Scene scene = new Scene(vbox, 300, 200);
        stage.setScene(scene);
        stage.show();
    }
}


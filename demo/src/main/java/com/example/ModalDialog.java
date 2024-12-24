package com.example;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModalDialog {

    public static void showConfirmationDialog(String message) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);  // Makes it a modal window
        dialogStage.setTitle("Confirmation");

        VBox vbox = new VBox(10);
        vbox.getChildren().add(new javafx.scene.control.Label(message));
        
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> dialogStage.close());
        vbox.getChildren().add(closeButton);

        Scene dialogScene = new Scene(vbox, 250, 100);
        dialogStage.setScene(dialogScene);
        dialogStage.showAndWait();  // Wait for the user to close it
    }
}


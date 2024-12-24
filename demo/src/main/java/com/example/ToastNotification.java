package com.example;
  import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.stage.Stage;

public class ToastNotification {

    public static void showToast(Stage stage, String message) {
        // Create a label for the message
        Label toastLabel = new Label(message);
        toastLabel.setFont(new Font("Arial", 16));
        toastLabel.setTextFill(Color.WHITE);
        toastLabel.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-padding: 10;");

        // Add the toast label to a StackPane
        StackPane root = new StackPane(toastLabel);
        root.setStyle("-fx-alignment: center;");

        // Create a timeline for fading out the toast
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> stage.close()));
        timeline.setCycleCount(1);
        timeline.play();

        // Show the toast
        stage.setScene(new Scene(root, 300, 100));
        stage.show();
    }
}

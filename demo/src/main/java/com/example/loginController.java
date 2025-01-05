package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;

public class loginController {


    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<String> userTypeComboBox;
    @FXML private Label errorLabel;
    
    private GenericDAO<User> userDao;

    @FXML
    public void initialize() {
       
        db conn = db.getInstance();
        userDao = new UserImpl(conn);
        
       
        userTypeComboBox.getItems().addAll("ETUDIANT", "PROFESSEUR");
    }

    @FXML
    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String userType = userTypeComboBox.getValue();

       
        if (email.isEmpty() || password.isEmpty() || userType == null) {
            errorLabel.setText("Please fill in all fields");
            return;
        }

        try {
           
            boolean authentifie = false;
            User utilisateurauthentifie = null;
            
            for (User user : userDao.getAll()) {
                if (user.getEmail().equals(email) && user.getType().equals(userType)) {
                   
                    authentifie = true;
                    utilisateurauthentifie = user;
                    break;
                }
            }

            if (authentifie) {
               
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene.fxml"));
                Parent root = loader.load();
                
               
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                Stage stage = new Stage();
                   stage.setTitle("Management System");
                stage.setScene(scene);

            stage.setFullScreen(true);
            stage.setFullScreenExitHint("Press ESC to exit fullscreen");
            stage.setResizable(true);
            stage.setWidth(900);
            stage.setHeight(800);
            stage.setTitle("Reservation Details");


             stage.setResizable(false);
            
                
            // Cela permet de basculer d'une fenêtre de connexion à une autre fenêtre 
                Stage loginStage = (Stage) emailField.getScene().getWindow();
                // on recupere la scene ou email fiels existe puis son fenetre et on le cast vers un objet de type de stage
                loginStage.close();
                
              
                stage.show();
            } else {
                errorLabel.setText("Invalid credentials or user type");
            }
        } catch (IOException e) {
            errorLabel.setText("Error loading application: " + e.getMessage());
        }
    }
}


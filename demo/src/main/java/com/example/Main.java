package com.example;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

import com.example.GenericDAO;


public class Main extends Application {
     private db conn;
    GenericDAO<User> userDao;
        GenericDAO<event> eventDao ;
        GenericDAO<salle> salleDao ;
        GenericDAO<terrain> terrainDao ;
        GenericDAO<reservation> reservationDao;

    @Override
    public void start(Stage primaryStage) {
       

        /*chaque class DAO utilise un singleton unique */
        conn = db.getInstance();// obtienir l instance unique de la classe db 
        userDao = new UserImpl(conn);
        eventDao = new eventImpl(conn);
        salleDao = new salleDaoImpl(conn);
        terrainDao = new terrainDaoImpl(conn);
        reservationDao = new reservationDaoImpl(conn);

        Label idLabel = new Label("ID:");
        TextField idField = new TextField();
        idField.setPromptText("Enter User ID");
        Label nomLabel = new Label("Nom:");
        TextField nomField = new TextField();
      
        nomField.setPromptText("Enter Name");
   
        Label prenomLabel = new Label("Prenom:");
        TextField prenomField = new TextField();
        prenomField.setPromptText("Enter Prenom");
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
emailField.setPromptText("Enter Email:");
        Label typeLabel = new Label("Type :");
        ComboBox<String> typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll("ETUDIANT", "PROFESSEUR");

        Button addUserButton = new Button("Add User");
        Button viewUsersButton = new Button("View All Users");
        addUserButton.disableProperty().bind(
            idField.textProperty().isEmpty()
            .or(nomField.textProperty().isEmpty())
            .or(prenomField.textProperty().isEmpty())
            .or(emailField.textProperty().isEmpty())
        );
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

       
        GridPane userGrid = new GridPane();
        userGrid.setPadding(new Insets(10));
        userGrid.setVgap(5);
        userGrid.setHgap(10);

        userGrid.add(idLabel, 0, 0);
        userGrid.add(idField, 1, 0);

        userGrid.add(nomLabel, 0, 1);
        userGrid.add(nomField, 1, 1);

        userGrid.add(prenomLabel, 0, 2);
        userGrid.add(prenomField, 1, 2);

        userGrid.add(emailLabel, 0, 3);
        userGrid.add(emailField, 1, 3);

        userGrid.add(typeLabel, 0, 4);
        userGrid.add(typeComboBox, 1, 4);

        userGrid.add(addUserButton, 0, 5);
        userGrid.add(viewUsersButton, 1, 5);

        userGrid.add(resultArea, 0, 6, 2, 1);

       
        Label eventIdLabel = new Label("Event ID:");
        TextField eventIdField = new TextField();
        eventIdField.setPromptText("Enter Event ID");
        Label eventNameLabel = new Label("Event Name:");
        TextField eventNameField = new TextField();
        eventNameField.setPromptText("Enter Event Name:");

        Label eventDateLabel = new Label("Event Date :");
        TextField eventDateField = new TextField();
        eventDateField.setPromptText("Enter Event Date : ");
        Label descriptionLabel = new Label("Description :");
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Enter Event Description:");
        Label user_idLabel = new Label("user id:");
        TextField user_idField = new TextField();
        user_idField.setPromptText("Enter User id:");
        Button addEventButton = new Button("Add Event");
        Button viewEventsButton = new Button("View All Events");
        addEventButton.disableProperty().bind(
            idField.textProperty().isEmpty()
            .or(eventIdField.textProperty().isEmpty())
            .or(eventNameField.textProperty().isEmpty()).or(eventDateField.textProperty().isEmpty())
        );
        TextArea eventResultArea = new TextArea();
        eventResultArea.setEditable(false);

        GridPane eventGrid = new GridPane();
        eventGrid.setPadding(new Insets(10));
        eventGrid.setVgap(5);
        eventGrid.setHgap(10);

        eventGrid.add(eventIdLabel, 0, 0);
        eventGrid.add(eventIdField, 1, 0);

        eventGrid.add(eventNameLabel, 0, 1);
        eventGrid.add(eventNameField, 1, 1);

        eventGrid.add(eventDateLabel, 0, 2);
        eventGrid.add(eventDateField, 1, 2);

        eventGrid.add(descriptionLabel, 0, 3);
        eventGrid.add(descriptionField, 1, 3);

        eventGrid.add(user_idLabel, 0, 4);
        eventGrid.add(user_idField, 1, 4);

        eventGrid.add(addEventButton, 0, 5);
        eventGrid.add(viewEventsButton, 1, 5);

        eventGrid.add(eventResultArea, 0, 6, 2, 1);

      
        Label salleIdLabel = new Label("salle ID:");
        TextField salleIdField = new TextField();
      salleIdField.setPromptText("Enter Salle id:");
        Label salleNameLabel = new Label("salle Name:");
        TextField salleNameField = new TextField();
        salleNameField.setPromptText("Enter Salle Name:");
        Label salleCapaciteLabel = new Label("capacite:");
        TextField salleCapaciteField = new TextField();
        salleCapaciteField.setPromptText("Enter  salle Capacity:");
        Button addSalleButton = new Button("Add salle");
        Button viewSalleButton = new Button("View All salles");
        addSalleButton.disableProperty().bind(

            salleIdField.textProperty().isEmpty()
            .or(salleNameField.textProperty().isEmpty())
            .or(salleCapaciteField.textProperty().isEmpty())
        );
        TextArea salleResultArea = new TextArea();
        salleResultArea.setEditable(false);

         Label TerrainIdLabel = new Label("Terrain ID:");
         TextField TerrainIdField = new TextField();
 TerrainIdField.setPromptText("Enter Terrain id:");
         Label TerrainNameLabel = new Label("Terrain Name:");
         TextField TerrainNameField = new TextField();
 TerrainNameField.setPromptText("Enter Terrain name:");
         Label TerrainTypeLabel = new Label("type:");
         TextField TerrainTypeField = new TextField();
 TerrainTypeField.setPromptText("Enter Terrain type:");
         Button addTerrainButton = new Button("Add Terrain");
         Button viewTerrainButton = new Button("View All Terrains");
         addTerrainButton.disableProperty().bind(
            TerrainIdField.textProperty().isEmpty()
            .or(TerrainNameField.textProperty().isEmpty())
            .or(TerrainTypeField.textProperty().isEmpty())
        );
         TextArea TerrainResultArea = new TextArea();
         TerrainResultArea.setEditable(false);

          // Labels and Fields for full reseravtion
          Label ReservationIdLabel = new Label("Reservation ID:");
          TextField ReservationIdField = new TextField();
          ReservationIdField.setPromptText("Enter Reservation Id:");
          Label ReservationIdUserLabel = new Label("Reservation ID user:");
          TextField ReservationIdUserField = new TextField();
          ReservationIdUserField.setPromptText("Enter Reservation's User Id:");
          Label ReservationIdEventLabel = new Label("Reservation ID event:");
          TextField ReservationIdEventField = new TextField();
          ReservationIdEventField.setPromptText("Enter Reservation's Event Id:");
          Label ReservationIdSalleLabel = new Label("Reservation ID salle:");
          TextField ReservationIdSalleField = new TextField();
          ReservationIdSalleField.setPromptText("Enter Reservation's salle Id:");
          Label ReservationIdTerrainLabel = new Label("Reservation ID terrain:");
          TextField ReservationIdTerrainField = new TextField();
          ReservationIdTerrainField.setPromptText("Enter Reservation's Terrain Id:");
          Label ReservationDateLabel = new Label("Reservation Date:");
          TextField ReservationDateField = new TextField();
          ReservationDateField.setPromptText("Enter Reservation Date:");
          Button addReservationButton = new Button("Add Reservation");
          Button viewReservationButton = new Button("View All Reservations");
          addReservationButton.disableProperty().bind(
            ReservationIdField.textProperty().isEmpty()
            .or(ReservationDateField.textProperty().isEmpty())
            .or(ReservationIdEventField.textProperty().isEmpty())
            .or(ReservationIdSalleField.textProperty().isEmpty())
            .or(ReservationIdTerrainField.textProperty().isEmpty())
            .or(ReservationIdUserField.textProperty().isEmpty())
        );
        addReservationButton.setTooltip(new Tooltip("Click to add a new Reservation"));
          TextArea ReservationResultArea = new TextArea();
          ReservationResultArea.setEditable(false);
 

        GridPane salleGrid = new GridPane();
        salleGrid.setPadding(new Insets(4));
        salleGrid.setVgap(5);
        salleGrid.setHgap(10);

        salleGrid.add(salleIdLabel, 0, 0);
        salleGrid.add(salleIdField, 1, 0);

        salleGrid.add(salleNameLabel, 0, 1);
        salleGrid.add(salleNameField, 1, 1);

        salleGrid.add(salleCapaciteLabel, 0, 2);
        salleGrid.add(salleCapaciteField, 1, 2);

        salleGrid.add(addSalleButton, 0, 3);
        salleGrid.add(viewSalleButton, 1, 3);

        salleGrid.add(salleResultArea, 0, 4, 2, 1);

 GridPane terrainGrid = new GridPane();
 terrainGrid.setPadding(new Insets(5));
 terrainGrid.setVgap(5);
 terrainGrid.setHgap(5);

 terrainGrid.add(TerrainIdLabel, 0, 0);
 terrainGrid.add(TerrainIdField, 1, 0);

 terrainGrid.add(TerrainNameLabel, 0, 1);
 terrainGrid.add(TerrainNameField, 1, 1);

 terrainGrid.add(TerrainTypeLabel, 0, 2);
 terrainGrid.add(TerrainTypeField, 1, 2);

 terrainGrid.add(addTerrainButton, 0, 3);
 terrainGrid.add(viewTerrainButton, 1, 3);

 terrainGrid.add(TerrainResultArea, 0, 4, 2, 1);
 
GridPane reservationGrid = new GridPane();
reservationGrid.setPadding(new Insets(5));
reservationGrid.setVgap(5);
reservationGrid.setHgap(5);


reservationGrid.add(ReservationIdLabel, 0, 0);
reservationGrid.add(ReservationIdField, 1, 0);

reservationGrid.add(ReservationIdUserLabel, 0, 1);
reservationGrid.add(ReservationIdUserField, 1, 1);

reservationGrid.add(ReservationIdSalleLabel, 0, 2);
reservationGrid.add(ReservationIdSalleField, 1, 2);

reservationGrid.add(ReservationIdEventLabel, 0, 3);
reservationGrid.add(ReservationIdEventField, 1, 3);

reservationGrid.add(ReservationIdTerrainLabel, 0, 4);
reservationGrid.add(ReservationIdTerrainField, 1, 4);

reservationGrid.add(ReservationDateLabel, 0, 5);
reservationGrid.add(ReservationDateField, 1, 5);

reservationGrid.add(addReservationButton, 0, 6);
reservationGrid.add(viewReservationButton, 1, 6);

reservationGrid.add(ReservationResultArea, 0, 7, 2, 1);
       
     

        addUserButton.setOnAction(event -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                String email = emailField.getText();
                String type = typeComboBox.getValue();

                if (type == null || (!type.equals("ETUDIANT") && !type.equals("PROFESSEUR"))) {
                    resultArea.setText("Invalid user type. Please select 'ETUDIANT' or 'PROFESSEUR'.");
                    return;
                }

                User newUser = new User(id, nom, prenom, email, type);
                userDao.add(newUser);

                resultArea.setText("User added: " + newUser);
                idField.clear();
                nomField.clear();
                prenomField.clear();
                emailField.clear();
                typeComboBox.getSelectionModel().clearSelection();
            } catch (Exception e) {
                resultArea.setText("Error: " + e.getMessage());
            }
        });

        viewUsersButton.setOnAction(event -> {
            List<User> users = userDao.getAll();
            StringBuilder result = new StringBuilder("List of Users:\n");
            for (User user : users) {
                result.append(user).append("\n");
            }
            resultArea.setText(result.toString());
        });

        addEventButton.setOnAction(event -> {
            try {
                int eventId = Integer.parseInt(eventIdField.getText());
                String eventName = eventNameField.getText();
                String eventDate = eventDateField.getText();
                String description = descriptionField.getText();
                int User_id = Integer.parseInt(user_idField.getText());

                event newEvent = new event(eventId, eventName, eventDate, description, User_id);
                eventDao.add(newEvent);

                eventResultArea.setText("Event added: " + newEvent);
                eventIdField.clear();
                eventNameField.clear();
                eventDateField.clear();
            } catch (Exception e) {
                eventResultArea.setText("Error: " + e.getMessage());
            }
        });

        
        viewEventsButton.setOnAction(event -> {
            List<event> events = eventDao.getAll();
            StringBuilder result = new StringBuilder("List of Events:\n");
            for (event eventItem : events) {
                result.append(eventItem).append("\n");
            }
            eventResultArea.setText(result.toString());
        });

        addSalleButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(salleIdField.getText());
                String nomSalle = salleNameField.getText();
                int capacite = Integer.parseInt(salleCapaciteField.getText());
                salle salle = new salle(id, nomSalle, capacite);
                salleDao.add(salle);
                salleResultArea.setText("Salle added: " + salle);
                salleIdField.clear();
                salleNameField.clear();
                salleCapaciteField.clear();
            } catch (Exception ee) {
                salleResultArea.setText("Error: " + ee.getMessage());
            }
        });

       
        viewSalleButton.setOnAction(event -> {
            List<salle> salles = salleDao.getAll();
            StringBuilder result = new StringBuilder("List of salles:\n");
            for (salle salleItem : salles) {
                result.append(salleItem).append("\n");
            }
            salleResultArea.setText(result.toString());
        });
        addTerrainButton.setOnAction(e->{
            try {
                int id = Integer.parseInt(TerrainIdField.getText());
                String nomTerrain = TerrainNameField.getText();
               String type = TerrainTypeField.getText();
              terrain terrain= new terrain(id, nomTerrain, type);
                terrainDao.add(terrain);
                TerrainResultArea.setText("terrain added: " + terrain);
                TerrainIdField.clear();
                TerrainNameField.clear();
                TerrainTypeField.clear();
            } catch (Exception ee) {
                TerrainResultArea.setText("Error: " + ee.getMessage());
            }
        });
        viewTerrainButton.setOnAction(event -> {
            List<terrain> terrains = terrainDao.getAll();
            StringBuilder result = new StringBuilder("List of Terrains:\n");
            for (terrain terrainItem : terrains) {
                result.append(terrainItem).append("\n");
            }
            TerrainResultArea.setText(result.toString());
        });
        

        addReservationButton.setOnAction(event -> {
            try {
                int id = Integer.parseInt(ReservationIdField.getText());
                int id_user = Integer.parseInt(ReservationIdUserField.getText());
                int id_event = Integer.parseInt(ReservationIdEventField.getText());
                int id_salle = Integer.parseInt(ReservationIdSalleField.getText());
                int id_terrain = Integer.parseInt(ReservationIdTerrainField.getText());
                String date_reservation= ReservationDateField.getText();
              

                reservation newReservation = new reservation(id, id_user, id_event, id_salle, id_terrain, date_reservation);
                reservationDao.add(newReservation);

                ReservationResultArea.setText("Reservation added: " + newReservation);
                ReservationIdField.clear();
                ReservationIdEventField.clear();
                ReservationIdSalleField.clear();
                ReservationIdTerrainField.clear();
                ReservationIdUserField.clear();
                ReservationDateField.clear();
                typeComboBox.getSelectionModel().clearSelection();
            } catch (Exception e) {
                ReservationResultArea.setText("Error: " + e.getMessage());
            }
        });
        viewReservationButton.setOnAction(event -> {
            List<reservation> reservations = reservationDao.getAll();
            StringBuilder result = new StringBuilder("List of reservations:\n");
            for (reservation reservationItem : reservations) {
                result.append(reservationItem).append("\n");
            }
            ReservationResultArea.setText(result.toString());
        });
        
       /* ScrollPane terrainScrollPane = new ScrollPane(terrainGrid);
        terrainScrollPane.setFitToWidth(true);
        terrainScrollPane.setFitToHeight(true); */
        
     
        
       /* TitledPane userPane = new TitledPane("User", userGrid);
        TitledPane eventPane = new TitledPane("Event", eventGrid);
        TitledPane sallePane = new TitledPane("Salle", salleGrid);
        TitledPane terrainPane = new TitledPane("Terrain", terrainGrid);
        TitledPane reservationPane = new TitledPane("Resssservation" , reservationGrid);
        
        Accordion accordion = new Accordion(userPane, eventPane, sallePane, terrainPane, reservationPane);
        */ 
        TabPane tabPane = new TabPane();
        Tab userTab = new Tab("Users", userGrid);
        Tab eventTab = new Tab("Events", eventGrid);
        Tab salleTab = new Tab("Salles", salleGrid);
        Tab terrainTab = new Tab("Terrains", terrainGrid);
        Tab reservationTab = new Tab("Reservations", reservationGrid);
        tabPane.getTabs().addAll(userTab, eventTab, salleTab, terrainTab, reservationTab);
      
        Label userNombre = new Label("Total Users: " + userDao.getAll().size());
        Label eventNombre = new Label("Upcoming Events: " + eventDao.getAll().size());
        Label salleNombre = new Label("Total salles: " + salleDao.getAll().size());
        Label terrainsNombre = new Label("Total Terrains: " + terrainDao.getAll().size());
        Label reservationNombre = new Label(" Total Reservations : " + reservationDao.getAll().size());
  
        VBox dashboard = new VBox( 10,userNombre, eventNombre, salleNombre, terrainsNombre, reservationNombre);
        dashboard.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #cccccc; -fx-border-width: 1;"); 
        dashboard.setPadding(new Insets(10));
        PieChart chart = new PieChart();
        chart.getData().add(new PieChart.Data("Events", eventDao.getAll().size()));
        chart.getData().add(new PieChart.Data("Users", userDao.getAll().size()));
        chart.getData().add(new PieChart.Data("Salles", salleDao.getAll().size()));
        chart.getData().add(new PieChart.Data("Terrains", terrainDao.getAll().size()));
        chart.getData().add(new PieChart.Data("Reservations", reservationDao.getAll().size()));
        chart.setPadding(new Insets(10));
      chart.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #cccccc; -fx-border-width: 1;"); 
        VBox root = new VBox(10);
        root.setPadding(new Insets(5));
         root.getChildren().addAll(tabPane, dashboard, chart);
        

        Scene scene = new Scene(root, 900, 900);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                primaryStage.setWidth(900); 
                primaryStage.setHeight(800);
             


                primaryStage.setTitle("Reservation Dashboard");
                primaryStage.setResizable(false);
                primaryStage.show();
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

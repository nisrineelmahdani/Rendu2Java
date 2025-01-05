package com.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class MyController {
    private db conn;
    private GenericDAO<User> userDao;
    private GenericDAO<event> eventDao;
    private GenericDAO<salle> salleDao;
    private GenericDAO<terrain> terrainDao;
    private GenericDAO<reservation> reservationDao;

    @FXML private TextField idField;
    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField emailField;
    @FXML private ComboBox<String> typeComboBox;
    @FXML private TextArea UserResultArea;
    
    @FXML private TextField eventIdField;
    @FXML private TextField eventNameField;
   // @FXML private TextField eventDateField;
   @FXML private DatePicker EventDateField;
    @FXML private TextArea descriptionField;
    //@FXML private TextField user_idField;
    @FXML private TextArea EventResultArea;
    @FXML private ComboBox<String> EventIdUserComboBox;

    @FXML private TextField salleIdField;
    @FXML private TextField salleNameField;
    @FXML private TextField salleCapaciteField;
    @FXML private TextArea SalleResultArea;
    
    @FXML private TextField TerrainIdField;
    @FXML private TextField TerrainNameField;
    @FXML private TextField TerrainTypeField;
    @FXML private TextArea TerrainResultArea;
 
    @FXML private TextField ReservationIdField;
    @FXML private ComboBox<String> ReservationIdUserComboBox;
    @FXML private ComboBox<String> ReservationIdEventComboBox;
    @FXML private ComboBox<String> ReservationIdSalleComboBox;
    @FXML private ComboBox<String> ReservationIdTerrainComboBox;
   
    @FXML private DatePicker ReservationDateField;
    @FXML private TextArea ReservationResultArea;
    
    @FXML private Label userNombre;
    @FXML private Label eventNombre;
    @FXML private Label salleNombre;
    @FXML private Label terrainsNombre;
    @FXML private Label reservationNombre;
    
    @FXML private PieChart statisticsChart;
    @FXML private Button addUserButton;
    @FXML private Button addEventButton;
    @FXML private Button addSalleButton;
    @FXML private Button addTerrainButton;
    @FXML private Button addReservationButton;

    @FXML private TableView<terrain> terrainTable;
    @FXML private TableColumn<terrain, String> nameColumn;
    @FXML private TableColumn<terrain, String> typeColumn;
    
    @FXML private TableView<salle> salleTable;
    @FXML private TableColumn<salle, String> sallenameColumn;
    @FXML private TableColumn<salle, Integer> salleCapaciteColumn;
    
    @FXML private TableView<event> eventTable;
    @FXML private TableColumn<event, String> eventnameColumn;
    @FXML private TableColumn<event, String> eventDateColumn;
    @FXML private TableColumn<event, String> DescriptionColumn;
    @FXML private TableColumn<event, Integer> Eventuser_idColumn;
    
    @FXML private TableView<User> userTable;
    @FXML private TableColumn<User, String> usernameColumn;
    @FXML private TableColumn<User, String> userPrenomColumn;
    @FXML private TableColumn<User, String> useremailColumn;
    @FXML private TableColumn<User, String> usertypeColumn;
    
    @FXML private TableView<reservation> reservationTable;
    @FXML private TableColumn<reservation, Integer> user_idColumn;
    @FXML private TableColumn<reservation, Integer> event_idColumn;
    @FXML private TableColumn<reservation, Integer> salle_idColumn;
    @FXML private TableColumn<reservation, Integer> terrain_idColumn;
    @FXML private TableColumn<reservation, LocalDate> date_idColumn;

    private terrain selectedTerrain;
    private salle selectedSalle;

    private ObservableList<terrain> terrainList = FXCollections.observableArrayList();
    private ObservableList<salle> salleList = FXCollections.observableArrayList();
    private ObservableList<event> eventList = FXCollections.observableArrayList();
    private ObservableList<User> userList = FXCollections.observableArrayList();
    private ObservableList<reservation> reservationsList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        conn = db.getInstance();
        userDao = new UserImpl(conn);
        eventDao = new eventImpl(conn);
        salleDao = new salleDaoImpl(conn);
        terrainDao = new terrainDaoImpl(conn);
        reservationDao = new reservationDaoImpl(conn);
        
        typeComboBox.getItems().addAll("ETUDIANT", "PROFESSEUR");

    // les valeurs des colonnes des tables
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nomTerrain"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        sallenameColumn.setCellValueFactory(new PropertyValueFactory<>("nom_salle"));
        salleCapaciteColumn.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        eventnameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        Eventuser_idColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        userPrenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        useremailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        usertypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        user_idColumn.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        event_idColumn.setCellValueFactory(new PropertyValueFactory<>("id_event"));
        salle_idColumn.setCellValueFactory(new PropertyValueFactory<>("id_salle"));
        terrain_idColumn.setCellValueFactory(new PropertyValueFactory<>("id_terrain"));
        date_idColumn.setCellValueFactory(new PropertyValueFactory<>("date_reservation"));

        // Bind tables to their respective lists
        terrainTable.setItems(terrainList);
        salleTable.setItems(salleList);
        userTable.setItems(userList);
        reservationTable.setItems(reservationsList);
        eventTable.setItems(eventList);

        setupButtonBindings();
        updateDashboard();
        updateChart();
        setupReservationListeners();
       // les ids dans les les comboxes
        ObservableList<User> users = FXCollections.observableArrayList(userDao.getAll());
    ObservableList<String> userIds = users.stream()
        .map(user -> String.valueOf(user.getId()))
        .collect(Collectors.toCollection(FXCollections::observableArrayList));
    ReservationIdUserComboBox.setItems(userIds);
    EventIdUserComboBox.setItems(userIds);
    
    // For Salles
    ObservableList<salle> salles = FXCollections.observableArrayList(salleDao.getAll());
    ObservableList<String> salleIds = salles.stream()
        .map(salle -> String.valueOf(salle.getId_salle()))
        .collect(Collectors.toCollection(FXCollections::observableArrayList));
    ReservationIdSalleComboBox.setItems(salleIds);

    // For Events
    ObservableList<event> events = FXCollections.observableArrayList(eventDao.getAll());
    ObservableList<String> eventIds = events.stream()
        .map(event -> String.valueOf(event.getId()))
        .collect(Collectors.toCollection(FXCollections::observableArrayList));
    ReservationIdEventComboBox.setItems(eventIds);

    // For Terrains
    ObservableList<terrain> terrains = FXCollections.observableArrayList(terrainDao.getAll());
    ObservableList<String> terrainIds = terrains.stream()
        .map(terrain -> String.valueOf(terrain.getIdTerrain()))
        .collect(Collectors.toCollection(FXCollections::observableArrayList));
    ReservationIdTerrainComboBox.setItems(terrainIds);
      
    }

private void updateComboBoxIds(ComboBox<String> comboBox, int newId) {
    comboBox.getItems().add(String.valueOf(newId));
}
    private void setupReservationListeners() {
        ReservationIdTerrainComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                try {
                    int terrainId = Integer.parseInt(newValue);
                    selectedTerrain = terrainDao.getById(terrainId);
                    updateAvailableDates();
                } catch (NumberFormatException e) {
                    selectedTerrain = null;
                }
            }
        });

        ReservationIdSalleComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                try {
                    int salleId = Integer.parseInt(newValue);
                    selectedSalle = salleDao.getById(salleId);
                    updateAvailableDates();
                } catch (NumberFormatException e) {
                    selectedSalle = null;
                }
            }
        });

        ReservationDateField.setDayCellFactory(this::createDayCellFactory);
    }
    private DateCell createDayCellFactory(DatePicker datePicker) {
        return new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
    
                if (date == null || empty) {
                    setDisable(true);
                    setStyle("");
                    return;
                }
    
                // Default style - green for available dates
                setStyle("-fx-background-color: #90EE90;"); // Light green
                setDisable(false);
    
                // Only check reservations if terrain or salle is selected
                if (selectedTerrain != null || selectedSalle != null) {
                    List<reservation> existingReservations = reservationDao.getAll();
                    
                    for (reservation res : existingReservations) {
                        // Check if date matches and either terrain or salle matches
                        if (res.getDate_reservation().equals(date)) {
                            boolean isConflict = false;
                            
                            if (selectedTerrain != null && res.getId_terrain() == selectedTerrain.getIdTerrain()) {
                                isConflict = true;
                            }
                            
                            if (selectedSalle != null && res.getId_salle() == selectedSalle.getId_salle()) {
                                isConflict = true;
                            }
                            
                            if (isConflict) {
                                setStyle("-fx-background-color: #FF0000;"); // Red for reserved dates
                                setDisable(true);
                                break;
                            }
                        }
                    }
                }
            }
        };
    }
    
    private boolean isDateAvailable(LocalDate date) {
        if (selectedTerrain == null && selectedSalle == null) {
            return true; // If nothing is selected, all dates are available
        }

        List<reservation> existingReservations = reservationDao.getAll();
        
        for (reservation res : existingReservations) {
            // Skip if the date doesn't match
            if (!res.getDate_reservation().equals(date)) {
                continue;
            }

            // Check terrain conflict
            if (selectedTerrain != null && res.getId_terrain() == selectedTerrain.getIdTerrain()) {
                return false;
            }

            // Check salle conflict
            if (selectedSalle != null && res.getId_salle() == selectedSalle.getId_salle()) {
                return false;
            }
        }
        
        return true;
    }
    private void updateAvailableDates() {
        // Force the DatePicker to refresh its display
        LocalDate currentDate = ReservationDateField.getValue();
        ReservationDateField.setValue(null);
        ReservationDateField.setValue(currentDate);
    }
    
    @FXML
    private void handleAddUser() {
        try {
            int id = Integer.parseInt(idField.getText());
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            String email = emailField.getText();
            String type = typeComboBox.getValue();

            if (type == null || (!type.equals("ETUDIANT") && !type.equals("PROFESSEUR"))) {
                UserResultArea.setText("Invalid user type. Please select 'ETUDIANT' or 'PROFESSEUR'.");
                return;
            }

            User newUser = new User(id, nom, prenom, email, type);
            userDao.add(newUser);
            UserResultArea.setText("User successfully added:\n" + newUser);
            /*  Show success alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("User successfully added:\n" + newUser);
        alert.showAndWait();
            resultArea.setText("User added: " + newUser);*/
            ToastNotification.showToast(new Stage(), "User Added Successfully!");

            // Clear fields
            idField.clear();
            nomField.clear();
            prenomField.clear();
            emailField.clear();
            typeComboBox.getSelectionModel().clearSelection();
            updateComboBoxIds(ReservationIdUserComboBox, newUser.getId());
        updateComboBoxIds(EventIdUserComboBox, newUser.getId());
        } catch (Exception e) {
            UserResultArea.setText("Error: " + e.getMessage());
        }
    }

    // View All Users
    @FXML
    private void handleViewUsers() {
        List<User> users = userDao.getAll();
     userList.clear();
        for (User user : users) {
         userList.add(user);
        }
    
    }

    // Add Event
    @FXML
    private void handleAddEvent() {
        try {
            int eventId = Integer.parseInt(eventIdField.getText());
            String eventName = eventNameField.getText();
            LocalDate eventDate =   EventDateField.getValue();
            String description = descriptionField.getText();
           int idUser = Integer.parseInt(EventIdUserComboBox.getValue().toString());

            event newEvent = new event(eventId, eventName, eventDate, description, idUser);
            eventDao.add(newEvent);
            EventResultArea.setText("Event successfully added:\n" + newEvent);
            ToastNotification.showToast(new Stage(), "Event Added Successfully!");
           
            // Clear fields
            eventIdField.clear();
            eventNameField.clear();
            updateComboBoxIds(ReservationIdEventComboBox, newEvent.getId());
        } catch (Exception e) {
            EventResultArea.setText("Error: " + e.getMessage());
        }
    }

    // View All Events
    @FXML
    private void handleViewEvents() {
        List<event> events = eventDao.getAll();
    
        // Clear the current list to prevent duplication
        eventList.clear();
    
        // Add the new list of events to the ObservableList
        for (event event : events) {
            eventList.add(event);
        }
    }

    // Add Salle
    @FXML
    private void handleAddSalle() {
        try {
            int id = Integer.parseInt(salleIdField.getText());
            String nomSalle = salleNameField.getText();
            int capacite = Integer.parseInt(salleCapaciteField.getText());

            salle salle = new salle(id, nomSalle, capacite);
            salleDao.add(salle);
            SalleResultArea.setText("Salle successfully added:\n" + salle);
            ToastNotification.showToast(new Stage(), "Room Added Successfully!");
            // Clear fields
            salleIdField.clear();
            salleNameField.clear();
            salleCapaciteField.clear();
            updateComboBoxIds(ReservationIdSalleComboBox, salle.getId_salle());
        } catch (Exception e) {
            SalleResultArea.setText("Error: " + e.getMessage());
        }
    }

    // View All Salles
    @FXML
    private void handleViewSalle() {
        List<salle> salles = salleDao.getAll();
        salleList.clear();
        for (salle salle : salles) {
         salleList.add(salle);
        }
      
    }

    // Add Terrain
    @FXML
    private void handleAddTerrain() {
        try {
            int id = Integer.parseInt(TerrainIdField.getText());
            String nomTerrain = TerrainNameField.getText();
            String type = TerrainTypeField.getText();

            terrain terrain = new terrain(id, nomTerrain, type);
            terrainDao.add(terrain);
            TerrainResultArea.setText("Terrain successfully added:\n" + terrain);
            ToastNotification.showToast(new Stage(), "Terrain Added Successfully!");
            // Clear fields
            TerrainIdField.clear();
            TerrainNameField.clear();
            TerrainTypeField.clear();
            updateComboBoxIds(ReservationIdTerrainComboBox, terrain.getIdTerrain());
        } catch (Exception e) {
            TerrainResultArea.setText("Error: " + e.getMessage());
        }
    }

    // View All Terrains
    @FXML
    private void handleViewTerrain() {
        // Get all terrains from the database
        List<terrain> terrains = terrainDao.getAll();
        
        // Clear the current list before adding new data
        terrainList.clear();
    
        // Add the retrieved terrains to the ObservableList
        for (terrain terrain : terrains) {
            terrainList.add(terrain);
        }
    }
    

    // Add Reservation
    @FXML
    private void handleAddReservation() {
        try {
            int id = Integer.parseInt(ReservationIdField.getText());
            int idUser = Integer.parseInt(ReservationIdUserComboBox.getValue().toString());
            int idEvent = Integer.parseInt(ReservationIdEventComboBox.getValue().toString());
            int idSalle = Integer.parseInt(ReservationIdSalleComboBox.getValue().toString());
            int idTerrain = Integer.parseInt(ReservationIdTerrainComboBox.getValue().toString());
            LocalDate dateReservation = ReservationDateField.getValue();

            if (dateReservation == null) {
                ReservationResultArea.setText("Please select a valid date");
                return;
            }

            if (!isDateAvailable(dateReservation)) {
                ReservationResultArea.setText("Selected date is not available for this terrain/salle");
                return;
            }

            reservation newReservation = new reservation(id, idUser, idEvent, idSalle, idTerrain, dateReservation);
            reservationDao.add(newReservation);
            ReservationResultArea.setText("Full Reservation successfully added:\n" + newReservation);
            ToastNotification.showToast(new Stage(), "Full Reservation Added Successfully!");
            
            // Clear fields
            if (ReservationIdEventComboBox.isEditable()) {
                ReservationIdEventComboBox.getEditor().clear();
            }
            if (ReservationIdSalleComboBox.isEditable()) {
                ReservationIdSalleComboBox.getEditor().clear();
            }
            if (ReservationIdTerrainComboBox.isEditable()) {
                ReservationIdTerrainComboBox.getEditor().clear();
            }
            if (ReservationIdUserComboBox.isEditable()) {
                ReservationIdUserComboBox.getEditor().clear();
            }
            
            ReservationDateField.setValue(null);
            
            // Update the calendar after adding a new reservation
            updateAvailableDates();
            
        } catch (Exception e) {
            ReservationResultArea.setText("Error: " + e.getMessage());
        }
    }


    // View All Reservations
    @FXML
    private void handleViewReservation() {
        List<reservation> reservations = reservationDao.getAll();
       reservationsList.clear();
        for (reservation reservation : reservations) {
        reservationsList.add(reservation);
        }
      
    }

    private void setupButtonBindings() {
        addUserButton.disableProperty().bind(
            idField.textProperty().isEmpty()
            .or(nomField.textProperty().isEmpty())
            .or(prenomField.textProperty().isEmpty())
            .or(emailField.textProperty().isEmpty())
        );
        
        addEventButton.disableProperty().bind(
            eventIdField.textProperty().isEmpty()
            .or(eventNameField.textProperty().isEmpty())
            .or(EventDateField.valueProperty().isNull())
        );
        
        addSalleButton.disableProperty().bind(
            salleIdField.textProperty().isEmpty()
            .or(salleNameField.textProperty().isEmpty())
            .or(salleCapaciteField.textProperty().isEmpty())
        );
        
        addTerrainButton.disableProperty().bind(
            TerrainIdField.textProperty().isEmpty()
            .or(TerrainNameField.textProperty().isEmpty())
            .or(TerrainTypeField.textProperty().isEmpty())
        );
        addReservationButton.disableProperty().bind(
            ReservationIdField.textProperty().isEmpty()
            .or(ReservationIdUserComboBox.valueProperty().isNull())
            .or(ReservationIdEventComboBox.valueProperty().isNull())
            .or(ReservationIdSalleComboBox.valueProperty().isNull())
            .or(ReservationIdTerrainComboBox.valueProperty().isNull())
            .or(ReservationDateField.valueProperty().isNull())
        );
        }
        private void updateDashboard() {

            // Retrieve data from DAOs and update the UI
            int userCount = userDao.getAll().size();
            int eventCount = eventDao.getAll().size();
            int salleCount = salleDao.getAll().size();
            int terrainCount = terrainDao.getAll().size();
            int reservationCount = reservationDao.getAll().size();
        
            // Update the labels with the current counts
            userNombre.setText("Users : "+ String.valueOf(userCount));
            eventNombre.setText( "Events: "+ String.valueOf(eventCount));
            salleNombre.setText( "Salles :"+String.valueOf(salleCount));
            terrainsNombre.setText( "Terrains: "+ String.valueOf(terrainCount));
            reservationNombre.setText("Reservations: " +String.valueOf(reservationCount));
        }
        private void updateChart() {
            // Get the count of each category
            int userCount = userDao.getAll().size();
            int eventCount = eventDao.getAll().size();
            int salleCount = salleDao.getAll().size();
            int terrainCount = terrainDao.getAll().size();
        
            // Create PieChart data slices
            PieChart.Data slice1 = new PieChart.Data("Users", userCount);
            PieChart.Data slice2 = new PieChart.Data("Events", eventCount);
            PieChart.Data slice3 = new PieChart.Data("Salles", salleCount);
            PieChart.Data slice4 = new PieChart.Data("Terrains", terrainCount);
        
            // Clear existing data and add new slices
            statisticsChart.getData().clear();
            statisticsChart.getData().addAll(slice1, slice2, slice3, slice4);
        }
  
        
    }
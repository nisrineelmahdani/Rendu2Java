package com.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import java.util.List;

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
    @FXML private TextArea resultArea;
    
    @FXML private TextField eventIdField;
    @FXML private TextField eventNameField;
    @FXML private TextField eventDateField;
    @FXML private TextField descriptionField;
    @FXML private TextField user_idField;
    @FXML private TextArea eventResultArea;
    
    @FXML private TextField salleIdField;
    @FXML private TextField salleNameField;
    @FXML private TextField salleCapaciteField;
    @FXML private TextArea salleResultArea;
    
    @FXML private TextField TerrainIdField;
    @FXML private TextField TerrainNameField;
    @FXML private TextField TerrainTypeField;
    @FXML private TextArea TerrainResultArea;
    
    @FXML private TextField ReservationIdField;
    @FXML private TextField ReservationIdUserField;
    @FXML private TextField ReservationIdEventField;
    @FXML private TextField ReservationIdSalleField;
    @FXML private TextField ReservationIdTerrainField;
    @FXML private TextField ReservationDateField;
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
    @FXML
private TableView<terrain> terrainTable;
@FXML
private TableColumn<terrain, String> nameColumn;
@FXML
private TableColumn<terrain, String> typeColumn;
@FXML
private TableView<event> eventTable;
@FXML
private TableColumn<event, String> eventnameColumn;
@FXML
private TableColumn<event, String> eventDateColumn;
@FXML
private TableColumn<event, String> DescriptionColumn;
@FXML
private TableColumn<event, String> user_idColumn;

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
// colonne tables 
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("nomTerrain"));
    typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    eventnameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
    eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    user_idColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
    // Bind the TableView to the ObservableList
    terrainTable.setItems(terrainList);
    eventTable.setItems(eventList);
        setupButtonBindings();
        updateDashboard();
        updateChart();
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
                resultArea.setText("Invalid user type. Please select 'ETUDIANT' or 'PROFESSEUR'.");
                return;
            }

            User newUser = new User(id, nom, prenom, email, type);
            userDao.add(newUser);
            resultArea.setText("User added: " + newUser);

            // Clear fields
            idField.clear();
            nomField.clear();
            prenomField.clear();
            emailField.clear();
            typeComboBox.getSelectionModel().clearSelection();
        } catch (Exception e) {
            resultArea.setText("Error: " + e.getMessage());
        }
    }

    // View All Users
    @FXML
    private void handleViewUsers() {
        List<User> users = userDao.getAll();
        StringBuilder result = new StringBuilder("List of Users:\n");
        for (User user : users) {
            result.append(user).append("\n");
        }
        resultArea.setText(result.toString());
    }

    // Add Event
    @FXML
    private void handleAddEvent() {
        try {
            int eventId = Integer.parseInt(eventIdField.getText());
            String eventName = eventNameField.getText();
            String eventDate = eventDateField.getText();
            String description = descriptionField.getText();
            int userId = Integer.parseInt(user_idField.getText());

            event newEvent = new event(eventId, eventName, eventDate, description, userId);
            eventDao.add(newEvent);
            eventResultArea.setText("Event added: " + newEvent);

            // Clear fields
            eventIdField.clear();
            eventNameField.clear();
            eventDateField.clear();
        } catch (Exception e) {
            eventResultArea.setText("Error: " + e.getMessage());
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
            salleResultArea.setText("Salle added: " + salle);

            // Clear fields
            salleIdField.clear();
            salleNameField.clear();
            salleCapaciteField.clear();
        } catch (Exception e) {
            salleResultArea.setText("Error: " + e.getMessage());
        }
    }

    // View All Salles
    @FXML
    private void handleViewSalle() {
        List<salle> salles = salleDao.getAll();
        StringBuilder result = new StringBuilder("List of Salles:\n");
        for (salle salle : salles) {
            result.append(salle).append("\n");
        }
        salleResultArea.setText(result.toString());
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
            TerrainResultArea.setText("Terrain added: " + terrain);

            // Clear fields
            TerrainIdField.clear();
            TerrainNameField.clear();
            TerrainTypeField.clear();
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
            int idUser = Integer.parseInt(ReservationIdUserField.getText());
            int idEvent = Integer.parseInt(ReservationIdEventField.getText());
            int idSalle = Integer.parseInt(ReservationIdSalleField.getText());
            int idTerrain = Integer.parseInt(ReservationIdTerrainField.getText());
            String dateReservation = ReservationDateField.getText();

            reservation newReservation = new reservation(id, idUser, idEvent, idSalle, idTerrain, dateReservation);
            reservationDao.add(newReservation);
            ReservationResultArea.setText("Reservation added: " + newReservation);

            // Clear fields
            ReservationIdField.clear();
            ReservationIdEventField.clear();
            ReservationIdSalleField.clear();
            ReservationIdTerrainField.clear();
            ReservationIdUserField.clear();
            ReservationDateField.clear();
        } catch (Exception e) {
            ReservationResultArea.setText("Error: " + e.getMessage());
        }
    }

    // View All Reservations
    @FXML
    private void handleViewReservation() {
        List<reservation> reservations = reservationDao.getAll();
        StringBuilder result = new StringBuilder("List of Reservations:\n");
        for (reservation reservation : reservations) {
            result.append(reservation).append("\n");
        }
        ReservationResultArea.setText(result.toString());
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
            .or(eventDateField.textProperty().isEmpty())
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
            .or(ReservationIdUserField.textProperty().isEmpty())
            .or(ReservationIdEventField.textProperty().isEmpty())
            .or(ReservationIdSalleField.textProperty().isEmpty())
            .or(ReservationIdTerrainField.textProperty().isEmpty())
            .or(ReservationDateField.textProperty().isEmpty())
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
            reservationNombre.setText("Reservations" +String.valueOf(reservationCount));
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
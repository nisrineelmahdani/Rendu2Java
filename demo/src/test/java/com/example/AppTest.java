package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.security.PrivateKey;
import java.sql.Connection;
import java.util.List;

public class AppTest {
    private db conn;
    private GenericDAO<event> eventDao;  
    private  GenericDAO<User> userDao;
    private GenericDAO<salle> salleDao;
    private GenericDAO<terrain> terrainDao;
    private GenericDAO<reservation> reservationDao;

    @BeforeEach
    public void setUp() {
        conn = db.getInstance();
        if (conn == null) {
            System.out.println("Database connection is null.");
        } else {
            System.out.println("Database connection established successfully.");
        }
        eventDao = new eventImpl(conn);
        userDao = new UserImpl(conn);
        salleDao = new salleDaoImpl(conn);
        terrainDao = new terrainDaoImpl(conn);
        reservationDao = new reservationDaoImpl(conn);
    }



    
    @Test
    public void testAddUser() {
      
      User user1User = new User(1, "wiam", "benaddi", "wiam@gmail.com", "ETUDIANT");
        userDao.add(user1User);
        User userRetirer = userDao.getById(1);
        assertNotNull(userRetirer, "User should be found by ID");
        assertEquals("wiam", userRetirer.getNom(), "User name should match");
    }

    @Test
    public void testGetAllusers() {
       
        
       

        List<User> users = userDao.getAll();
        assertEquals(1, users.size(), "There should be 1 users in the list");
    }
    @Test
    public void testAddEvent() {
      
        event newEvent = new event(1, "event1", "2024-12-13", "test", 1);

      
        eventDao.add(newEvent);
        event eventRetire = eventDao.getById(1);
        assertNotNull(eventRetire, "Event should be found by ID");
        assertEquals("event1", eventRetire.getNom(), "Event name should match");
    }

    @Test
    public void testGetAllEvents() {
       
        event event1 = new event(2, "test2", "2024-12-13", "test", 1);
        event event2 = new event(3, "test3", "2024-12-13", "test", 1);

     
        eventDao.add(event1);
        eventDao.add(event2);

        List<event> events = eventDao.getAll();
        assertEquals(3, events.size(), "There should be 3 events in the list");
    }

    @Test
    public void testAddsalle() {
      
      salle salle1 = new salle(1, "tp3", 30);
        salleDao.add(salle1);
      
      

        
        salle salleRetirer = salleDao.getById(1);
        assertNotNull(salleRetirer, "salle should be found by ID");
        assertEquals("tp3", salleRetirer.getNom_salle(), "salle name should match");
    }

    @Test
    public void testGetAllsalles() {

        List<salle> salles = salleDao.getAll();
        assertEquals(1, salles.size(), "There should be 1 salles in the list");
    }
    @Test
    public void testAddterrain() {
      
      terrain terrain1 = new terrain(0, "baskettball", "FOOTBALL");
        terrainDao.add(terrain1);
      
        int insertedTerrainId = terrain1.getIdTerrain();
        terrain terrainRetirer = terrainDao.getById(insertedTerrainId);
        assertNotNull(terrainRetirer, "terrain should be found by ID");
        assertEquals("baskettball", terrainRetirer.getNomTerrain(), "terrain name should match");
    }

    @Test
    public void testGetAllterrains() {
       
        
       

        List<terrain> terrains = terrainDao.getAll();
        assertEquals(1, terrains.size(), "There should be 1 terrains in the list");
    }
 @Test
    public void testAddreservation() {
      
      reservation reservation1 = new reservation(0, 1, 1, 1, 1,"2024-12-19");
        reservationDao.add(reservation1);
        int insertedReservationId = reservation1.getId();
        reservation reservationRetirer = reservationDao.getById(insertedReservationId);
        assertNotNull(reservationRetirer, "reservation should be found by ID");
        assertEquals(1, reservationRetirer.getId(), "reservation id should match");
    }

    @Test
    public void testGetAllreservations() {
        List<reservation> reservations = reservationDao.getAll();
        assertEquals(1, reservations.size(), "There should be 1 reservations in the list");
    }
    
}

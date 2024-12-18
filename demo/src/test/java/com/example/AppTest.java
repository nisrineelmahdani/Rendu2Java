package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

public class AppTest {
    private db conn;
    private GenericDAO<event> eventDao;  

    @BeforeEach
    public void setUp() {
        conn = db.getInstance();
        if (conn == null) {
            System.out.println("Database connection is null.");
        } else {
            System.out.println("Database connection established successfully.");
        }
        eventDao = new eventImpl(conn);
    }

    @Test
    public void testAddEvent() {
      
        event newEvent = new event(3, "test3", "2024-12-13", "testt", 1);

      
        eventDao.add(newEvent);

        
        event retrievedEvent = eventDao.getById(3);
        assertNotNull(retrievedEvent, "Event should be found by ID");
        assertEquals("test3", retrievedEvent.getNom(), "Event name should match");
    }

    @Test
    public void testGetAllEvents() {
       
        event event1 = new event(4, "Conference", "2024-12-13", "testt", 1);
        event event2 = new event(5, "Seminar", "2024-12-13", "testt", 1);

     
        eventDao.add(event1);
        eventDao.add(event2);

        List<event> events = eventDao.getAll();
        assertEquals(5, events.size(), "There should be 5 events in the list");
    }
}

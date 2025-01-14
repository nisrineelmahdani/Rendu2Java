package com.example;

import java.time.LocalDate;

public class event {
    
    private int id;
    private String nom;
    private LocalDate date;
    private String description;
    private int userId; 

  
    public event(int id, String nom, LocalDate date, String description, int userId) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.description = description;
        this.userId = userId;
    }

 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

  
    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                '}';
    }
}

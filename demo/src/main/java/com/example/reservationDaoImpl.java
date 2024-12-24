package com.example;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class reservationDaoImpl implements  GenericDAO<reservation> {
private Connection conn;
public reservationDaoImpl(db cnn){
    this.conn= cnn.getConnection();
}
    @Override
    public void add(reservation reservation) {
       try {
        String sql ="INSERT INTO reservations (id_reservation, id_user,id_event,id_salle,id_terrain, date_reservation) VALUES(?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, reservation.getId());
        pstmt.setInt(2, reservation.getId_user());
        pstmt.setInt(3, reservation.getId_event());
        pstmt.setInt(4, reservation.getId_salle());
        pstmt.setInt(5, reservation.getId_terrain());
        LocalDate dateReservation = reservation.getDate_reservation();
String formattedDate = dateReservation.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
pstmt.setString(6, formattedDate);
        //pstmt.setString(6, reservation.getDate_reservation());
        pstmt.executeUpdate();
        System.out.println("reservation added successfully!");

       } catch (Exception e) {
       e.printStackTrace();
       }
    }

    @Override
    public reservation getById(int id) {
       reservation reservation= null;
        try {
            String sql = "select * from reservations where id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
           reservation = new reservation(rs.getInt("id_reservation"), rs.getInt("id_user"), rs.getInt("id_event"), rs.getInt("id_salle"), rs.getInt("id_terrain"), rs.getObject("date_reservation", LocalDate.class));

         }
       
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservation;
    }
    @Override
    public List<reservation> getAll() {
       List<reservation> reservations = new ArrayList<>();
        try {
            String sql = "SELECT * FROM reservations";
          Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
               reservations .add (new reservation(
                        rs.getInt("id_reservation"),
                        rs.getInt("id_user"),
                        rs.getInt("id_event"),
                        rs.getInt("id_salle"),
                        rs.getInt("id_terrain"),
                        rs.getObject("date_reservation", LocalDate.class)
                       
                ));
              
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public void update(reservation reservation) {
        try {
            String sql = "UPDATE reservations SET id_user = ?, id_event = ? , id_salle = ? , id_terrain = ? , date_reservation = ? WHERE id_reservation = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reservation.getId_user());
            pstmt.setInt(2, reservation.getId_event());
            pstmt.setInt(3, reservation.getId_salle());
            pstmt.setInt(4, reservation.getId_terrain());
            LocalDate dateReservation = reservation.getDate_reservation();
            String formattedDate = dateReservation.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            pstmt.setString(6, formattedDate);
            pstmt.setInt(6, reservation.getId());
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Reservation updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM reservations WHERE id_reservation = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("reservation deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}

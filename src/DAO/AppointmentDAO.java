/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Appointment;
import Model.Data;
import Utilities.DBConnection;
import Utilities.DBQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author Eric
 */
public class AppointmentDAO {
    
    /**
     * Select statement for all rows in the appointments table. 
     * 
     */
    public static void selectAppointments() throws SQLException {
        try {
            Data.clearAppointments();
            Connection conn = DBConnection.getConnection();
            String sqlStatement = "SELECT * "
                                + "FROM appointments;";
            DBQuery.setPreparedStatement(conn, sqlStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()) {
                int appointmentID = resultSet.getInt("Appointment_ID");
                String title = resultSet.getString("Title");
                String description = resultSet.getString("Description");
                String location = resultSet.getString("Location");
                Timestamp startTimestamp = resultSet.getTimestamp("Start");
                LocalDateTime start = startTimestamp.toLocalDateTime();
                Timestamp endTimestamp = resultSet.getTimestamp("End");
                LocalDateTime end = endTimestamp.toLocalDateTime();
                String teacherName = resultSet.getString("Teacher");
                int teacherId = resultSet.getInt("Teacher_ID");
                String studentName = resultSet.getString("Student");
                int studentId = resultSet.getInt("Student_ID");                

                Appointment appointment = new Appointment(appointmentID, title, description, 
                            location, start, end, teacherName, teacherId, studentName, studentId);

                Data.addAppointment(appointment);
            }
        }
        catch(SQLException exception) {
                exception.printStackTrace();
        }  
    }
    
    /**
     * Delete statement for a row in the appointments table. 
     * @param appointmentID Appointment ID to be deleted
     */
    public static void deleteAppointment(int appointmentID) throws SQLException {
        try {
            Connection conn = DBConnection.getConnection();
  
            String sqlStatement = "DELETE FROM appointments WHERE Appointment_ID = ?;";
            DBQuery.setPreparedStatement(conn, sqlStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            preparedStatement.setInt(1, appointmentID);
            preparedStatement.execute();
            
        }
        catch(SQLException exception) {
            exception.printStackTrace();
        }
    }
}

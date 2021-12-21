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
public abstract class AppointmentDAO {
    
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
     * Insert statement for adding a row to the appointments table. 
     * @param title Title data for the new appointment
     * @param description Description data for the new appointment
     * @param location Location data for the new appointment
     * @param start Start timestamp for the new appointment
     * @param end End timestamp for the new appointment
     * @param teacherName Associated teacher name for the new appointment
     * @param teacherID Associated teacher ID for the new appointment
     * @param studentName Associated student name for the new appointment
     * @param studentID Associated student ID for the new appointment
     */
    public static void insertAppointment(
            String title, 
            String description, 
            String location,  
            LocalDateTime start, 
            LocalDateTime end, 
            String teacherName, 
            int teacherID, 
            String studentName, 
            int studentID) {
            try {
                Connection conn = DBConnection.getConnection();
                String sqlStatement = "INSERT INTO appointments(Title, Description, Location, Start, End, Teacher, Teacher_ID, Student, Student_ID ) " 
                                    +"Values(?, ?, ?, ?, ?, ?, ?, ?, ?);";
                DBQuery.setPreparedStatement(conn, sqlStatement);
                PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
                preparedStatement.setString(1, title);
                preparedStatement.setString(2, description);
                preparedStatement.setString(3, location);
                preparedStatement.setTimestamp(4, Timestamp.valueOf(start));
                preparedStatement.setTimestamp(5, Timestamp.valueOf(end));
                preparedStatement.setString(6, teacherName);
                preparedStatement.setInt(7, teacherID);
                preparedStatement.setString(8, studentName);
                preparedStatement.setInt(9, studentID);
                preparedStatement.execute();
                
            }
            catch(SQLException exception) {
                exception.printStackTrace();
            }
            
    }
    
    /**
     * Set statement for updating a row in the appointments table. 
     * @param id Appointment ID to be updated
     * @param title Updated title data for the row
     * @param description Updated description data for the row
     * @param location Updated location data for the row
     * @param start Updated start timestamp for the row
     * @param end Updated end timestamp for the row
     * @param teacherName Updated associated teacher name for the row
     * @param teacherID Updated associated teacher ID for the row
     * @param studentName Updated associated student name for the row
     * @param studentID Updated associated student ID for the row
     */
    public static void updateAppointment(
            int id,
            String title, 
            String description, 
            String location,  
            LocalDateTime start, 
            LocalDateTime end, 
            String teacherName, 
            int teacherID, 
            String studentName, 
            int studentID) {
        try {
            Connection conn = DBConnection.getConnection();
            String sqlStatement = "UPDATE appointments "
                                + "SET Title = ?, Description = ?, Location = ?, "
                                + "Start = ?, End = ?, Teacher = ?, Teacher_ID = ?, Student = ?, Student_ID = ? "
                                + "Where Appointment_ID = ?;";
            DBQuery.setPreparedStatement(conn, sqlStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, location);
            preparedStatement.setTimestamp(4, Timestamp.valueOf(start));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(end));
            preparedStatement.setString(6, teacherName);
            preparedStatement.setInt(7, teacherID);
            preparedStatement.setString(8, studentName);
            preparedStatement.setInt(9, studentID);
            preparedStatement.setInt(10, id);
            preparedStatement.execute();
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
    
    /**
     * Select statement for all rows in the appointments table with a specified type, during a specific month. 
     * @param location Location of appointments to be queried
     * @param start Start timestamp for designated month
     * @param end End timestamp for designated month
     */
    public static int getTotalsReport(String location, LocalDateTime start, LocalDateTime end) throws SQLException {
        Connection conn = DBConnection.getConnection();
        int count = 0;
        String sqlStatement = "SELECT COUNT(*) FROM appointments "
                            + "WHERE location = ? AND "
                            + "Start BETWEEN ? and ?;";
        DBQuery.setPreparedStatement(conn, sqlStatement);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
        preparedStatement.setString(1, location);
        preparedStatement.setTimestamp(2, Timestamp.valueOf(start));
        preparedStatement.setTimestamp(3, Timestamp.valueOf(end));
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        while(resultSet.next()) {
            count = resultSet.getInt("COUNT(*)");
        }
        return count;
    }
}

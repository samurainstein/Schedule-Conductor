/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Data;
import Model.InstrumentStudent;
import Model.InstrumentTeacher;
import Utilities.DBConnection;
import Utilities.DBQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Eric
 */
public abstract class InstrumentStudentDAO {
    
    /**
     * Select statement for all rows in the instrument_student table. 
     * 
     */
    public static void selectStudents() {
        try {
            Data.clearStudents();
            Connection conn = DBConnection.getConnection();
            String sqlStatement = "SELECT * FROM instrument_student;";
            DBQuery.setPreparedStatement(conn, sqlStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                String division = resultSet.getString("division");
                String postalCode = resultSet.getString("postal_code");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String instrument = resultSet.getString("instrument");
                char availableOnline = resultSet.getString("available_online").charAt(0);
                char availableInPerson = resultSet.getString("available_online").charAt(0);
                
                InstrumentStudent student = new InstrumentStudent(
                        id, 
                        name, 
                        country, 
                        division, 
                        postalCode, 
                        address, 
                        phone,  
                        instrument, 
                        availableOnline, 
                        availableInPerson);
                Data.addStudent(student);
            }
        }
        catch(SQLException exception) {
            exception.printStackTrace();
        }
    }
    
    /**
     * Delete statement for a row in the instrument_students table. 
     * @param studentId Student ID to be deleted
     */
    public static void deleteStudent(int studentId) {
        try {
            Connection conn = DBConnection.getConnection();
            String sqlStatement = "DELETE FROM instrument_student "
                                + "WHERE id = ?;";
            DBQuery.setPreparedStatement(conn, sqlStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            preparedStatement.setInt(1,studentId);
            preparedStatement.execute();
        }
        catch(SQLException exception) {
            exception.printStackTrace();
        }
    }
    
    /**
     * Insert statement for adding a row to the instrument_student table. 
     * @param name Name for the new student
     * @param country Country name for the new student
     * @param division Division name for the new student
     * @param postal Postal code for the new student
     * @param address Address for the new student
     * @param phone Phone number for the new student
     * @param instrument Instrument for the new student
     * @param availableOnline Online availability for the new student
     * @param availableInPerson In person availability for the new student
     */
    public static void insertStudent(
            String name,
            String country,
            String division,
            String postal,
            String address,
            String phone,
            String instrument,
            char availableOnline,
            char availableInPerson) {
        try {
            Connection conn = DBConnection.getConnection();
            String sqlStatement = "INSERT INTO instrument_student(name, country, division, postal_code, address, phone, "
                                       + "instrument, available_online, available_in_person) "
                                        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
            DBQuery.setPreparedStatement(conn, sqlStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, country);
            preparedStatement.setString(3, division);
            preparedStatement.setString(4, postal);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, phone);
            preparedStatement.setString(7, instrument);
            String online = String.valueOf(availableOnline);
            preparedStatement.setString(8, online);
            String inPerson = String.valueOf(availableInPerson);
            preparedStatement.setString(9, inPerson);
            
            preparedStatement.execute();
//            Data.clearCustomers();
        }
        catch(SQLException exception) {
            exception.printStackTrace();
        }
    }
    
    /**
     * Set statement for updating a row in the instrumen_student table. 
     * @param id student ID to be updated
     * @param name Updated name data for the student
     * @param country Updated country data for the student
     * @param division Updated Division for the student
     * @param postal Updated postal Code data for the student
     * @param address Updated address data for the student
     * @param phone Updated phone data for the student
     * @param instrument Updated instrument data for the student
     * @param availableOnline Updated online availability data for the student
     * @param availableInPerson Updated in person availability data for the student
     */
    public static void updateStudent(
            int id, 
            String name,
            String country,
            String division,
            String postal,
            String address,
            String phone,
            String instrument,
            char availableOnline,
            char availableInPerson)  {
        try {
            Connection conn = DBConnection.getConnection();
            String sqlStatement = "UPDATE instrument_student "
                                + "SET name = ?, country = ?, division = ?, postal_code = ?, address = ?, phone = ?, "
                                + "instrument = ?, available_online = ?, available_in_person = ? "
                                + "WHERE id = ?;";
            DBQuery.setPreparedStatement(conn, sqlStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, country);
            preparedStatement.setString(3, division);
            preparedStatement.setString(4, postal);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, phone);
            preparedStatement.setString(7, instrument);
            preparedStatement.setString(8, String.valueOf(availableOnline));
            preparedStatement.setString(9, String.valueOf(availableInPerson));
            preparedStatement.setInt(10, id);
            preparedStatement.execute();
        }
        catch(SQLException exception) {
            exception.printStackTrace();
        }
    }
    
}

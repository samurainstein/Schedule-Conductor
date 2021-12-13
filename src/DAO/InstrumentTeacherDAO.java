/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.InstrumentTeacher;
import Model.Data;
import Utilities.DBConnection;
import Utilities.DBQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used to send queries to the instrument_teacher table in the database
 * @author Eric Matelyan
 */
public abstract class InstrumentTeacherDAO {
    
    /**
     * Checks if username/password pair exists in the users table. 
     * @param username Username to be checked
     * @param password Password to be checked
     * @return Returns the User ID if found.  Otherwise, returns 0. 
     */
    public static int teacherLogin(String username, String password) {
        try {           
            Connection conn = DBConnection.getConnection();
            String sqlStatement = "SELECT * FROM instrument_teacher "
                                + "WHERE username = ? "
                                + "AND password = ?";
            DBQuery.setPreparedStatement(conn, sqlStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                Data.setLoggedInTeacherID(id);
                return id;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    /**
     * Select statement for all rows in the instrument_teacher table. 
     * 
     */
    public static void selectTeachers() {
        try {
            Data.clearTeachers();
            Connection conn = DBConnection.getConnection();
            String sqlStatement = "SELECT id, name, country, division, postal_code, address, phone, "
                    + "instrument, available_online, available_in_person, username, password " +
                    "FROM instrument_teacher;";
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
                char availableInPerson = resultSet.getString("available_in_person").charAt(0);
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                
                InstrumentTeacher teacher = new InstrumentTeacher(
                        id, 
                        name, 
                        country, 
                        division, 
                        postalCode, 
                        address, 
                        phone, 
                        username, 
                        password, 
                        instrument, 
                        availableOnline, 
                        availableInPerson);
                Data.addTeacher(teacher);
            }
        }
        catch(SQLException exception) {
            exception.printStackTrace();
        }
    }
    
    /**
     * Insert statement for adding a row to the instrument_teacher table. 
     * @param name Name for the new teacher
     * @param country Country name for the new teacher
     * @param division Division name for the new teacher
     * @param postal Postal code for the new teacher
     * @param address Address for the new teacher
     * @param phone Phone number for the new teacher
     * @param instrument Instrument for the new teacher
     * @param availableOnline Online availability for the new teacher
     * @param availableInPerson In person availability for the new teacher
     * @param username Username for the new teacher
     * @param password Password for the new teacher
     */
    public static void insertTeacher(
            String name,
            String country,
            String division,
            String postal,
            String address,
            String phone,
            String instrument,
            char availableOnline,
            char availableInPerson,
            String username,
            String password) {
        try {
            Connection conn = DBConnection.getConnection();
            String sqlStatement = "INSERT INTO instrument_teacher(name, country, division, postal_code, address, phone, "
                                       + "instrument, available_online, available_in_person, username, password) "
                                        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
            preparedStatement.setString(10, username);
            preparedStatement.setString(11, password);
            
            preparedStatement.execute();
//            Data.clearCustomers();
        }
        catch(SQLException exception) {
            exception.printStackTrace();
        }
    }
    
//    /**
//     * Set statement for updating a row in the customers table. 
//     * @param name Updated name data for the new customer
//     * @param address Updated address data for the new customer
//     * @param postalCode Updated postal Code data for the new customer
//     * @param phone Updated phone data for the new customer
//     * @param divisionID Updated Division ID for the new customer
//     * @param customerID Customer ID to be updated
//     */
//    public static void updateCustomer(String name, String address, String postalCode, String phone, int divisionID, int customerID) {
//        try {
//            Connection conn = DBConnection.getConnection();
//            String sqlStatement = "UPDATE customers "
//                                + "SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? "
//                                + "WHERE Customer_ID = ?;";
//            DBQuery.setPreparedStatement(conn, sqlStatement);
//            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, address);
//            preparedStatement.setString(3, postalCode);
//            preparedStatement.setString(4, phone);
//            preparedStatement.setInt(5, divisionID);
//            preparedStatement.setInt(6, customerID);
//            preparedStatement.execute();
//        }
//        catch(SQLException exception) {
//            exception.printStackTrace();
//        }
//    }
//    
    /**
     * Delete statement for a row in the instrument_teachers table. 
     * @param teacherId Teacher ID to be deleted
     */
    public static void deleteTeacher(int teacherId) {
        try {
            Connection conn = DBConnection.getConnection();
            String sqlStatement = "DELETE FROM instrument_teacher "
                                + "WHERE id = ?;";
            DBQuery.setPreparedStatement(conn, sqlStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            preparedStatement.setInt(1,teacherId);
            preparedStatement.execute();
        }
        catch(SQLException exception) {
            exception.printStackTrace();
        }
    }
//    
//    /**
//     * Select statement for all rows in the customers table with a specific associated Country ID. 
//     * @param reportCountryID Country ID to be searched for
//     */
//    public static void selectCustomersByCountry(int reportCountryID) {
//        try {
//            Data.clearCustomersByCountry();
//            Connection conn = DBConnection.getConnection();
//            String sqlStatement = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division, Country "
//                                + "FROM customers, first_level_divisions, countries "
//                                + "WHERE customers.Division_ID = first_level_divisions.Division_ID "
//                                + "AND first_level_divisions.Country_ID = countries.Country_ID "
//                                + "AND countries.Country_ID = ?;";
//            DBQuery.setPreparedStatement(conn, sqlStatement);
//            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
//            preparedStatement.setInt(1, reportCountryID);
//            preparedStatement.execute();
//            ResultSet resultSet = preparedStatement.getResultSet();
//            while(resultSet.next()) {
//                int customerID = resultSet.getInt("Customer_ID");
//                String customerName = resultSet.getString("Customer_Name");
//                String address = resultSet.getString("Address");
//                String postalCode = resultSet.getString("Postal_Code");
//                String phone = resultSet.getString("Phone");
//                String division = resultSet.getString("Division");
//                String country = resultSet.getString("Country");
//                
//                Customer customer = new Customer(customerID, customerName, address, postalCode, phone, division, country);
//                Data.addCustomerByCountry(customer);
//            }
//        }
//        catch(SQLException exception) {
//            exception.printStackTrace();
//        }
//    }
}

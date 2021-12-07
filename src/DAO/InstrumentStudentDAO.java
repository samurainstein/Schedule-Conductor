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
public class InstrumentStudentDAO {
    
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
    
}

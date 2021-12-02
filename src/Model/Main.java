/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Utilities.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Eric
 */
public class Main extends Application {
    
     /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DBConnection.startConnection();
        Connection conn = DBConnection.getConnection();

        //Locale.setDefault(new Locale("fr"));  
        //TimeZone.setDefault(TimeZone.getTimeZone("Japan"));
        
        launch(args);
        DBConnection.closeConnection();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("View/ScheduleConductor.css");

        
        stage.setScene(scene);
        stage.show();
    }    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AppointmentDAO;
import DAO.InstrumentTeacherDAO;
import Model.Appointment;
import Model.Data;
import Utilities.Alerts;
import Utilities.PageLoader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class HomeController implements Initializable {

    private String pageTitle;
    private Parent root;
    private Stage stage;
    private int loggedInTeacherId;
    private ObservableList<Appointment> teacherAppointments = FXCollections.observableArrayList();
    @FXML
    private Label logoutLabel;
    @FXML
    private Label notificationLBL;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        logoutLabel = new Label();
//        logoutLabel.setOnMouseClicked(logoutEventHandler); 
        loggedInTeacherId = Data.getLoggedInTeacherId();
        try {
            AppointmentDAO.selectAppointments();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        teacherAppointments = Data.getTeacherAppointments(loggedInTeacherId);
        if(teacherAppointments.size() > 0) {
            Appointment appointment = teacherAppointments.get(0);
            notificationLBL.setText(appointment.getTitle());
        }
        else {
            notificationLBL.setText("There are no upcoming appointments");
        }
    }    
    
    //This is not working for some reason.
//    EventHandler<MouseEvent> logoutEventHandler = new EventHandler<MouseEvent>() {
//        public void handle(MouseEvent event) {
//            System.out.println("Logout clicked");
//            pageTitle = PageLoader.getLoginTitle();
//            try {
//                root = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//            stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
//            PageLoader.pageLoad(stage, root, pageTitle);
//        }             
//    };

    @FXML
    private void logout(MouseEvent event) {
        System.out.println("Logout clicked");
        pageTitle = PageLoader.getLoginTitle();
        try {
            root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        PageLoader.pageLoad(stage, root, pageTitle);
    }
}

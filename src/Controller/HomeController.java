/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AppointmentDAO;
import Model.Appointment;
import Model.Data;
import Utilities.EventHandle;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
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
    @FXML
    private Label studentLBL;
    @FXML
    private Label titleLBL;
    @FXML
    private Label locationLBL;
    @FXML
    private Label studentLBLtxt;
    @FXML
    private Label titleLBLtxt;
    @FXML
    private Label locationLBLtxt;
    @FXML
    private Label dateLBL;
    @FXML
    private Label timeLBL;
    @FXML
    private Label dateLBLtxt;
    @FXML
    private Label timeLBLtxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logoutLabel.setOnMouseClicked(EventHandle.navEventLogout()); 
        loggedInTeacherId = Data.getLoggedInTeacherId();
        Data.clearAppointments();
        try {
            AppointmentDAO.selectAppointments();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Data.clearTeacherAppointments();
        teacherAppointments = Data.getTeacherAppointments(loggedInTeacherId);
        if(teacherAppointments.size() > 0) {
            notificationLBL.setText("Your next appointment");
            studentLBL.setVisible(true);
            titleLBL.setVisible(true);
            locationLBL.setVisible(true);
            studentLBLtxt.setVisible(true);
            titleLBLtxt.setVisible(true);
            locationLBLtxt.setVisible(true);
            dateLBL.setVisible(true);
            timeLBL.setVisible(true);
            dateLBLtxt.setVisible(true);
            timeLBLtxt.setVisible(true);
            
            Appointment appointment = teacherAppointments.get(0);
            int studentId = appointment.getStudentId();
            String studentName = Data.getStudentName(studentId);
            studentLBLtxt.setText(studentName);
            titleLBLtxt.setText(appointment.getTitle());
            locationLBLtxt.setText(appointment.getLocation());
            LocalDateTime startDateTime = appointment.getStart();
            LocalDate startDate = startDateTime.toLocalDate();
            LocalTime startTime = startDateTime.toLocalTime();
            dateLBLtxt.setText(startDate.toString());
            timeLBLtxt.setText(startTime.toString());
            
        }
        else {
            notificationLBL.setText("There are no upcoming appointments");
            studentLBL.setVisible(false);
            titleLBL.setVisible(false);
            locationLBL.setVisible(false);
            studentLBLtxt.setVisible(false);
            titleLBLtxt.setVisible(false);
            locationLBLtxt.setVisible(false);
            dateLBL.setVisible(false);
            timeLBL.setVisible(false);
            dateLBLtxt.setVisible(false);
            timeLBLtxt.setVisible(false);
        }
    }    
    
//    EventHandler<MouseEvent> logoutEventHandler = new EventHandler<MouseEvent>() {
//        public void handle(MouseEvent event) {
//            try {
//                root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//            stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
//            PageLoader.pageLoad(stage, root, pageTitle);
//        }
//    };
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.InstrumentStudentDAO;
import DAO.InstrumentTeacherDAO;
import Model.Data;
import Model.InstrumentStudent;
import Model.InstrumentTeacher;
import Utilities.DateAndTime;
import Utilities.EventHandle;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class AddAppointmentController implements Initializable {

    @FXML
    private Label homeLBL;
    @FXML
    private Label teachersLBL;
    @FXML
    private Label teacherAddLBL;
    @FXML
    private Label studentsLBL;
    @FXML
    private Label studentAddLBL;
    @FXML
    private Label appointmentsLBL;
    @FXML
    private Label appointmentAddLBL;
    @FXML
    private Label reportsLBL;
    @FXML
    private Label logoutLabel;
    @FXML
    private Button saveBTN;
    @FXML
    private Button clearBTN;
    @FXML
    private Button cancelBTN;
    @FXML
    private TextField titleTF;
    @FXML
    private TextArea descriptionTA;
    @FXML
    private ComboBox<String> locationCB;
    @FXML
    private DatePicker dateDP;
    @FXML
    private ComboBox<LocalTime> timeCB;
    @FXML
    private ComboBox<String> lengthCB;
    @FXML
    private ComboBox<InstrumentTeacher> teacherCB;
    @FXML
    private ComboBox<InstrumentStudent> studentCB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homeLBL.setOnMouseClicked(EventHandle.navHomeEvent());
        teachersLBL.setOnMouseClicked(EventHandle.navTeachersEvent());
        teacherAddLBL.setOnMouseClicked(EventHandle.navTeacherAddEvent());
        studentsLBL.setOnMouseClicked(EventHandle.navStudentsEvent());
        studentAddLBL.setOnMouseClicked(EventHandle.navStudentAddEvent());
        appointmentsLBL.setOnMouseClicked(EventHandle.navAppointmentsEvent());
        appointmentAddLBL.setOnMouseClicked(EventHandle.navAppointmentAddEvent());
        reportsLBL.setOnMouseClicked(EventHandle.navReportsEvent());
        logoutLabel.setOnMouseClicked(EventHandle.navLogoutEvent());
        
        cancelBTN.setOnAction(EventHandle.appointmentCancelBTN());
        try {
            clearBTN.setOnAction(EventHandle.appointmentAddClearBTN(
                    titleTF,
                    descriptionTA,
                    locationCB,
                    dateDP,
                    timeCB,
                    lengthCB,
                    teacherCB,
                    studentCB));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            saveBTN.setOnAction(EventHandle.appointmentAddSaveBTN(
                    titleTF,
                    descriptionTA,
                    locationCB,
                    dateDP,
                    timeCB,
                    lengthCB,
                    teacherCB,
                    studentCB));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        locationCB.setItems(Data.getLocations());
        locationCB.setPromptText("Select a location");
        dateDP.setPromptText("Select a date");
        
        DateAndTime.setAppointmentTimes();
        DateAndTime.convertAppointmentTimes();
        timeCB.setItems(DateAndTime.getConvertedStartTimes());  
        timeCB.setPromptText("Select a start time");
        
        lengthCB.setItems(Data.getLengths());
        lengthCB.setPromptText("Select a length");
        InstrumentTeacherDAO.selectTeachers();
        InstrumentStudentDAO.selectStudents();
        teacherCB.setItems(Data.getAllTeachers());
        teacherCB.setPromptText("Select a Teacer");
        studentCB.setItems(Data.getAllStudents());
        studentCB.setPromptText("Select a Student");
    }    
    
}

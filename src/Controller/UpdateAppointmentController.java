/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CountryDAO;
import DAO.DivisionDAO;
import DAO.InstrumentStudentDAO;
import DAO.InstrumentTeacherDAO;
import Model.Appointment;
import Model.Country;
import Model.Data;
import Model.Division;
import Model.InstrumentStudent;
import Model.InstrumentTeacher;
import Utilities.DateAndTime;
import Utilities.EventHandle;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class UpdateAppointmentController implements Initializable {

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
    private TextField idTF;
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
    @FXML
    private Button saveBTN;
    @FXML
    private Button clearBTN;
    @FXML
    private Button cancelBTN;

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
        logoutLabel.setOnMouseClicked(EventHandle.navLogoutEvent());
        
        locationCB.setItems(Data.getLocations());
        DateAndTime.setAppointmentTimes();
        DateAndTime.convertAppointmentTimes();
        timeCB.setItems(DateAndTime.getConvertedStartTimes());  
        lengthCB.setItems(Data.getLengths());
        InstrumentTeacherDAO.selectTeachers();
        InstrumentStudentDAO.selectStudents();
        teacherCB.setItems(Data.getAllTeachers());
        studentCB.setItems(Data.getAllStudents());
        
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
        saveBTN.setOnAction(EventHandle.appointmentUpdateSaveBTN(
                idTF, 
                titleTF,
                descriptionTA,
                locationCB,
                dateDP,
                timeCB,
                lengthCB,
                teacherCB,
                studentCB
        ));
    }    
    
    public void passAppointmentData(Appointment appointment) {
        idTF.setText(Integer.toString(appointment.getAppointmentID()));
        titleTF.setText(appointment.getTitle());
        descriptionTA.setText(appointment.getDescription());
        locationCB.setItems(Data.getLocations());
        locationCB.setValue(appointment.getLocation());
        LocalDateTime startDateTime = appointment.getStart();
        LocalDateTime endDateTime = appointment.getEnd();
        LocalDate date = startDateTime.toLocalDate();
        dateDP.setValue(date);
        LocalTime startTime = startDateTime.toLocalTime();
        DateAndTime.setAppointmentTimes();
        DateAndTime.convertAppointmentTimes();
        timeCB.setItems(DateAndTime.getConvertedStartTimes());
        timeCB.setValue(startTime);
        lengthCB.setItems(Data.getLengths());
        LocalTime endTime = endDateTime.toLocalTime();
        if (endTime.isAfter(startTime)) 
        {
            startTime = startTime.plusMinutes(30);
            lengthCB.setValue("30 Minutes");
            if (endTime.isAfter(startTime))
            {
                startTime = startTime.plusMinutes(30);
                lengthCB.setValue("1 Hour");
                if (endTime.isAfter(startTime))
                {
                    lengthCB.setValue("1 Hour 30 Minutes");
                    startTime = startTime.plusMinutes(30);
                    if (endTime.isAfter(startTime))
                    {
                        lengthCB.setValue("2 Hours");
                        startTime = startTime.plusMinutes(30);
                    }
                }
            }
        }
          
        InstrumentTeacherDAO.selectTeachers();
        InstrumentStudentDAO.selectStudents();
        teacherCB.setItems(Data.getAllTeachers());
        studentCB.setItems(Data.getAllStudents());
        teacherCB.setValue(Data.getTeacherObject(appointment.getTeacherId()));
        studentCB.setValue(Data.getStudentObject(appointment.getStudentId()));
    }    
    
}

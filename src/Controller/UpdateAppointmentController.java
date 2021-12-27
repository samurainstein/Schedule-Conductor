/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AppointmentDAO;
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
import Utilities.Alerts;
import Utilities.DateAndTime;
import Utilities.EventHandlerNavMenu;
import Utilities.PageLoader;
import com.mysql.cj.util.StringUtils;
import java.io.IOException;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    
    private Parent root;
    private String pageTitle;
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homeLBL.setOnMouseClicked(EventHandlerNavMenu.navHomeEvent());
        teachersLBL.setOnMouseClicked(EventHandlerNavMenu.navTeachersEvent());
        teacherAddLBL.setOnMouseClicked(EventHandlerNavMenu.navTeacherAddEvent());
        studentsLBL.setOnMouseClicked(EventHandlerNavMenu.navStudentsEvent());
        studentAddLBL.setOnMouseClicked(EventHandlerNavMenu.navStudentAddEvent());
        appointmentsLBL.setOnMouseClicked(EventHandlerNavMenu.navAppointmentsEvent());
        appointmentAddLBL.setOnMouseClicked(EventHandlerNavMenu.navAppointmentAddEvent());
        reportsLBL.setOnMouseClicked(EventHandlerNavMenu.navReportsEvent());
        logoutLabel.setOnMouseClicked(EventHandlerNavMenu.navLogoutEvent());
        
        locationCB.setItems(Data.getLocations());
        DateAndTime.setAppointmentTimes();
        DateAndTime.convertAppointmentTimes();
        timeCB.setItems(DateAndTime.getConvertedStartTimes());  
        lengthCB.setItems(Data.getLengths());
        InstrumentTeacherDAO.selectTeachers();
        InstrumentStudentDAO.selectStudents();
        teacherCB.setItems(Data.getAllTeachers());
        studentCB.setItems(Data.getAllStudents());
        
        EventHandler<ActionEvent> clickCancelBtnHandler = (ActionEvent event) -> {
            try {
                root = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            pageTitle = PageLoader.getAppointmentsTitle();
            stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            PageLoader.pageLoad(stage, root, pageTitle);
        };

        EventHandler<ActionEvent> clickClearBtnHandler = (ActionEvent event) -> {
            titleTF.setText("");
            descriptionTA.setText("");
            locationCB.getSelectionModel().clearSelection();
            dateDP.setValue(null);
            timeCB.getSelectionModel().clearSelection();
            lengthCB.getSelectionModel().clearSelection();
            teacherCB.getSelectionModel().clearSelection();
            studentCB.getSelectionModel().clearSelection();
        };
        
        EventHandler<ActionEvent> clickSaveBtnHandler = (ActionEvent event) -> {
            int id = Integer.parseInt(idTF.getText());
            String title = titleTF.getText();
            String description = descriptionTA.getText();
            String location = locationCB.getSelectionModel().getSelectedItem();
            LocalDate date = dateDP.getValue();
            LocalTime startTime = timeCB.getValue();
            LocalTime endTime = timeCB.getValue();
            String length = lengthCB.getSelectionModel().getSelectedItem();
            InstrumentTeacher teacher = teacherCB.getSelectionModel().getSelectedItem();
            InstrumentStudent student = studentCB.getSelectionModel().getSelectedItem();
            if (StringUtils.isEmptyOrWhitespaceOnly(title)
                    || StringUtils.isEmptyOrWhitespaceOnly(description)) {
                Alerts.invalidFields();
                return;
            }
            try {
                if (length.matches("30 Minutes")) {
                    endTime = startTime.plusMinutes(30);
                } else if (length.matches("1 Hour")) {
                    endTime = startTime.plusHours(1);
                } else if (length.matches("1 Hour 30 Minutes")) {
                    endTime = startTime.plusMinutes(30);
                    endTime = startTime.plusHours(1);
                } else if (length.matches("2 Hours")) {
                    endTime = startTime.plusHours(2);
                }
                String teacherName = teacher.getName();
                int teacherId = teacher.getId();
                String studentName = student.getName();
                int studentId = student.getId();
                AppointmentDAO.selectAppointments();
                Boolean teacherOverlap = Data.checkTeacherOverlap(teacherId, startTime, endTime, date);
                Boolean studentOverlap = Data.checkStudentOverlap(studentId, startTime, endTime, date);
                if (!teacherOverlap && !studentOverlap) {
                    LocalDateTime start = LocalDateTime.of(date, startTime);
                    LocalDateTime end = LocalDateTime.of(date, endTime);
                    AppointmentDAO.updateAppointment(
                            id,
                            title,
                            description,
                            location,
                            start,
                            end,
                            teacherName,
                            teacherId,
                            studentName,
                            studentId);
                    Parent root1;
                    try {
                        root1 = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
                        stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                        String pageTitle1 = PageLoader.getAppointmentsTitle();
                        PageLoader.pageLoad(stage, root1, pageTitle1);
                    }catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    Alerts.appointmentOverlap();
                }
            }catch (SQLException exception) {
                exception.printStackTrace();
            }catch (NullPointerException exception) {
                Alerts.invalidFields();
            }
        };
        
        cancelBTN.setOnAction(clickCancelBtnHandler);
            clearBTN.setOnAction(clickClearBtnHandler);
        saveBTN.setOnAction(clickSaveBtnHandler);
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

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
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class AppointmentsController implements Initializable {

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
    private Tab allTab;    
    @FXML
    private Tab monthTab;
    @FXML
    private TableView<Appointment> monthApptTable;
    @FXML
    private TableColumn<Appointment, Integer> monthIdCol;
    @FXML
    private TableColumn<Appointment, String> monthTitleCol;
    @FXML
    private TableColumn<Appointment, String> monthDescriptionCol;
    @FXML
    private TableColumn<Appointment, String> monthLocationCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> monthStartCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> monthEndCol;
    @FXML
    private TableColumn<Appointment, String> monthTeacherCol;
    @FXML
    private TableColumn<Appointment, String> monthStudentCol;
    @FXML
    private Tab weekTab;
    @FXML
    private TableView<Appointment> weekApptTable;
    @FXML
    private TableColumn<Appointment, Integer> weekIdCol;
    @FXML
    private TableColumn<Appointment, String> weekTitleCol;
    @FXML
    private TableColumn<Appointment, String> weekDescriptionCol;
    @FXML
    private TableColumn<Appointment, String> weekLocationCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> weekStartCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> weekEndCol;
    @FXML
    private TableColumn<Appointment, String> weekTeacherCol;
    @FXML
    private TableColumn<Appointment, String> weekStudentCol;
    @FXML
    private Tab dayTab;
    @FXML
    private TableView<Appointment> dayApptTable;
    @FXML
    private TableColumn<Appointment, Integer> dayIdCol;
    @FXML
    private TableColumn<Appointment, String> dayTitleCol;
    @FXML
    private TableColumn<Appointment, String> dayDescriptionCol;
    @FXML
    private TableColumn<Appointment, String> dayLocationCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> dayStartCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> dayEndCol;
    @FXML
    private TableColumn<Appointment, String> dayTeacherCol;
    @FXML
    private TableColumn<Appointment, String> dayStudentCol;
    @FXML
    private Button addBTN;
    @FXML
    private Button updateBTN;
    @FXML
    private Button deleteBTN;
    @FXML
    private TableView<Appointment> allApptTable;
    @FXML
    private TableColumn<Appointment, Integer> allIdCol;
    @FXML
    private TableColumn<Appointment, String> allTitleCol;
    @FXML
    private TableColumn<Appointment, String> allDescriptionCol;
    @FXML
    private TableColumn<Appointment, String> allLocationCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> allStartCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> allEndCol;
    @FXML
    private TableColumn<Appointment, String> allTeacherCol;
    @FXML
    private TableColumn<Appointment, String> allStudentCol;
    @FXML
    private TabPane appointmentsTabPane;

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
        
        allIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        allTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        allDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        allLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        allStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));          
        allEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        allTeacherCol.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        allStudentCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        
        monthIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        monthTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        monthDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        monthLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        monthStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        monthEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        monthTeacherCol.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        monthStudentCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        
        weekIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        weekTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        weekDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        weekLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        weekStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        weekEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        weekTeacherCol.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        weekStudentCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        
        dayIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        dayTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        dayDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dayLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        dayStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        dayEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        dayTeacherCol.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        dayStudentCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        
        try {
            AppointmentDAO.selectAppointments();
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        allApptTable.setItems(Data.getAllAppointments());
        allTab.setOnSelectionChanged(EventHandle.appointmentsAllTab(allApptTable));
        monthTab.setOnSelectionChanged(EventHandle.appointmentsMonthTab(monthApptTable));
        weekTab.setOnSelectionChanged(EventHandle.appointmentsWeekTab(weekApptTable));
        dayTab.setOnSelectionChanged(EventHandle.appointmentsDayTab(dayApptTable));
        
        addBTN.setOnAction(EventHandle.appointmentsAddBTN());
        deleteBTN.setOnAction(EventHandle.appointmentsDeleteBTN(allApptTable, monthApptTable, weekApptTable, dayApptTable));
        updateBTN.setOnAction(EventHandle.appointmentUpdateBTN(allApptTable));
    }    
    
}

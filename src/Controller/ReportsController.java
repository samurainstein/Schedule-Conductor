/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AppointmentDAO;
import DAO.InstrumentStudentDAO;
import DAO.InstrumentTeacherDAO;
import Model.Appointment;
import Model.Data;
import Model.InstrumentStudent;
import Model.InstrumentTeacher;
import Utilities.DateAndTime;
import Utilities.EventHandlerNavMenu;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class ReportsController implements Initializable {

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
    private TabPane reportsTabPane;
    @FXML
    private Tab apptTotals;
    @FXML
    private ComboBox<String> locationCB;
    @FXML
    private ComboBox<Month> monthCB;
    @FXML
    private Button runBTN;
    @FXML
    private Label totalNumLBL;
    @FXML
    private Tab studentsByInstTAB;
    @FXML
    private TableView<InstrumentStudent> studentsTable;
    @FXML
    private TableColumn<InstrumentStudent, Integer> idCol;
    @FXML
    private TableColumn<InstrumentStudent, String> nameCol;
    @FXML
    private TableColumn<InstrumentStudent, String> countryCol;
    @FXML
    private TableColumn<InstrumentStudent, String> divisionCol;
    @FXML
    private TableColumn<InstrumentStudent, String> postalCodeCol;
    @FXML
    private TableColumn<InstrumentStudent, String> addressCol;
    @FXML
    private TableColumn<InstrumentStudent, String> phoneCol;
    @FXML
    private TableColumn<InstrumentStudent, String> instrumentCol;
    @FXML
    private TableColumn<InstrumentStudent, String> availableOnlineCol;
    @FXML
    private TableColumn<InstrumentStudent, String> availableInPersonCol;
    @FXML
    private ComboBox<String> instrumentCB;
    @FXML
    private Button runBTN2;
    @FXML
    private Tab teachApptTAB;
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
    private ComboBox<InstrumentTeacher> teacherCB;
    @FXML
    private Button runBTN3;

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
        reportsLBL.setStyle("-fx-background-color: derive(#939DAD, 70%);");
        reportsLBL.setOnMouseClicked(EventHandlerNavMenu.navReportsEvent());
        logoutLabel.setOnMouseClicked(EventHandlerNavMenu.navLogoutEvent());        
        
        locationCB.setItems(Data.getLocations());
        monthCB.setItems(DateAndTime.getAllMonths());
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("Country"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("Division"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        instrumentCol.setCellValueFactory(new PropertyValueFactory<>("Instrument"));
        availableOnlineCol.setCellValueFactory(new PropertyValueFactory<>("AvailableOnline"));
        availableInPersonCol.setCellValueFactory(new PropertyValueFactory<>("AvailableInPerson"));
        
        allIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        allTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        allDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        allLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        allStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));          
        allEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        allTeacherCol.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        allStudentCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        
//        EventHandler apptTotals = new EventHandler() {
//            @Override
//            public void handle(Event arg0) {
//                    locationCB.getItems().clear();
//                    monthCB.getItems().clear();
//                    locationCB.setItems(Data.getLocations());
//                    monthCB.setItems(DateAndTime.getAllMonths());  
//                    locationCB.setPromptText("Select a location");
//                    monthCB.setPromptText("Select a month");
//            }
//        };
//        studentsByInstTAB.setOnSelectionChanged(apptTotals);
        
        EventHandler stdntByInstTab = (EventHandler) (Event arg0) -> {
            try {
                instrumentCB.setItems(Data.getAllInstruments());
                instrumentCB.setPromptText("Select an instrument");
                InstrumentStudentDAO.selectStudentInstruments();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        };
        
        EventHandler apptsByTeacherTab = (EventHandler) (Event arg0) -> {
            InstrumentTeacherDAO.selectTeachers();
            teacherCB.setItems(Data.getAllTeachers());
            teacherCB.setPromptText("Select a teacher");
        };
        
        EventHandler apptTotalsHandler = (EventHandler) (Event arg0) -> {
            String location = locationCB.getSelectionModel().getSelectedItem();
            Month month = monthCB.getSelectionModel().getSelectedItem();
            LocalDateTime startLDT = DateAndTime.getMonthStart(month);
            LocalDateTime endLDT = DateAndTime.getMonthEnd(month);
            int total;
            try {
                total = AppointmentDAO.getTotalsReport(location, startLDT, endLDT);
                totalNumLBL.setText(String.valueOf(total));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        };
        
        EventHandler studentsByInstHandler = (EventHandler) (Event arg0) -> {
            studentsTable.getItems().clear();
            try {
                String instrumentSearch = instrumentCB.getSelectionModel().getSelectedItem();
                InstrumentStudentDAO.selectStudentsByInstrument(instrumentSearch);
                studentsTable.setItems(Data.getInstrumentStudents());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        };
        
        EventHandler teacherAppointmentsHandler = (EventHandler) (Event arg0) -> {
            allApptTable.getItems().clear();
            int teacherIdSearch = teacherCB.getSelectionModel().getSelectedItem().getId();
            AppointmentDAO.selectAppointmentsByTeacher(teacherIdSearch);
            allApptTable.setItems(Data.getAppointmentsByTeacher());
        };
        
        studentsByInstTAB.setOnSelectionChanged(stdntByInstTab);
        teachApptTAB.setOnSelectionChanged(apptsByTeacherTab);
        runBTN.setOnAction(apptTotalsHandler);
        runBTN2.setOnAction(studentsByInstHandler);
        runBTN3.setOnAction(teacherAppointmentsHandler);
    }
}



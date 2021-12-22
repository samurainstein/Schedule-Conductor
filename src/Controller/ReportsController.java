/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AppointmentDAO;
import DAO.InstrumentStudentDAO;
import Model.Data;
import Model.InstrumentStudent;
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
    private TableColumn<InstrumentStudent, String> instrumentCol;
    @FXML
    private TableColumn<InstrumentStudent, String> availableOnlineCol;
    @FXML
    private TableColumn<InstrumentStudent, String> availableInPersonCol;
    @FXML
    private ComboBox<String> instrumentCB;
    @FXML
    private Button runBTN2;

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
        monthCB.setItems(DateAndTime.getAllMonths());
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("Country"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("Division"));
        instrumentCol.setCellValueFactory(new PropertyValueFactory<>("Instrument"));
        availableOnlineCol.setCellValueFactory(new PropertyValueFactory<>("AvailableOnline"));
        availableInPersonCol.setCellValueFactory(new PropertyValueFactory<>("AvailableInPerson"));
        
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
        
        EventHandler stdntByInstTab = new EventHandler() {
            @Override
            public void handle(Event arg0) {
                try {
                    instrumentCB.setItems(Data.getAllInstruments());
                    instrumentCB.setPromptText("Select and instrument");
                    InstrumentStudentDAO.selectStudentInstruments();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        };
        
        EventHandler apptTotalsHandler = new EventHandler() {
            @Override
            public void handle(Event arg0) {
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
            }
        };
        
        EventHandler studentsByInstHandler = new EventHandler() {
            @Override
            public void handle(Event arg0) {
                studentsTable.getItems().clear();
                try {
                    String instrumentSearch = instrumentCB.getSelectionModel().getSelectedItem();
                    InstrumentStudentDAO.selectStudentsByInstrument(instrumentSearch);
                    studentsTable.setItems(Data.getInstrumentStudents());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        };
        
        studentsByInstTAB.setOnSelectionChanged(stdntByInstTab);
        runBTN.setOnAction(apptTotalsHandler);
        runBTN2.setOnAction(studentsByInstHandler);
    }
}



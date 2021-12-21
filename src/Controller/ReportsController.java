/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AppointmentDAO;
import DAO.InstrumentTeacherDAO;
import Model.Data;
import Utilities.Alerts;
import Utilities.DateAndTime;
import Utilities.EventHandle;
import Utilities.PageLoader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private Tab monthTab;
    @FXML
    private TableView<?> monthApptTable;
    @FXML
    private TableColumn<?, ?> monthIdCol;
    @FXML
    private TableColumn<?, ?> monthTitleCol;
    @FXML
    private TableColumn<?, ?> monthDescriptionCol;
    @FXML
    private TableColumn<?, ?> monthLocationCol;
    @FXML
    private TableColumn<?, ?> monthStartCol;
    @FXML
    private TableColumn<?, ?> monthEndCol;
    @FXML
    private TableColumn<?, ?> monthTeacherCol;
    @FXML
    private TableColumn<?, ?> monthStudentCol;
    @FXML
    private Tab weekTab;
    @FXML
    private TableView<?> weekApptTable;
    @FXML
    private TableColumn<?, ?> weekIdCol;
    @FXML
    private TableColumn<?, ?> weekTitleCol;
    @FXML
    private TableColumn<?, ?> weekDescriptionCol;
    @FXML
    private TableColumn<?, ?> weekLocationCol;
    @FXML
    private TableColumn<?, ?> weekStartCol;
    @FXML
    private TableColumn<?, ?> weekEndCol;
    @FXML
    private TableColumn<?, ?> weekTeacherCol;
    @FXML
    private TableColumn<?, ?> weekStudentCol;
    @FXML
    private Tab dayTab;
    @FXML
    private TableView<?> dayApptTable;
    @FXML
    private TableColumn<?, ?> dayIdCol;
    @FXML
    private TableColumn<?, ?> dayTitleCol;
    @FXML
    private TableColumn<?, ?> dayDescriptionCol;
    @FXML
    private TableColumn<?, ?> dayLocationCol;
    @FXML
    private TableColumn<?, ?> dayStartCol;
    @FXML
    private TableColumn<?, ?> dayEndCol;
    @FXML
    private TableColumn<?, ?> dayTeacherCol;
    @FXML
    private TableColumn<?, ?> dayStudentCol;
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
        
        locationCB.setItems(Data.getLocations());
        monthCB.setItems(DateAndTime.getAllMonths());
        
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
        runBTN.setOnAction(apptTotalsHandler);
    }
}



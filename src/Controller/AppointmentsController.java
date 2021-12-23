/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AppointmentDAO;
import Model.Appointment;
import Model.Data;
import Utilities.Alerts;
import Utilities.EventHandlerNavMenu;
import Utilities.PageLoader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
        
        EventHandler<Event> clickAllTabHandler = new EventHandler<Event>() {
            public void handle(Event event) {
                try {
                    AppointmentDAO.selectAppointments();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                allApptTable.setItems(Data.getAllAppointments());
            }
        };

        EventHandler<Event> clickMonthTabHandler = new EventHandler<Event>() {
            public void handle(Event event) {
                Data.clearMonthlyAppointments();
                try {
                    AppointmentDAO.selectAppointments();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                Data.filterMonthlyAppointments();
                monthApptTable.setItems(Data.getMonthlyAppointments());
            }
        };

        EventHandler<Event> clickWeekTabHandler = new EventHandler<Event>() {
            public void handle(Event event) {
                Data.clearWeeklyAppointments();
                try {
                    AppointmentDAO.selectAppointments();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                Data.filterWeeklyAppointments();
                weekApptTable.setItems(Data.getWeeklyAppointments());
            }
        };
        
        EventHandler<Event> clickDayTabHandler = new EventHandler<Event>() {
            public void handle(Event event) {
                Data.clearDailyAppointments();
                try {
                    AppointmentDAO.selectAppointments();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                Data.filterDailyAppointments();
                dayApptTable.setItems(Data.getDailyAppointments());
            }
        };
        
        EventHandler<ActionEvent> clickAddBtnHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/View/AddAppointment.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                pageTitle = PageLoader.getAppointmentAddTitle();
                stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                PageLoader.pageLoad(stage, root, pageTitle);
            }
        };

        EventHandler<ActionEvent> clickUpdateBtnHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TableView<Appointment> currentTable = dayApptTable;
                if (allApptTable.getSelectionModel().getSelectedItem() == null) {
                    if (monthApptTable.getSelectionModel().getSelectedItem() == null) {
                        if (weekApptTable.getSelectionModel().getSelectedItem() == null) {
                            if (dayApptTable.getSelectionModel().getSelectedItem() == null) {
                                alertNull();
                            } else {
                                currentTable = dayApptTable;
                            }
                        } else {
                            currentTable = weekApptTable;
                        }
                    } else {
                        currentTable = monthApptTable;
                    }
                } else {
                    currentTable = allApptTable;
                }
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/UpdateAppointment.fxml"));
                    Appointment selectedAppointment = currentTable.getSelectionModel().getSelectedItem();
                    String pageTitle = PageLoader.getAppointmentUpdateTitle();
                    try {
                        PageLoader.appointmentUpdatePageLoad(event, loader, pageTitle, selectedAppointment);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } catch (NullPointerException exception) {
                    alertNull();
                }
            }
        };
        
        EventHandler<ActionEvent> clickDeleteBtnHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TableView<Appointment> currentTable = dayApptTable;
                if (allApptTable.getSelectionModel().getSelectedItem() == null) {
                    if (monthApptTable.getSelectionModel().getSelectedItem() == null) {
                        if (weekApptTable.getSelectionModel().getSelectedItem() == null) {
                            if (dayApptTable.getSelectionModel().getSelectedItem() == null) {
                                alertNull();
                            } else {
                                currentTable = dayApptTable;
                            }
                        } else {
                            currentTable = weekApptTable;
                        }
                    } else {
                        currentTable = monthApptTable;
                    }
                } else {
                    currentTable = allApptTable;
                }

                Appointment appointment = currentTable.getSelectionModel().getSelectedItem();
                int appointmentID = appointment.getAppointmentID();
//                    allViewTable.setItems(Data.getAllAppointments());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this appointment?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    try {
                        AppointmentDAO.deleteAppointment(appointmentID);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        AppointmentDAO.selectAppointments();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        root = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    pageTitle = PageLoader.getAppointmentsTitle();
                    stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                    PageLoader.pageLoad(stage, root, pageTitle);
//                    currentTable.setItems(Data.getAllAppointments());
//                        Alerts.appointmentDeleteConfirm(appointmentID, type);
                }
            }
        };
        
        allTab.setOnSelectionChanged(clickAllTabHandler);
        monthTab.setOnSelectionChanged(clickMonthTabHandler);
        weekTab.setOnSelectionChanged(clickWeekTabHandler);
        dayTab.setOnSelectionChanged(clickDayTabHandler);
        
        addBTN.setOnAction(clickAddBtnHandler);
        deleteBTN.setOnAction(clickDeleteBtnHandler);
        updateBTN.setOnAction(clickUpdateBtnHandler);
    }

    /**
     * Method for generating an alert to indicate an invalid appointment
     * selection.
     */
    public static void alertNull() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Selection");
        alert.setContentText("Please select an appointment");
        alert.showAndWait();
    }
    
}

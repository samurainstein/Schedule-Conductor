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
import Utilities.Alerts;
import Utilities.EventHandlerNavMenu;
import Utilities.PageLoader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class StudentsController implements Initializable {

    @FXML
    private Label teachersLBL;
    @FXML
    private Label logoutLabel;
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
    private Label homeLBL;
    @FXML
    private TableView<InstrumentStudent> studentsTable;
    @FXML
    private Button addBTN;
    @FXML
    private Button updateBTN;
    @FXML
    private Button deleteBTN;
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
        
        InstrumentStudentDAO.selectStudents();
        studentsTable.setItems(Data.getAllStudents());

        EventHandler<ActionEvent> clickAddBtnHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/View/AddStudent.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                pageTitle = PageLoader.getStudentAddTitle();
                stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                PageLoader.pageLoad(stage, root, pageTitle);
            }
        };

        EventHandler<ActionEvent> clickUpdateBtnHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/UpdateStudent.fxml"));
                    InstrumentStudent selectedStudent = studentsTable.getSelectionModel().getSelectedItem();
                    String pageTitle = PageLoader.getStudentUpdateTitle();
                    try {
                        PageLoader.studentUpdatePageLoad(event, loader, pageTitle, selectedStudent);
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

                try {
                    InstrumentStudent student = studentsTable.getSelectionModel().getSelectedItem();
                    int id = student.getId();
                    try {
                        AppointmentDAO.selectAppointments();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    boolean checkAssociated = Data.checkStudAssocAppt(id);
                    if (checkAssociated == true) {
                        Alerts.associatedAppointment();
                    } else {

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this student?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            InstrumentStudentDAO.deleteStudent(id);
                            InstrumentStudentDAO.selectStudents();
                            studentsTable.setItems(Data.getAllStudents());
                            alertDeleteConfirm();
                        }
                    }
                } catch (NullPointerException exception) {
                    alertNull();
                }
            }
        };

        addBTN.setOnAction(clickAddBtnHandler);
        updateBTN.setOnAction(clickUpdateBtnHandler);
        deleteBTN.setOnAction(clickDeleteBtnHandler);
    }
    
    /**
     * Method for generating an alert to indicate an invalid teacher selection. 
     */
    public static void alertNull() {
        String alertTitle = "Invalid Selection";
        String alertText = "Please select a student";
        Alert invalidAlert = new Alert(Alert.AlertType.ERROR);
        invalidAlert.setTitle(alertTitle);
        invalidAlert.setContentText(alertText);
        invalidAlert.showAndWait();
    }
    
    /**
     * Method for generating an alert to confirm that a teacher was deleted. 
     */
    public static void alertDeleteConfirm() {
        String alertTitle = "Confirmation";
        String alertText = "Student was deleted";
        Alert confirmAlert = new Alert(Alert.AlertType.INFORMATION);
        confirmAlert.setTitle(alertTitle);
        confirmAlert.setContentText(alertText);
        confirmAlert.showAndWait();
    }
}

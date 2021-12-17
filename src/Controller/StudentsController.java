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
import Utilities.EventHandle;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
        logoutLabel.setOnMouseClicked(EventHandle.navLogoutEvent());

        addBTN.setOnAction(EventHandle.studentsAddBTN());
        updateBTN.setOnAction(EventHandle.studentsUpdateBTN(studentsTable));
        try {
            deleteBTN.setOnAction(EventHandle.studentDeleteBTN(studentsTable));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

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
    }

}

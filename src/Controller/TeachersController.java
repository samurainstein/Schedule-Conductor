/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.InstrumentTeacherDAO;
import Model.Data;
import Model.InstrumentTeacher;
import Utilities.EventHandle;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TeachersController implements Initializable {

    @FXML
    private Label teachersLBL;
    @FXML
    private Label logoutLabel;
    @FXML
    private TableColumn<InstrumentTeacher, Integer> idCol;
    @FXML
    private TableColumn<InstrumentTeacher, String> nameCol;
    @FXML
    private TableColumn<InstrumentTeacher, String> countryCol;
    @FXML
    private TableColumn<InstrumentTeacher, String> divisionCol;
    @FXML
    private TableColumn<InstrumentTeacher, String> postalCodeCol;
    @FXML
    private TableColumn<InstrumentTeacher, String> addressCol;
    @FXML
    private TableColumn<InstrumentTeacher, String> phoneCol;
    @FXML
    private TableColumn<InstrumentTeacher, String> instrumentCol;
    @FXML
    private TableColumn<InstrumentTeacher, String> availableOnlineCol;
    @FXML
    private TableColumn<InstrumentTeacher, String> availableInPersonCol;
    @FXML
    private Label homeLBL;
    @FXML
    private TableView<InstrumentTeacher> teachersTable;
    @FXML
    private Button addBTN;
    @FXML
    private Button updateBTN;
    @FXML
    private Button deleteBTN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homeLBL.setOnMouseClicked(EventHandle.navHomeEvent());
        logoutLabel.setOnMouseClicked(EventHandle.navLogoutEvent());
        teachersLBL.setOnMouseClicked(EventHandle.navTeachersEvent());

//        addBTN.setOnAction(arg0);
//        updateBTN.setOnAction(arg0);
        try {
            deleteBTN.setOnAction(EventHandle.teachersDeleteEvent(teachersTable));
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
        InstrumentTeacherDAO.selectTeachers();
        teachersTable.setItems(Data.getAllTeachers());
    }

}

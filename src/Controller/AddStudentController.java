/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CountryDAO;
import Model.Country;
import Model.Data;
import Model.Division;
import Utilities.EventHandle;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class AddStudentController implements Initializable {

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
    private TextField nameTF;
    @FXML
    private ComboBox<Country> countryCB;
    @FXML
    private ComboBox<Division> divisionCB;
    @FXML
    private TextField postalTF;
    @FXML
    private TextField addressTF;
    @FXML
    private TextField phoneTF;
    @FXML
    private TextField instrumentTF;
    @FXML
    private RadioButton onlineYRB;
    @FXML
    private ToggleGroup onlineTGL;
    @FXML
    private RadioButton onlineNRB;
    @FXML
    private RadioButton inPersonYRB;
    @FXML
    private ToggleGroup inPersonTGL;
    @FXML
    private RadioButton inPersonNRB;
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
        reportsLBL.setOnMouseClicked(EventHandle.navReportsEvent());
        logoutLabel.setOnMouseClicked(EventHandle.navLogoutEvent());
        
        CountryDAO.selectCountries();
        countryCB.setItems(Data.getAllCountries());
        countryCB.setPromptText("Select a Country");
        
        onlineTGL.selectToggle(onlineNRB);
        inPersonTGL.selectToggle(inPersonNRB);

        try {
            countryCB.setOnAction(EventHandle.comboCountrySelectEvent(countryCB, divisionCB));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        cancelBTN.setOnAction(EventHandle.studentCancelBTN());
        try {
            clearBTN.setOnAction(EventHandle.studentAddClearBTN(
                    nameTF,
                    countryCB,
                    divisionCB,
                    postalTF,
                    addressTF,
                    phoneTF,
                    instrumentTF,
                    onlineTGL,
                    onlineNRB,
                    inPersonTGL,
                    inPersonNRB));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        try {
            saveBTN.setOnAction(EventHandle.studentAddSaveBTN(
                    nameTF, 
                    countryCB, 
                    divisionCB, 
                    postalTF, 
                    addressTF, 
                    phoneTF, 
                    instrumentTF, 
                    onlineTGL, 
                    inPersonTGL));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }    
    
}

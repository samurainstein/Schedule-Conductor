/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CountryDAO;
import DAO.DivisionDAO;
import Model.Country;
import Model.Data;
import Model.Division;
import Model.InstrumentStudent;
import Model.InstrumentTeacher;
import Utilities.EventHandle;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class UpdateStudentController implements Initializable {

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
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;
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
        logoutLabel.setOnMouseClicked(EventHandle.navLogoutEvent());
        
        saveBTN.setOnAction(EventHandle.studentUpdateSaveBTN(
                idTF, 
                nameTF, 
                countryCB, 
                divisionCB, 
                postalTF, 
                addressTF, 
                phoneTF, 
                instrumentTF, 
                onlineTGL, 
                inPersonTGL));
        
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
        cancelBTN.setOnAction(EventHandle.studentCancelBTN());
        
        try {
            countryCB.setOnAction(EventHandle.comboCountrySelectEvent(countryCB, divisionCB));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

public void passStudentData(InstrumentStudent student) {
        idTF.setText(Integer.toString(student.getId()));
        nameTF.setText(student.getName());
        postalTF.setText(student.getPostalCode());
        addressTF.setText(student.getAddress());
        phoneTF.setText(student.getPhone());
        instrumentTF.setText(student.getInstrument());

        char availableOnline = student.getAvailableOnline();
        if (availableOnline == 'Y') {
            onlineTGL.selectToggle(onlineYRB);
        } else {
            onlineTGL.selectToggle(onlineNRB);
        }
        char availableInPerson = student.getAvailableInPerson();
        if (availableInPerson == 'Y') {
            inPersonTGL.selectToggle(inPersonYRB);
        } else {
            inPersonTGL.selectToggle(inPersonNRB);
        }
        
        String teacherCountry = student.getCountry();
        CountryDAO.selectCountries();
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        allCountries = Data.getAllCountries();
        countryCB.setItems(allCountries);
        for (Country country : allCountries) {
            if (country.getCountryName().matches(teacherCountry)) {
                countryCB.setValue(country);
                break;
            }
        }

        int countryID = 0;
        String teacherDivision = student.getDivision();;
        DivisionDAO.selectDivisions();
        ObservableList<Division> allDivisions = FXCollections.observableArrayList();
        ObservableList<Division> filteredDivisions = FXCollections.observableArrayList();

        allDivisions = Data.getAllDivisions();
        for (Division div : allDivisions) {
            if (teacherDivision.equals(div.getDivisionName())) {
                countryID = div.getCountryID();
                DivisionDAO.selectFilteredDivisions(countryID);
                filteredDivisions = Data.getFilteredDivisions();
                divisionCB.setItems(filteredDivisions);
                divisionCB.setValue(div);
                break;
            }
        }
    }    
    
}
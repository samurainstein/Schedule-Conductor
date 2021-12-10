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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class AddTeacherController implements Initializable {

    @FXML
    private Label homeLBL;
    @FXML
    private Label teachersLBL;
    @FXML
    private Label logoutLabel;
    @FXML
    private Button addBTN;
    @FXML
    private Button updateBTN;
    @FXML
    private Button deleteBTN;
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
    private RadioButton onlineNRB;
    @FXML
    private RadioButton inPersonYRB;
    @FXML
    private RadioButton inPersonNRB;
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private Label teacherAddLBL;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homeLBL.setOnMouseClicked(EventHandle.navHomeEvent());
        teachersLBL.setOnMouseClicked(EventHandle.navTeachersEvent());
        teacherAddLBL.setOnMouseClicked(EventHandle.navTeacherAddEvent());
        logoutLabel.setOnMouseClicked(EventHandle.navLogoutEvent());
        CountryDAO.selectCountries();
        countryCB.setItems(Data.getAllCountries());
        countryCB.setPromptText("Select a Country");
        try {
            countryCB.setOnAction(EventHandle.comboCountrySelectEvent(countryCB, divisionCB));
            //FIX THIS: Filter divisions based on country
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }    
    
}

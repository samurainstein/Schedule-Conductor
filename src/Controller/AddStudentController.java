/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CountryDAO;
import DAO.DivisionDAO;
import DAO.InstrumentStudentDAO;
import Model.Country;
import Model.Data;
import Model.Division;
import Utilities.Alerts;
import Utilities.EventHandlerNavMenu;
import Utilities.PageLoader;
import com.mysql.cj.util.StringUtils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

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
        studentAddLBL.setStyle("-fx-background-color: derive(#939DAD, 70%);");
        studentAddLBL.setOnMouseClicked(EventHandlerNavMenu.navStudentAddEvent());
        appointmentsLBL.setOnMouseClicked(EventHandlerNavMenu.navAppointmentsEvent());
        appointmentAddLBL.setOnMouseClicked(EventHandlerNavMenu.navAppointmentAddEvent());
        reportsLBL.setOnMouseClicked(EventHandlerNavMenu.navReportsEvent());
        logoutLabel.setOnMouseClicked(EventHandlerNavMenu.navLogoutEvent());

        CountryDAO.selectCountries();
        countryCB.setItems(Data.getAllCountries());
        countryCB.setPromptText("Select a Country");

        onlineTGL.selectToggle(onlineNRB);
        inPersonTGL.selectToggle(inPersonNRB);

        EventHandler<ActionEvent> comboCountryHandler = (ActionEvent event) -> {
            if (!(countryCB.getSelectionModel().isEmpty())) {
                divisionCB.getItems().clear();
                int countryIDSelection = countryCB.getValue().getCountryID();
                DivisionDAO.selectFilteredDivisions(countryIDSelection);
                divisionCB.setItems(Data.getFilteredDivisions());
                divisionCB.setPromptText("Please Select a Division");
            }
        };

        EventHandler<ActionEvent> clickCancelBtnHandler = (ActionEvent event) -> {
            try {
                root = FXMLLoader.load(getClass().getResource("/View/Students.fxml"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            pageTitle = PageLoader.getStudentsTitle();
            stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            PageLoader.pageLoad(stage, root, pageTitle);
        };

        EventHandler<ActionEvent> clickClearBtnHandler = (ActionEvent event) -> {
            nameTF.setText("");
            countryCB.getSelectionModel().clearSelection();
            divisionCB.getItems().clear();
            postalTF.setText("");
            addressTF.setText("");
            phoneTF.setText("");
            instrumentTF.setText("");
            onlineTGL.selectToggle(onlineNRB);
            inPersonTGL.selectToggle(inPersonNRB);
        };

        EventHandler<ActionEvent> clickSaveBtnHandler = (ActionEvent event) -> {
            String name = nameTF.getText();
            String postal = postalTF.getText();
            String address = addressTF.getText();
            String phone = phoneTF.getText();
            String instrument = instrumentTF.getText();
            RadioButton onlineRadio = (RadioButton) onlineTGL.getSelectedToggle();
            char onlineRadioChar = onlineRadio.getText().charAt(0);
            RadioButton inPersonRadio = (RadioButton) inPersonTGL.getSelectedToggle();
            char inPersonRadioChar = inPersonRadio.getText().charAt(0);
            
            if (StringUtils.isEmptyOrWhitespaceOnly(name)
                    || StringUtils.isEmptyOrWhitespaceOnly(postal)
                    || StringUtils.isEmptyOrWhitespaceOnly(address)
                    || StringUtils.isEmptyOrWhitespaceOnly(phone)
                    || StringUtils.isEmptyOrWhitespaceOnly(instrument)) {
                Alerts.invalidFields();
                return;
            }
            
            try {
                String country = countryCB.getSelectionModel().getSelectedItem().getCountryName();
                String division = divisionCB.getSelectionModel().getSelectedItem().getDivisionName();
                InstrumentStudentDAO.insertStudent(name, country, division, postal, address, phone,
                        instrument, onlineRadioChar, inPersonRadioChar);
                try {
                    root = FXMLLoader.load(getClass().getResource("/View/Students.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                pageTitle = PageLoader.getStudentsTitle();
                stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                PageLoader.pageLoad(stage, root, pageTitle);
            } catch (NullPointerException ex) {
                Alerts.countryOrDivisionNullAlert();
            }
        };

        countryCB.setOnAction(comboCountryHandler);
        cancelBTN.setOnAction(clickCancelBtnHandler);
        clearBTN.setOnAction(clickClearBtnHandler);
        saveBTN.setOnAction(clickSaveBtnHandler);
    }

}

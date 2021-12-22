/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CountryDAO;
import DAO.DivisionDAO;
import DAO.InstrumentTeacherDAO;
import Model.Country;
import Model.Data;
import Model.Division;
import Model.InstrumentTeacher;
import Utilities.Alerts;
import Utilities.EventHandlerNavMenu;
import Utilities.PageLoader;
import com.mysql.cj.util.StringUtils;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class UpdateTeacherController implements Initializable {

    @FXML
    private Label homeLBL;
    @FXML
    private Label teachersLBL;
    @FXML
    private Label teacherAddLBL;
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
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private Button saveBTN;
    @FXML
    private Button clearBTN;
    @FXML
    private Button cancelBTN;
    @FXML
    private TextField idTF;
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

        EventHandler<ActionEvent> comboCountryHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                if (!(countryCB.getSelectionModel().isEmpty())) {
                    divisionCB.getItems().clear();
                    int countryIDSelection = countryCB.getValue().getCountryID();
                    DivisionDAO.selectFilteredDivisions(countryIDSelection);
                    divisionCB.setItems(Data.getFilteredDivisions());
                    divisionCB.setPromptText("Please Select a Division");
                }
            }
        };

        EventHandler<ActionEvent> clickSaveBtnHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                int id = Integer.parseInt(idTF.getText());
                String name = nameTF.getText();
                String postal = postalTF.getText();
                String address = addressTF.getText();
                String phone = phoneTF.getText();
                String instrument = instrumentTF.getText();
                RadioButton onlineRadio = (RadioButton) onlineTGL.getSelectedToggle();
                char onlineRadioChar = onlineRadio.getText().charAt(0);
                RadioButton inPersonRadio = (RadioButton) inPersonTGL.getSelectedToggle();
                char inPersonRadioChar = inPersonRadio.getText().charAt(0);
                String username = usernameTF.getText();
                String password = passwordTF.getText();

                if (StringUtils.isEmptyOrWhitespaceOnly(name)
                        || StringUtils.isEmptyOrWhitespaceOnly(postal)
                        || StringUtils.isEmptyOrWhitespaceOnly(address)
                        || StringUtils.isEmptyOrWhitespaceOnly(phone)
                        || StringUtils.isEmptyOrWhitespaceOnly(instrument)
                        || StringUtils.isEmptyOrWhitespaceOnly(username)
                        || StringUtils.isEmptyOrWhitespaceOnly(password)) {
                    Alerts.invalidFields();
                    return;
                }

                try {
                    String country = countryCB.getSelectionModel().getSelectedItem().getCountryName();
                    String division = divisionCB.getSelectionModel().getSelectedItem().getDivisionName();
                    InstrumentTeacherDAO.updateTeacher(id, name, country, division, postal, address, phone,
                            instrument, onlineRadioChar, inPersonRadioChar, username, password);
                    try {
                        root = FXMLLoader.load(getClass().getResource("/View/Teachers.fxml"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    pageTitle = PageLoader.getTeachersTitle();
                    stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                    PageLoader.pageLoad(stage, root, pageTitle);
                } catch (NullPointerException ex) {
                    Alerts.countryOrDivisionNullAlert();
                }
            }
        };

        EventHandler<ActionEvent> clickClearBtnHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                nameTF.setText("");
                countryCB.getSelectionModel().clearSelection();
                divisionCB.getItems().clear();
                postalTF.setText("");
                addressTF.setText("");
                phoneTF.setText("");
                instrumentTF.setText("");
                onlineTGL.selectToggle(onlineNRB);
                inPersonTGL.selectToggle(inPersonNRB);
                usernameTF.setText("");
                passwordTF.setText("");
            }
        };

        EventHandler<ActionEvent> clickCancelBtnHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/View/Teachers.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                pageTitle = PageLoader.getTeachersTitle();
                stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                PageLoader.pageLoad(stage, root, pageTitle);
            }
        };

        saveBTN.setOnAction(clickSaveBtnHandler);
        clearBTN.setOnAction(clickClearBtnHandler);
        cancelBTN.setOnAction(clickCancelBtnHandler);
        countryCB.setOnAction(comboCountryHandler);

    }

    public void passTeacherData(InstrumentTeacher teacher) {
        idTF.setText(Integer.toString(teacher.getId()));
        nameTF.setText(teacher.getName());
        postalTF.setText(teacher.getPostalCode());
        addressTF.setText(teacher.getAddress());
        phoneTF.setText(teacher.getPhone());
        instrumentTF.setText(teacher.getInstrument());
        usernameTF.setText(teacher.getUsername());
        passwordTF.setText(teacher.getPassword());

        char availableOnline = teacher.getAvailableOnline();
        if (availableOnline == 'Y') {
            onlineTGL.selectToggle(onlineYRB);
        } else {
            onlineTGL.selectToggle(onlineNRB);
        }
        char availableInPerson = teacher.getAvailableInPerson();
        if (availableInPerson == 'Y') {
            inPersonTGL.selectToggle(inPersonYRB);
        } else {
            inPersonTGL.selectToggle(inPersonNRB);
        }

        String teacherCountry = teacher.getCountry();
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
        String teacherDivision = teacher.getDivision();;
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

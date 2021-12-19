/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import DAO.AppointmentDAO;
import DAO.DivisionDAO;
import DAO.InstrumentStudentDAO;
import DAO.InstrumentTeacherDAO;
import Model.Appointment;
import Model.Country;
import Model.Data;
import Model.Division;
import Model.InstrumentStudent;
import Model.InstrumentTeacher;
import com.mysql.cj.util.StringUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Eric
 */
public abstract class EventHandle {

    private static Parent root;
    private static Stage stage;
    private static String pageTitle;

    public static EventHandler<KeyEvent> loginKeyEvent(TextField usernameTF, TextField passwordTF) {
        EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    String username = usernameTF.getText();
                    String password = passwordTF.getText();
                    int id = InstrumentTeacherDAO.teacherLogin(username, password);
                    if (id == 0) {
                        Alerts.loginInvalid();
                    } else {
                        try {
                            root = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        Data.setLoggedInTeacherID(id);
                        pageTitle = PageLoader.getHomeTitle();
                        stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                        PageLoader.pageLoad(stage, root, pageTitle);
                    }
                }
            }
        };
        return eventHandler;
    }

    public static EventHandler<ActionEvent> loginActionEvent(TextField usernameTF, TextField passwordTF) {
        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String username = usernameTF.getText();
                String password = passwordTF.getText();
                int id = InstrumentTeacherDAO.teacherLogin(username, password);
                if (id == 0) {
                    Alerts.loginInvalid();
                } else {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Data.setLoggedInTeacherID(id);
                    pageTitle = PageLoader.getHomeTitle();
                    stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                    PageLoader.pageLoad(stage, root, pageTitle);
                }
            }

        };
        return eventHandler;
    }

    public static EventHandler<MouseEvent> navHomeEvent() {

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                pageTitle = PageLoader.getHomeTitle();
                stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                PageLoader.pageLoad(stage, root, pageTitle);
            }
        };

        return eventHandler;
    }

    public static EventHandler<MouseEvent> navTeachersEvent() {

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
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

        return eventHandler;
    }

    public static EventHandler<MouseEvent> navTeacherAddEvent() {

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/View/AddTeacher.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                pageTitle = PageLoader.getTeacherAddTitle();
                stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                PageLoader.pageLoad(stage, root, pageTitle);
            }
        };

        return eventHandler;
    }

    public static EventHandler<MouseEvent> navStudentsEvent() {

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/View/Students.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                pageTitle = PageLoader.getStudentsTitle();
                stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                PageLoader.pageLoad(stage, root, pageTitle);
            }
        };

        return eventHandler;
    }

    public static EventHandler<MouseEvent> navStudentAddEvent() {

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
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

        return eventHandler;
    }

    public static EventHandler<MouseEvent> navAppointmentsEvent() {

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                pageTitle = PageLoader.getAppointmentsTitle();
                stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                PageLoader.pageLoad(stage, root, pageTitle);
            }
        };

        return eventHandler;
    }

    public static EventHandler<MouseEvent> navAppointmentAddEvent() {
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
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
        return eventHandler;
    }

    public static EventHandler<MouseEvent> navLogoutEvent() {

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                pageTitle = PageLoader.getLoginTitle();
                stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                PageLoader.pageLoad(stage, root, pageTitle);
            }
        };

        return eventHandler;
    }

    public static EventHandler<ActionEvent> teachersAddBTN() {

        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/View/AddTeacher.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                pageTitle = PageLoader.getTeacherAddTitle();
                stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                PageLoader.pageLoad(stage, root, pageTitle);
            }
        };

        return eventHandler;
    }

    public static EventHandler<ActionEvent> teachersDeleteBTN(TableView<InstrumentTeacher> teachersTable) throws SQLException {
        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                try {
                    InstrumentTeacher teacher = teachersTable.getSelectionModel().getSelectedItem();
                    int id = teacher.getId();
                    try {
                        AppointmentDAO.selectAppointments();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    boolean checkAssociated = Data.checkTeachAssocAppt(id);
                    if (checkAssociated == true) {
                        Alerts.associatedAppointment();
                    } else {

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this teacher?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            InstrumentTeacherDAO.deleteTeacher(id);
                            InstrumentTeacherDAO.selectTeachers();
                            teachersTable.setItems(Data.getAllTeachers());
                            Alerts.teacherDeleteConfirm();
                        }
                    }
                } catch (NullPointerException exception) {
                    Alerts.teacherDeleteNull();
                }
            }
        };
        return eventHandler;
    }

    public static EventHandler<ActionEvent> teachersUpdateBTN(TableView<InstrumentTeacher> teachersTable) {
        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/UpdateTeacher.fxml"));
                    InstrumentTeacher selectedTeacher = teachersTable.getSelectionModel().getSelectedItem();
                    String pageTitle = PageLoader.getTeacherUpdateTitle();
                    try {
                        PageLoader.teachUpdatePageLoad(event, loader, pageTitle, selectedTeacher);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } catch (NullPointerException exception) {
                    Alerts.teacherDeleteNull();
                }
            }
        };
        return eventHandler;
    }

    public static EventHandler<ActionEvent> comboCountrySelectEvent(ComboBox<Country> countryCombo, ComboBox<Division> divisionCombo) throws SQLException {
        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                if (!(countryCombo.getSelectionModel().isEmpty())) {
                    divisionCombo.getItems().clear();
                    int countryIDSelection = countryCombo.getValue().getCountryID();
                    DivisionDAO.selectFilteredDivisions(countryIDSelection);
                    divisionCombo.setItems(Data.getFilteredDivisions());
                    divisionCombo.setPromptText("Please Select a Division");
                }
            }
        };
        return eventHandler;
    }

    public static EventHandler<ActionEvent> teachAddClearBTN(
            TextField nameTF,
            ComboBox<Country> countryCB,
            ComboBox<Division> divisionCB,
            TextField postalTF,
            TextField addressTF,
            TextField phoneTF,
            TextField instrumentTF,
            ToggleGroup onlineTGL,
            RadioButton onlineNRB,
            ToggleGroup inPersonTGL,
            RadioButton inPersonNRB,
            TextField usernameTF,
            TextField passwordTF
    ) throws SQLException {

        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
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
        return eventHandler;
    }

    public static EventHandler<ActionEvent> teachAddSaveBTN(
            TextField nameTF,
            ComboBox<Country> countryCB,
            ComboBox<Division> divisionCB,
            TextField postalTF,
            TextField addressTF,
            TextField phoneTF,
            TextField instrumentTF,
            ToggleGroup onlineTGL,
            ToggleGroup inPersonTGL,
            TextField usernameTF,
            TextField passwordTF
    ) throws SQLException {

        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

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
                    InstrumentTeacherDAO.insertTeacher(name, country, division, postal, address, phone,
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
        return eventHandler;
    }

    public static EventHandler<ActionEvent> teachCancelBTN() {

        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
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

        return eventHandler;
    }

    public static EventHandler<ActionEvent> teachUpdtSaveBTN(
            TextField idTF,
            TextField nameTF,
            ComboBox<Country> countryCB,
            ComboBox<Division> divisionCB,
            TextField postalTF,
            TextField addressTF,
            TextField phoneTF,
            TextField instrumentTF,
            ToggleGroup onlineTGL,
            ToggleGroup inPersonTGL,
            TextField usernameTF,
            TextField passwordTF) {
        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
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
        return eventHandler;
    }

    public static EventHandler<ActionEvent> studentsAddBTN() {

        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
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

        return eventHandler;
    }

    public static EventHandler<ActionEvent> studentsUpdateBTN(TableView<InstrumentStudent> studentsTable) {
        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
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
                    Alerts.teacherDeleteNull();
                }
            }
        };
        return eventHandler;
    }

    public static EventHandler<ActionEvent> studentDeleteBTN(TableView<InstrumentStudent> studentsTable) throws SQLException {
        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
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
                            Alerts.studentDeleteConfirm();
                        }
                    }
                } catch (NullPointerException exception) {
                    Alerts.studentDeleteNull();
                }
            }
        };
        return eventHandler;
    }

    public static EventHandler<ActionEvent> studentCancelBTN() {

        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/View/Students.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                pageTitle = PageLoader.getStudentsTitle();
                stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                PageLoader.pageLoad(stage, root, pageTitle);
            }
        };

        return eventHandler;
    }

    public static EventHandler<ActionEvent> studentAddClearBTN(
            TextField nameTF,
            ComboBox<Country> countryCB,
            ComboBox<Division> divisionCB,
            TextField postalTF,
            TextField addressTF,
            TextField phoneTF,
            TextField instrumentTF,
            ToggleGroup onlineTGL,
            RadioButton onlineNRB,
            ToggleGroup inPersonTGL,
            RadioButton inPersonNRB
    ) throws SQLException {

        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
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
            }
        };
        return eventHandler;
    }

    public static EventHandler<ActionEvent> studentAddSaveBTN(
            TextField nameTF,
            ComboBox<Country> countryCB,
            ComboBox<Division> divisionCB,
            TextField postalTF,
            TextField addressTF,
            TextField phoneTF,
            TextField instrumentTF,
            ToggleGroup onlineTGL,
            ToggleGroup inPersonTGL
    ) throws SQLException {

        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

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

            }
        };
        return eventHandler;
    }

    public static EventHandler<ActionEvent> studentUpdateSaveBTN(
            TextField idTF,
            TextField nameTF,
            ComboBox<Country> countryCB,
            ComboBox<Division> divisionCB,
            TextField postalTF,
            TextField addressTF,
            TextField phoneTF,
            TextField instrumentTF,
            ToggleGroup onlineTGL,
            ToggleGroup inPersonTGL) {
        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
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
                    InstrumentStudentDAO.updateStudent(id, name, country, division, postal, address, phone,
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
            }
        };
        return eventHandler;
    }

    public static EventHandler<Event> appointmentsAllTab(TableView<Appointment> allViewTable) {

        EventHandler<Event> eventHandler = new EventHandler<Event>() {
            public void handle(Event event) {
                try {
                    AppointmentDAO.selectAppointments();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                allViewTable.setItems(Data.getAllAppointments());
            }
        };

        return eventHandler;
    }

    public static EventHandler<Event> appointmentsMonthTab(TableView<Appointment> monthViewTable) {

        EventHandler<Event> eventHandler = new EventHandler<Event>() {
            public void handle(Event event) {
                Data.clearMonthlyAppointments();
                try {
                    AppointmentDAO.selectAppointments();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                Data.filterMonthlyAppointments();
                monthViewTable.setItems(Data.getMonthlyAppointments());
            }
        };

        return eventHandler;
    }

    public static EventHandler<Event> appointmentsWeekTab(TableView<Appointment> weekViewTable) {

        EventHandler<Event> eventHandler = new EventHandler<Event>() {
            public void handle(Event event) {
                Data.clearWeeklyAppointments();
                try {
                    AppointmentDAO.selectAppointments();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                Data.filterWeeklyAppointments();
                weekViewTable.setItems(Data.getWeeklyAppointments());
            }
        };

        return eventHandler;
    }

    public static EventHandler<Event> appointmentsDayTab(TableView<Appointment> dayViewTable) {

        EventHandler<Event> eventHandler = new EventHandler<Event>() {
            public void handle(Event event) {
                Data.clearDailyAppointments();
                try {
                    AppointmentDAO.selectAppointments();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                Data.filterDailyAppointments();
                dayViewTable.setItems(Data.getDailyAppointments());
            }
        };

        return eventHandler;
    }

    public static EventHandler<ActionEvent> appointmentsDeleteBTN(
            TableView<Appointment> allApptTable,
            TableView<Appointment> monthApptTable,
            TableView<Appointment> weekApptTable,
            TableView<Appointment> dayApptTable) {
        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TableView<Appointment> currentTable = dayApptTable;
                if (allApptTable.getSelectionModel().getSelectedItem() == null) {
                    if (monthApptTable.getSelectionModel().getSelectedItem() == null) {
                        if (weekApptTable.getSelectionModel().getSelectedItem() == null) {
                            if (dayApptTable.getSelectionModel().getSelectedItem() == null) {
                                Alerts.appointmentNullAlert();
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
        return eventHandler;
    }
    
    public static EventHandler<ActionEvent> appointmentCancelBTN() {

        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                pageTitle = PageLoader.getAppointmentsTitle();
                stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
                PageLoader.pageLoad(stage, root, pageTitle);
            }
        };

        return eventHandler;
    }

    public static EventHandler<ActionEvent> appointmentAddClearBTN(
            TextField titleTF, 
            TextArea descriptionTA, 
            ComboBox<String> locationCB, 
            DatePicker dateDP, 
            ComboBox<LocalTime> timeCB, 
            ComboBox<String> lengthCB, 
            ComboBox<InstrumentTeacher> teacherCB,
            ComboBox<InstrumentStudent> studentCB
            ) throws SQLException {

        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                titleTF.setText("");
                descriptionTA.setText("");
                locationCB.getSelectionModel().clearSelection();
                dateDP.setValue(null);
                timeCB.getSelectionModel().clearSelection();
                lengthCB.getSelectionModel().clearSelection();
                teacherCB.getSelectionModel().clearSelection();
                studentCB.getSelectionModel().clearSelection();
                
            }
        };
        return eventHandler;
    }

//    public static EventHandler<ActionEvent> appointmentAddSaveBTN(
//            TextField nameTF,
//            ComboBox<Country> countryCB,
//            ComboBox<Division> divisionCB,
//            TextField postalTF,
//            TextField addressTF,
//            TextField phoneTF,
//            TextField instrumentTF,
//            ToggleGroup onlineTGL,
//            ToggleGroup inPersonTGL
//    ) throws SQLException {
//
//        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent event) {
//
//                String name = nameTF.getText();
//                String postal = postalTF.getText();
//                String address = addressTF.getText();
//                String phone = phoneTF.getText();
//                String instrument = instrumentTF.getText();
//                RadioButton onlineRadio = (RadioButton) onlineTGL.getSelectedToggle();
//                char onlineRadioChar = onlineRadio.getText().charAt(0);
//                RadioButton inPersonRadio = (RadioButton) inPersonTGL.getSelectedToggle();
//                char inPersonRadioChar = inPersonRadio.getText().charAt(0);
//
//                if (StringUtils.isEmptyOrWhitespaceOnly(name)
//                        || StringUtils.isEmptyOrWhitespaceOnly(postal)
//                        || StringUtils.isEmptyOrWhitespaceOnly(address)
//                        || StringUtils.isEmptyOrWhitespaceOnly(phone)
//                        || StringUtils.isEmptyOrWhitespaceOnly(instrument)) {
//                    Alerts.invalidFields();
//                    return;
//                }
//
//                try {
//                    String country = countryCB.getSelectionModel().getSelectedItem().getCountryName();
//                    String division = divisionCB.getSelectionModel().getSelectedItem().getDivisionName();
//                    InstrumentStudentDAO.insertStudent(name, country, division, postal, address, phone,
//                            instrument, onlineRadioChar, inPersonRadioChar);
//                    try {
//                        root = FXMLLoader.load(getClass().getResource("/View/Students.fxml"));
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                    pageTitle = PageLoader.getStudentsTitle();
//                    stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
//                    PageLoader.pageLoad(stage, root, pageTitle);
//                } catch (NullPointerException ex) {
//                    Alerts.countryOrDivisionNullAlert();
//                }
//
//            }
//        };
//        return eventHandler;
//    }

}

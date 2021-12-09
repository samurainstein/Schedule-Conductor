/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import DAO.AppointmentDAO;
import DAO.InstrumentTeacherDAO;
import Model.Data;
import Model.InstrumentTeacher;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    
    public static EventHandler<ActionEvent> teachersDeleteEvent(TableView<InstrumentTeacher> teachersTable) throws SQLException {
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
                    boolean checkAssociated = Data.checkAssociatedAppointments(id);
                    if (checkAssociated == true) {
                        Alerts.associatedAppointment();
                    } else {

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this customer?");
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
}
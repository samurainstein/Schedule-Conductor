/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.InstrumentTeacherDAO;
import Model.Data;
import Utilities.Alerts;
import Utilities.EventHandlerNavMenu;
import Utilities.PageLoader;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * This class handles events on the Login screen
 *
 * @author Eric Matelyan
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usernameTF;
    @FXML
    private Button loginBT;
    @FXML
    private Label zoneIDLabel;

    private Parent root;
    private String pageTitle;
    private ZoneId zoneID;
    private Stage stage;
    @FXML
    private PasswordField passwordFld;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Data.clearLoggedInTeacherId();
        pageTitle = PageLoader.getHomeTitle();
        zoneID = ZoneId.systemDefault();
        zoneIDLabel.setText("Location: " + zoneID.toString());

        EventHandler<KeyEvent> pressEnterHandler = (KeyEvent keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                String username = usernameTF.getText();
                String password = passwordFld.getText();
                int id = InstrumentTeacherDAO.teacherLogin(username, password);
                if (id == 0) {
                    alertInvalidLogin();
                } else {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Data.setLoggedInTeacherID(id);
                    pageTitle = PageLoader.getHomeTitle();
                    stage = (Stage) ((Node) keyEvent.getTarget()).getScene().getWindow();
                    PageLoader.pageLoad(stage, root, pageTitle);
                }
            }
        };

        EventHandler<ActionEvent> clickLoginBtnHandler = (ActionEvent event) -> {
            String username = usernameTF.getText();
            String password = passwordFld.getText();
            int id = InstrumentTeacherDAO.teacherLogin(username, password);
            if (id == 0) {
                alertInvalidLogin();
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
        };
        
        usernameTF.setOnKeyPressed(pressEnterHandler);
        passwordFld.setOnKeyPressed(pressEnterHandler);
        loginBT.setOnKeyPressed(pressEnterHandler);
        loginBT.setOnAction(clickLoginBtnHandler);
    }
    
    /**
     * Method for generating an alert to indicate an invalid login username or password. 
     */
    public static void alertInvalidLogin() {
        String alertText = "Username or password is incorrect";
        String alertTitle = "Invalid username or password";
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(alertTitle);
        alert.setContentText(alertText);
        alert.showAndWait();
    }
}

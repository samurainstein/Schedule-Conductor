/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.InstrumentTeacherDAO;
import Model.Data;
//import Utilities.ActivityLog;
import Utilities.Alerts;
import Utilities.PageLoader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * This class handles events on the Login screen
 *
 * @author Eric Matelyan
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private Button loginBT;
    @FXML
    private Label zoneIDLabel;

    private Parent root;
    private String pageTitle;
    private String alertTitle;
    private String alertText;
    private ZoneId zoneID;
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            root = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
        alertText = "Username or password is incorrect";
        alertTitle = "Invalid username or password";
        pageTitle = PageLoader.getHomeTitle();
        zoneID = ZoneId.systemDefault();
        zoneIDLabel.setText("Location: " + zoneID.toString());
        usernameTF.setOnKeyPressed(keyPressHandler);
        passwordTF.setOnKeyPressed(keyPressHandler);
        loginBT.setOnKeyPressed(keyPressHandler);
        loginBT.setOnAction(actionEventHandler);
    }

//    @FXML
//    private void onLogin(ActionEvent event) throws SQLException, IOException {
//        String username = usernameTF.getText();
//        String password = passwordTF.getText();
//        int id = InstrumentTeacherDAO.teacherLogin(username, password);
//        if (id == 0) {
//            Alerts.loginInvalid(alertTitle, alertText);
////            ActivityLog.loginAttempt(userID, username);
//        } else {
//            System.out.println("Login Successful");
////            ActivityLog.loginAttempt(userID, username);
////            Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
////            String pageTitle = PageLoader.getHomeTitle();
////            PageLoader.pageLoad(event, root, pageTitle);  
//        }
//    }
    
    EventHandler<KeyEvent> keyPressHandler = new EventHandler<KeyEvent>() {
        public void handle(KeyEvent event) {
            if(event.getCode() == KeyCode.ENTER ) {
                String username = usernameTF.getText();
                String password = passwordTF.getText();
                int id = InstrumentTeacherDAO.teacherLogin(username, password);
                if (id == 0) {
                    Alerts.loginInvalid(alertTitle, alertText);
                } else {
                    try {
                        root = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Data.setLoggedInTeacherID(id);
                    stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
                    PageLoader.pageLoad(stage, root, pageTitle);
                    System.out.println("Login Successful");
                }
            }
        }
    };
    
    EventHandler<ActionEvent> actionEventHandler = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {
            String username = usernameTF.getText();
            String password = passwordTF.getText();
            int id = InstrumentTeacherDAO.teacherLogin(username, password);
            if (id == 0) {
                    Alerts.loginInvalid(alertTitle, alertText);
            } 
            else {
                try {
                    root = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Data.setLoggedInTeacherID(id);
                stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
                PageLoader.pageLoad(stage, root, pageTitle);
                System.out.println("Login Successful");
            }
        }             
    };
}

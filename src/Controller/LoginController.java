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
import Utilities.EventHandle;
import Utilities.PageLoader;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private TextField passwordTF;
    @FXML
    private Button loginBT;
    @FXML
    private Label zoneIDLabel;

    private Parent root;
    private String pageTitle;
    private ZoneId zoneID;
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Data.clearLoggedInTeacherId();
        pageTitle = PageLoader.getHomeTitle();
        zoneID = ZoneId.systemDefault();
        zoneIDLabel.setText("Location: " + zoneID.toString());
        
        usernameTF.setOnKeyPressed(EventHandle.loginKeyEvent(usernameTF, passwordTF));
        passwordTF.setOnKeyPressed(EventHandle.loginKeyEvent(usernameTF, passwordTF));
        loginBT.setOnKeyPressed(EventHandle.loginKeyEvent(usernameTF, passwordTF));
        loginBT.setOnAction(EventHandle.loginActionEvent(usernameTF, passwordTF));
    }
    
//    EventHandler<KeyEvent> keyPressHandler = new EventHandler<KeyEvent>() {
//        public void handle(KeyEvent event) {
//            if(event.getCode() == KeyCode.ENTER ) {
//                String username = usernameTF.getText();
//                String password = passwordTF.getText();
//                int id = InstrumentTeacherDAO.teacherLogin(username, password);
//                if (id == 0) {
//                    Alerts.loginInvalid();
//                } else {
//                    try {
//                        root = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                    Data.setLoggedInTeacherID(id);
//                    stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
//                    PageLoader.pageLoad(stage, root, pageTitle);
//                }
//            }
//        }
//    };
    
//    EventHandler<ActionEvent> actionEventHandler = new EventHandler<ActionEvent>() {
//        public void handle(ActionEvent event) {
//            String username = usernameTF.getText();
//            String password = passwordTF.getText();
//            int id = InstrumentTeacherDAO.teacherLogin(username, password);
//            if (id == 0) {
//                    Alerts.loginInvalid();
//            } 
//            else {
//                try {
//                    root = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//                Data.setLoggedInTeacherID(id);
//                stage = (Stage)((Node)event.getTarget()).getScene().getWindow();
//                PageLoader.pageLoad(stage, root, pageTitle);
//            }
//        }             
//    };
}

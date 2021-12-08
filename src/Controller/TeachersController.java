/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Utilities.EventHandle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class TeachersController implements Initializable {

    @FXML
    private Label teachersLBL;
    @FXML
    private Label logoutLabel;
    @FXML
    private TableColumn<?, ?> idCol;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> country;
    @FXML
    private TableColumn<?, ?> division;
    @FXML
    private TableColumn<?, ?> postalCode;
    @FXML
    private TableColumn<?, ?> address;
    @FXML
    private TableColumn<?, ?> phone;
    @FXML
    private TableColumn<?, ?> instrument;
    @FXML
    private TableColumn<?, ?> availableOnline;
    @FXML
    private TableColumn<?, ?> availableInPerson;
    @FXML
    private Label homeLBL;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homeLBL.setOnMouseClicked(EventHandle.navHomeEvent());
        logoutLabel.setOnMouseClicked(EventHandle.navLogoutEvent());
        teachersLBL.setOnMouseClicked(EventHandle.navTeachersEvent());
    }    
    
}

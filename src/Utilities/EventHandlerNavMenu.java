/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Eric
 */
public abstract class EventHandlerNavMenu {

    private static Parent root;
    private static Stage stage;
    private static String pageTitle;

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
    
    public static EventHandler<MouseEvent> navReportsEvent() {

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
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
}

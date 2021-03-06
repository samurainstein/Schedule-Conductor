/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Controller.UpdateAppointmentController;
import Controller.UpdateStudentController;
import Controller.UpdateTeacherController;
import Model.Appointment;
import Model.InstrumentStudent;
import Model.InstrumentTeacher;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is used to load pages while the application is running. 
 * @author Eric
 */
public abstract class PageLoader {
    
    private static String loginTitle = "Login";
    private static String homeTitle = "Home";
    private static String teachersTitle = "Teachers";
    private static String studentsTitle = "Students";
    private static String appointmentsTitle = "Appointments";
    private static String teacherAddTitle = "Add Teacher";
    private static String studentAddTitle = "Add Student";
    private static String appointmentAddTitle = "Add Appointment";
    private static String teacherUpdateTitle = "Update Teacher";
    private static String studentUpdateTitle = "Update Student";
    private static String appointmentUpdateTitle = "Update Appointment";
    private static String reportsTitle = "Reports";
    
    /**
     * This method loads an application page, based on the passed in event, root, and page title. 
     * @param stage Stage object,passed from screen where event occurred
     * @param root The root of the page to be loaded
     * @param pageTitle The title of the page that will be set to the new screen
     */
    public static void pageLoad(Stage stage, Parent root, String pageTitle) {
        Scene scene = new Scene(root);
        scene.getStylesheets().add("View/ScheduleConductor.css");
        stage.setTitle(pageTitle);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * This method loads the teacher update page, and passes data from the main teachers page. 
     * @param event Event object that was generated from an event in the application
     * @param loader The loader object of the page to be loaded
     * @param pageTitle The title of the page that will be set to the new screen
     * @param selectedTeacher The teacher that is being passed to the update page
     */
    public static void teachUpdatePageLoad(ActionEvent event, FXMLLoader loader, String pageTitle, InstrumentTeacher selectedTeacher) throws IOException {
        Parent root = loader.load();
        UpdateTeacherController updateCont = loader.getController();
        updateCont.passTeacherData(selectedTeacher);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("View/ScheduleConductor.css");
        stage.setTitle(pageTitle);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * This method loads the student update page, and passes data from the main students page. 
     * @param event Event object that was generated from an event in the application
     * @param loader The loader object of the page to be loaded
     * @param pageTitle The title of the page that will be set to the new screen
     * @param selectedStudent The student that is being passed to the update page
     */
    public static void studentUpdatePageLoad(ActionEvent event, FXMLLoader loader, String pageTitle, InstrumentStudent selectedStudent) throws IOException {
        Parent root = loader.load();
        UpdateStudentController updateCont = loader.getController();
        updateCont.passStudentData(selectedStudent);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("View/ScheduleConductor.css");
        stage.setTitle(pageTitle);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * This method loads the appointment update page, and passes data from the main appointments page. 
     * @param event Event object that was generated from an event in the application
     * @param loader The loader object of the page to be loaded
     * @param pageTitle The title of the page that will be set to the new screen
     * @param selectedAppointment The appointment that is being passed to the update page
     */
    public static void appointmentUpdatePageLoad(ActionEvent event, FXMLLoader loader, String pageTitle, Appointment selectedAppointment) throws IOException {
        Parent root = loader.load();
        UpdateAppointmentController updateCont = loader.getController();
        updateCont.passAppointmentData(selectedAppointment);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("View/ScheduleConductor.css");
        stage.setTitle(pageTitle);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Method for returning the login page title. 
     * @return Returns the login page title
     */
    public static String getLoginTitle() {
        return loginTitle;
    }
    
    /**
     * Method for returning the home page title. 
     * @return Returns the home page title
     */
    public static String getHomeTitle() {
        return homeTitle;
    }
    
    /**
     * Method for returning the teachers page title. 
     * @return Returns the teachers page title
     */
    public static String getTeachersTitle() {
        return teachersTitle;
    }
    
    /**
     * Method for returning the add teacher page title. 
     * @return Returns the add teacher page title
     */
    public static String getTeacherAddTitle() {
        return teacherAddTitle;
    }
    
    /**
     * Method for returning the students page title. 
     * @return Returns the students page title
     */
    public static String getStudentsTitle() {
        return studentsTitle;
    }
    
    /**
     * Method for returning the add student page title. 
     * @return Returns the add student page title
     */
    public static String getStudentAddTitle() {
        return studentAddTitle;
    }
    
    /**
     * Method for returning the appointments page title. 
     * @return Returns the appointments page title
     */
    public static String getAppointmentsTitle() {
        return appointmentsTitle;
    }
    
    
    
    /**
     * Method for returning the add appointments page title. 
     * @return Returns the add appointments page title
     */
    public static String getAppointmentAddTitle() {
        return appointmentAddTitle;
    }
    
    /**
     * Method for returning the update teacher page title. 
     * @return Returns the update teachers page title
     */
    public static String getTeacherUpdateTitle() {
        return teacherUpdateTitle;
    }
    
    /**
     * Method for returning the update student page title. 
     * @return Returns the update student page title
     */
    public static String getStudentUpdateTitle() {
        return studentUpdateTitle;
    }
    
    /**
     * Method for returning the update appointments page title. 
     * @return Returns the update appointments page title
     */
    public static String getAppointmentUpdateTitle() {
        return appointmentUpdateTitle;
    }
    
    /**
     * Method for returning the reports page title. 
     * @return Returns the reports page title
     */
    public static String getReportsTitle() {
        return reportsTitle;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import javafx.scene.control.Alert;

/**
 * This class is used to define various alerts that are generated in the program. 
 * @author Eric Matelyan
 */
public abstract class Alerts {
    
    private static String alertText;
    private static String alertTitle;
    
    /**
     * Method for generating an alert to indicate an invalid login username or password. 
     */
    public static void loginInvalid() {
        alertText = "Username or password is incorrect";
        alertTitle = "Invalid username or password";
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(alertTitle);
        alert.setContentText(alertText);
        alert.showAndWait();
    }
    
//    /**
//     * Method for generating an alert to indicate an invalid appointment selection. 
//     */
//    public static void appointmentNullAlert() {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Invalid Selection");
//        alert.setContentText("Please select an appointment");
//        alert.showAndWait();
//    }
//    
//    /**
//     * Method for generating an alert to confirm that an appointment was deleted. 
//     * @param appointmentID ID of appointment that was deleted
//     * @param type Type of appointment that was deleted
//     */
//    public static void appointmentDeleteConfirm(int appointmentID, String type) {
//        Alert confirmAlert = new Alert(Alert.AlertType.INFORMATION);
//            confirmAlert.setTitle("Confirmation");
//            confirmAlert.setContentText("Appointment was deleted\n" + "Appointment ID: " + appointmentID + "\n" + "Appointment Type: " + type);
//            confirmAlert.showAndWait();
//    }
//    
//    /**
//     * Method for generating an alert to indicate that the entered appointment conflicts with another appointment. 
//     */
//    public static void appointmentOverlap() {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Appointment overlap");
//        alert.setContentText("Selected time overlaps with another appointment.  Please select a different time");
//        alert.showAndWait();
//    }
//    
    /**
     * Method for generating an alert to indicate that associated appointments should be deleted before deleting a teacher. 
     */
    public static void associatedAppointment() {
        alertText = "Please delete all appointments associated with this teacher before deleting the teacher";
        alertTitle = "Associated Appointment";
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(alertTitle);
        alert.setContentText(alertText);
        alert.showAndWait();
    }
//    
//    /**
//     * Method for generating an alert to indicate an upcoming appointment in the next 15 minutes. 
//     */
//    public static void appointmentUpcomingAlert(Appointment appointment) {
//        int appointmentID = appointment.getAppointmentID();
//        LocalTime appointmentTime = appointment.getStart().toLocalTime();
//        LocalDate appointmentDate = appointment.getStart().toLocalDate();
//        String popupTitle = "Appointment Notification";
//        String popupText = "You have an appointment in the next 15 minutes.\n" +
//                            "Appointment ID: " + appointmentID + "\n" + 
//                            "Date: " + appointmentDate + "\n" +
//                            "Time: " + appointmentTime;
//        Alert popup = new Alert(Alert.AlertType.INFORMATION);
//        popup.setTitle(popupTitle);
//        popup.setContentText(popupText);
//        popup.showAndWait();
//    }
//    
    /**
     * Method for generating an alert to indicate an invalid customer selection. 
     */
    public static void teacherDeleteNull() {
        alertTitle = "Invalid Selection";
        alertText = "Please select a teacher";
        Alert invalidAlert = new Alert(Alert.AlertType.ERROR);
        invalidAlert.setTitle(alertTitle);
        invalidAlert.setContentText(alertText);
        invalidAlert.showAndWait();
    }
    
    /**
     * Method for generating an alert to confirm that a teacher was deleted. 
     */
    public static void teacherDeleteConfirm() {
        alertTitle = "Confirmation";
        alertText = "Teacher was deleted";
        Alert confirmAlert = new Alert(Alert.AlertType.INFORMATION);
        confirmAlert.setTitle(alertTitle);
        confirmAlert.setContentText(alertText);
        confirmAlert.showAndWait();
    }
    
    /**
     * Method for generating an alert to indicate an invalid country or division selection. 
     */
    public static void countryOrDivisionNullAlert() {
        alertTitle = "Invalid Selection";
        alertText = "Please select a country and division";
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(alertTitle);
        alert.setContentText(alertText);
        alert.showAndWait();
    }
    
    /**
     * Method for generating an alert to indicate all fields weren't filled in. 
     */
    public static void invalidFields() {
        alertTitle = "Insufficient Information";
        alertText = "Please fill in all fields";
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(alertTitle);
        alert.setContentText(alertText);
        alert.showAndWait();
    }
    
}

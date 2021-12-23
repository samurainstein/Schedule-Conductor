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
     * Method for generating an alert to indicate that the entered appointment conflicts with another appointment. 
     */
    public static void appointmentOverlap() {
        alertText = "Selected time overlaps with another appointment.  Please select a different time";
        alertTitle = "Appointment overlap";
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(alertTitle);
        alert.setContentText(alertText);
        alert.showAndWait();
    }
    
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

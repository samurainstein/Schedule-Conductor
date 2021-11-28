/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDateTime;

/**
 * This class defines the attributes and methods of Appointment objects
 * @author Eric Matelyan
 */
public class Appointment {
    
    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private LocalDateTime start;
    private LocalDateTime end;
    private int studentId;
    private int teacherId;
    
    /**
     * Constructor for Appointment objects. 
     * 
     * @param appointmentID ID number of appointment
     * @param title Title of appointment
     * @param description Description of appointment
     * @param location Location of appointment
     * @param start Start date and time of appointment
     * @param end End date and time of appointment
     * @param studentId student ID associated with appointment
     * @param teacherId teacher ID associated with appointment
     */
    public Appointment(int appointmentID, String title, String description,String location, 
            LocalDateTime start, LocalDateTime end, int studentId, int teacherId) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.start = start;
        this.end = end;
        this.studentId = studentId;
        this.teacherId = teacherId;
    }
    
    /**
     * Method for getting an appointment ID. 
     * @return Returns an appointment ID
     */
    public int getAppointmentID() {
        return appointmentID;
    }
    
    /**
     * Method for getting the title of an appointment. 
     * @return Returns a title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Method for getting the description of an appointment. 
     * @return Returns a description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Method for getting the location of an appointment. 
     * @return Returns a location
     */
    public String getLocation() {
        return location;
    }
    
    /**
     * Method for getting the start date/time of an appointment. 
     * @return Returns a start date/time
     */
    public LocalDateTime getStart() {
        return start;
    }
    
    /**
     * Method for getting the end date/time of an appointment. 
     * @return Returns an end date/time
     */
    public LocalDateTime getEnd() {
        return end;
    }
    
    /**
     * Method for getting the associated student ID of an appointment. 
     * @return Returns a student ID
     */
    public int getStudentId() {
        return studentId;
    }
    
    /**
     * Method for getting the associated teacher ID of an appointment. 
     * @return Returns a teacher ID
     */
    public int getTeacherId() {
        return teacherId;
    }
    
}

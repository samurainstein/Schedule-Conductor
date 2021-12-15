/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.InstrumentStudentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class contains various data that is pulled from the database or individually defined, 
 * as well as methods for retrieving and manipulating the data. 
 * @author Eric Matelyan
 */
public abstract class Data {
    
    private static ObservableList<InstrumentStudent> allStudents = FXCollections.observableArrayList();
    private static ObservableList<InstrumentTeacher> allTeachers = FXCollections.observableArrayList();
    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();
    private static ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    private static ObservableList<Division> filteredDivisions = FXCollections.observableArrayList();
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> weeklyAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> monthlyAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> teacherAppointments = FXCollections.observableArrayList();
    private static int loggedInTeacherId;
    
    /**
     * Method for returning a list of all students. 
     * @return Returns an observable list of all students
     */
    public static ObservableList<InstrumentStudent> getAllStudents() {
        return allStudents;
    }
    
    /**
     * Method for adding a student to the list of all students. 
     * @param student Student to be added
     */
    public static void addStudent(InstrumentStudent student) {
        allStudents.add(student);
    }
     
    /**
     * Method for clearing the the list of all students. 
     */
    public static void clearStudents() { 
        allStudents.clear();
    }
    
    public static String getStudentName(int studentId) {
        clearStudents();
        InstrumentStudentDAO.selectStudents();
        for(Student student: allStudents) {
            if(student.getId() == studentId) {
                return student.getName();
            }
        }
        return null;
    }
    
    /**
     * Method for returning a list of all teachers. 
     * @return Returns an observable list of all teachers
     */
    public static ObservableList<InstrumentTeacher> getAllTeachers() {
        return allTeachers;
    }
    
    /**
     * Method for adding a teacher to the list of all teachers. 
     * @param teacher Teacher to be added
     */
    public static void addTeacher(InstrumentTeacher teacher) {
        allTeachers.add(teacher);
    }
     
    /**
     * Method for clearing the the list of all teachers. 
     */
    public static void clearTeachers() { 
        allTeachers.clear();
    }
    
    /**
     * Method for returning a list of all countries. 
     * @return Returns an observable list of all countries
     */
    public static ObservableList<Country> getAllCountries() {
        return allCountries;
    }
    
    /**
     * Method for adding a country to the list of all countries. 
     * @param country Country to be added
     */
    public static void addCountry(Country country) {
        allCountries.add(country);
    }
    
    /**
     * Method for clearing the the list of all countries. 
     */
    public static void clearCountries() { //add to uml
        allCountries.clear();
    }
    
    /**
     * Method for returning a list of all divisions. 
     * @return Returns an observable list of all divisions
     */
    public static ObservableList<Division> getAllDivisions() {
        return allDivisions;
    }
    
    /**
     * Method for adding a division to the list of all divisions. 
     * @param division Division to be added
     */
    public static void addDivision(Division division) {
        allDivisions.add(division);
    }
    
    /**
     * Method for returning a list of country specific divisions. 
     * @return Returns an observable list of country specific divisions
     */
    public static ObservableList<Division> getFilteredDivisions() {
        return filteredDivisions;
    }
    
    /**
     * Method for adding a division to the list of country specific divisions. 
     * @param division Division to be added
     */
    public static void addFilteredDivision(Division division) {
        filteredDivisions.add(division);
    }
       
    /**
     * Method for clearing the list of country specific divisions. 
     */
    public static void clearFilteredDivisions() { 
        filteredDivisions.clear();
    }
    
    /**
     * Method for clearing the list of all divisions. 
     */
    public static void clearDivisions() {
        allDivisions.clear();
    }
    
    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }
    
    /**
     * Method for adding an appointment to the list of all appointments. 
     * @param appointment Appointment to be added
     */
    public static void addAppointment(Appointment appointment) {
        allAppointments.add(appointment);        
    }
    
    /**
     * Method for clearing the list of all appointments. 
     */
    public static void clearAppointments() {
        allAppointments.clear();
    }
    
    /**
     * Method for returning a list of appointments in the current week. 
     * @return Returns an observable list of appointments in the current week
     */
    public static ObservableList<Appointment> getWeeklyAppointments() {
        return weeklyAppointments;
    }
    
    /**
     * Method for clearing the list of appointments in the current week. 
     */
    public static void clearWeeklyAppointments() {
        weeklyAppointments.clear();
    }
    
    /**
     * Method for returning a list of appointments in the current month. 
     * @return Returns an observable list of appointments in the current month
     */
    public static ObservableList<Appointment> getMonthlyAppointments() {
        return monthlyAppointments;
    }
    
    /**
     * Method for clearing the list of appointments in the current month. 
     */
    public static void clearMonthlyAppointments() {
        monthlyAppointments.clear();
    }
    
    /**
     * Method for returning a list of appointments that are associated with a specific teacher ID. 
     * @param teacherId Teacher ID to be searched
     * @return Returns an observable list of appointments
     */
    public static ObservableList<Appointment> getTeacherAppointments(int teacherId) {
        for(Appointment appointment : allAppointments) {
            if(appointment.getTeacherId() == teacherId)
                teacherAppointments.add(appointment);
        }
        return teacherAppointments;
    }
    
    /**
     * Method for clearing the list of teacher specific appointments. 
     */
    public static void clearTeacherAppointments() {
        teacherAppointments.clear();
    }
    
    /**
     * Method for checking if any appointments are associated with a specific teacher ID. 
     * @param teacherId Teacher ID to check
     * @return Returns a boolean of whether any appointments are associated with the teacher ID
     */
    public static boolean checkTeachAssocAppt(int teacherId) {
        boolean associated = false;
        for(Appointment appointment : allAppointments) {
            if(associated == true) {
                break;
            }
            if(appointment.getTeacherId()== teacherId) {
                associated = true;
            }
        }
        
        return associated;
    }
    
    public static boolean checkStudAssocAppt(int studentId) {
        boolean associated = false;
        for(Appointment appointment : allAppointments) {
            if(associated == true) {
                break;
            }
            if(appointment.getStudentId()== studentId) {
                associated = true;
            }
        }
        
        return associated;
    }
    
    /**
     * Method for setting the currently logged in teacher ID. 
     * @param teacherId Teacher ID to set
     */
    public static void setLoggedInTeacherID(int teacherId) {
        loggedInTeacherId = teacherId;
    }
    
    /**
     * Method for returning the currently logged in teacher ID. 
     * @return Returns a teacher ID
     */
    public static int getLoggedInTeacherId() {
        return loggedInTeacherId;
    }
    
    public static void clearLoggedInTeacherId() {
        loggedInTeacherId = 0;
    }
}

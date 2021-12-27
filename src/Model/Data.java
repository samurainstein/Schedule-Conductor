/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.InstrumentStudentDAO;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
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
    private static ObservableList<Appointment> dailyAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> teacherAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> studentAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> appointmentsByTeacher = FXCollections.observableArrayList();
    private static ObservableList<ZonedDateTime> zonedAppointmentTimes = FXCollections.observableArrayList();
    private static ObservableList<LocalTime> appointmentTimes = FXCollections.observableArrayList();
    private static ObservableList<String> locations = FXCollections.observableArrayList();
    private static ObservableList<String> lengths = FXCollections.observableArrayList();
    private static ObservableList<String> instruments = FXCollections.observableArrayList();
    private static ObservableList<InstrumentStudent> instrumentStudents = FXCollections.observableArrayList();
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
     * Method for clearing the list of all divisions. 
     */
    public static void clearDivisions() {
        allDivisions.clear();
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
     * Method for filtering all appointments in the current day. 
     */
    public static void filterDailyAppointments() {
        LocalDate today = LocalDate.now();
        for(Appointment appointment : allAppointments) {
            if(appointment.getStart().toLocalDate().equals(today))
                dailyAppointments.add(appointment);
        }
    }
    
    /**
     * Method for returning a list of appointments in the current day. 
     * @return Returns an observable list of appointments in the current day
     */
    public static ObservableList<Appointment> getDailyAppointments() {
        return dailyAppointments;
    }
    
    /**
     * Method for clearing the list of appointments in the current day. 
     */
    public static void clearDailyAppointments() {
        dailyAppointments.clear();
    }
    
    /**
     * Method for filtering all appointments in the current week. 
     */
    public static void filterWeeklyAppointments() {
        LocalDate startDate = LocalDate.now(); 
        DayOfWeek dayOfWeek = startDate.getDayOfWeek();
        switch (dayOfWeek) {
            case MONDAY:
                break;
            case TUESDAY:
                startDate = startDate.minusDays(1);
                break;
            case WEDNESDAY:
                startDate = startDate.minusDays(2);
                break;
            case THURSDAY:
                startDate = startDate.minusDays(3);
                break;
            case FRIDAY:
                startDate = startDate.minusDays(4);
                break;
            case SATURDAY:
                startDate = startDate.minusDays(5);
                break;
            case SUNDAY:
                startDate = startDate.minusDays(6);
                break;
            default:
                break;
        }
        
        LocalDate endDate = startDate.plusDays(6);
        for(Appointment appointment : allAppointments) {
            LocalDate appointmentDate = appointment.getStart().toLocalDate();
            if((appointmentDate.isEqual(startDate) || appointmentDate.isAfter(startDate)) && 
                (appointmentDate.isEqual(endDate) || appointmentDate.isBefore(endDate)))
                weeklyAppointments.add(appointment);
                }
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
     * Method for filtering all appointments in the current month. 
     */
    public static void filterMonthlyAppointments() {
        LocalDate today = LocalDate.now();
        int monthOfYear = today.getMonthValue();
        for(Appointment appointment : allAppointments) {
            if(appointment.getStart().getMonthValue() == monthOfYear)
                monthlyAppointments.add(appointment);
        }
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
     * Method for returning a list of all EST business hour appointment times. 
     * @return Returns an observable list of EST business hour appointment times
     */
    public static ObservableList<ZonedDateTime> getAllAppointmentTimes() {
        return zonedAppointmentTimes;
    }
    
    /**
     * Method for adding an appointment time to the list of appointment times. 
     * @param appointmentTime Appointment time to be added
     */
    public static void addAppointmentTime(ZonedDateTime appointmentTime) {
        zonedAppointmentTimes.add(appointmentTime);
    }
    
    /**
     * Method for clearing the list of appointment times.  
     */
    public static void clearAppointmentTimes() {
        zonedAppointmentTimes.clear();
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
    
    public static ObservableList<String> getLocations() {
        if(locations.size() == 0) {
            locations.add("In-Studio");
            locations.add("In-Home");
            locations.add("Online");
        }
        return locations;
    }
    
    public static ObservableList<String> getLengths() {
        if(lengths.size() == 0) {
            lengths.add("30 Minutes");
            lengths.add("1 Hour");
            lengths.add("1 Hour 30 Minutes");
            lengths.add("2 Hours");
        }
        return lengths;
    }
    
    public static boolean checkTeacherOverlap(int teacherID, LocalTime startTime, LocalTime endTime, LocalDate startDate) {
        boolean overlap = false;
        teacherAppointments.clear();
        for(Appointment appointment : allAppointments) {
            if(appointment.getTeacherId() == teacherID) {
                teacherAppointments.add(appointment);
            }
        }
        for(Appointment appointment : teacherAppointments) {
            if(overlap == true) {
                break;
            }
            LocalTime teachStartTime = appointment.getStart().toLocalTime();
            LocalTime teachEndTime = appointment.getEnd().toLocalTime();
            LocalDate teachStartDate = appointment.getStart().toLocalDate();
            if(teachStartDate.equals(startDate)) {
                System.out.println("dates match");
                if(startTime.equals(teachStartTime)  || endTime.equals(teachEndTime)) {
                    System.out.println("if(startTime.equals(custStartTime)  || endTime.equals(custEndTime)) OVERLAP!");
                    overlap = true;  
                }
                else if(startTime.isAfter(teachStartTime) && startTime.isBefore(teachEndTime)) /*&& endTime.equals(custEndTime))*/  {
                    System.out.println("if(startTime.isAfter(custStartTime) && startTime.isBefore(custEndTime)) OVERLAP!");
                    overlap = true;
                }
                else if(endTime.isAfter(teachStartTime) && endTime.isBefore(teachEndTime)) {
                    System.out.println("if(endTime.isAfter(custStartTime) && endTime.isBefore(custEndTime)) OVERLAP!");
                    overlap = true;
                }
                else {
                    System.out.println("NO OVERLAP!"); 
                    overlap = false;
                }
            }
        }             
        return overlap;
    }
    
    public static boolean checkStudentOverlap(int studentID, LocalTime startTime, LocalTime endTime, LocalDate startDate) {
        boolean overlap = false;
        studentAppointments.clear();
        for(Appointment appointment : allAppointments) {
            if(appointment.getStudentId() == studentID) {
                studentAppointments.add(appointment);
            }
        }
        for(Appointment appointment : studentAppointments) {
            if(overlap == true) {
                break;
            }
            LocalTime studentStartTime = appointment.getStart().toLocalTime();
            LocalTime studentEndTime = appointment.getEnd().toLocalTime();
            LocalDate studentStartDate = appointment.getStart().toLocalDate();
            if(studentStartDate.equals(startDate)) {
                System.out.println("dates match");
                if(startTime.equals(studentStartTime)  || endTime.equals(studentEndTime)) {
                    System.out.println("if(startTime.equals(custStartTime)  || endTime.equals(custEndTime)) OVERLAP!");
                    overlap = true;  
                }
                else if(startTime.isAfter(studentStartTime) && startTime.isBefore(studentEndTime)) /*&& endTime.equals(custEndTime))*/  {
                    System.out.println("if(startTime.isAfter(custStartTime) && startTime.isBefore(custEndTime)) OVERLAP!");
                    overlap = true;
                }
                else if(endTime.isAfter(studentStartTime) && endTime.isBefore(studentEndTime)) {
                    System.out.println("if(endTime.isAfter(custStartTime) && endTime.isBefore(custEndTime)) OVERLAP!");
                    overlap = true;
                }
                else {
                    System.out.println("NO OVERLAP!"); 
                    overlap = false;
                }
            }
        }             
        return overlap;
    }
    
    /**
     * Method for returning a instrumentTeacher object from the list of all teachers by teacher ID. 
     * @param teacherID Teacher ID to be searched
     * @return Returns a teacher object
     */
    public static InstrumentTeacher getTeacherObject(int teacherID) {
        InstrumentTeacher teacherObject = null;
        for(InstrumentTeacher teacher : allTeachers) {
            if(teacher.getId() == teacherID) {
                teacherObject = teacher;
                break;
            }
        }
        return teacherObject;
    }
    
    /**
     * Method for returning a InstrumentStudent object from the list of all students by student ID. 
     * @param studentID Student ID to be searched
     * @return Returns a student object
     */
    public static InstrumentStudent getStudentObject(int studentID) {
        InstrumentStudent studentObject = null;
        for(InstrumentStudent student : allStudents) {
            if(student.getId() == studentID) {
                studentObject = student;
                break;
            }
        }
        return studentObject;
    }
    
    /**
     * Method for returning a list of all instruments in the database. 
     * @return Returns an observable list of all instruments
     */
    public static ObservableList<String> getAllInstruments() {
        return instruments;
    }
    
    /**
     * Method for adding an instrument to the list of all instruments. 
     * @param instrument Type of instrument to add
     */
    public static void addInstruments(String instrument) {
        instruments.add(instrument);
    }
    
    /**
     * Method for clearing the list of all instruments. 
     */
    public static void clearInstruments() {
        instruments.clear();
    }
    
    /**
     * Method for returning a list of all instrument students in the database. 
     * @return Returns an observable list of all instrument students
     */
    public static ObservableList<InstrumentStudent> getInstrumentStudents() {
        return instrumentStudents;
    }
    
    /**
     * Method for adding an instrument to the list of all instrument students. 
     * @param instrumentStudent Type of instrument student to add
     */
    public static void addInstrumentStudents(InstrumentStudent instrumentStudent) {
        instrumentStudents.add(instrumentStudent);
    }
    
    /**
     * Method for clearing the list of all instrument students. 
     */
    public static void clearInstrumentStudents() {
        instrumentStudents.clear();
    }
    
    /**
     * Method for returning a list of appointments with a specific contact. 
     * @return Returns an observable list of contact specific appointments
     */
    public static ObservableList<Appointment> getAppointmentsByTeacher() {
        return appointmentsByTeacher;
    }
    
    /**
     * Method for adding an appointment to the list of contact specific appointments. 
     * @param appointment Appointment to be added
     */
    public static void addAppointmentByTeacher(Appointment appointment) {
        appointmentsByTeacher.add(appointment);
    }
    
    /**
     * Method for clearing the list of contact specific appointments. 
     */
    public static void clearAppointmentsByTeacher() {
        appointmentsByTeacher.clear();
    }
    
}

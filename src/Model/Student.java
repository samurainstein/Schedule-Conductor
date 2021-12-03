/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Eric
 */
public abstract class Student {
    
    private int id;
    private String name;
    private String country;
    private String division;
    private String postalCode;
    private String address;
    private String phone;
    
    /**
     * Constructor for Student objects. 
     * @param id ID of student
     * @param name Name of student
     * @param country Country of student
     * @param division Division of student 
     * @param postalCode Postal Code of student
     * @param address Address of student   
     * @param phone Phone number of student
     */
    public Student(int id,
            String name,
            String country,
            String division,
            String postalCode,
            String address,
            String phone) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.division = division;
        this.postalCode = postalCode;
        this.address = address;
        this.phone = phone;
    }
    
    /**
     * Method for getting a student ID. 
     * @return Returns a student ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * Method for getting a student name. 
     * @return Returns a student name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Method for getting a student country Id. 
     * @return Returns a student country Id
     */
    public String getCountry() {
        return country;
    }
    
    /**
     * Method for getting a student division Id. 
     * @return Returns a student division Id
     */
    public String getDivision() {
        return division;
    }
    
    /**
     * Method for getting a student's postal code. 
     * @return Returns a postal code
     */
    public String getPostalCode() {
        return postalCode;
    }
        
    /**
     * Method for getting the address of a student. 
     * @return Returns an address
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * Method for getting a student's phone number. 
     * @return Returns a phone number
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * Overrides toString() method, and returns a formatted student ID and name. 
     * @return Returns a formatted student ID and name
     */
    @Override
    public String toString() {
        return ("[" + id + "] " + name);
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Eric Matelyan
 */
public abstract class Teacher {
    
    private int id;
    private String name;
    private String country;
    private String division;
    private String postalCode;
    private String address;
    private String phone;
    private String username;
    private String password;
    
    /**
     * Constructor for Teacher objects. 
     * @param id ID of teacher
     * @param name Name of teacher
     * @param country Country of teacher
     * @param division Division of teacher 
     * @param postalCode Postal Code of teacher
     * @param address Address of teacher   
     * @param phone Phone number of teacher
     * @param username Username of teacher for login
     * @param password Password of teacher for login
     */
    public Teacher(int id, String name, String country, String division, String postalCode, String address, String phone, String username, String password) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.division = division;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }
    
    /**
     * Method for getting a teacher ID. 
     * @return Returns a teacher ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * Method for getting a teacher name. 
     * @return Returns a teacher name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Method for getting a teacher country Id. 
     * @return Returns a teacher country Id
     */
    public String getCountry() {
        return country;
    }
    
    /**
     * Method for getting a teacher division Id. 
     * @return Returns a teacher division Id
     */
    public String getDivision() {
        return division;
    }
    
    /**
     * Method for getting a teacher's postal code. 
     * @return Returns a postal code
     */
    public String getPostalCode() {
        return postalCode;
    }
        
    /**
     * Method for getting the address of a teacher. 
     * @return Returns an address
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * Method for getting a teacher's phone number. 
     * @return Returns a phone number
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * Method for getting a teacher's username. 
     * @return Returns a username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Method for getting a teacher's password. 
     * @return Returns a password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Overrides toString() method, and returns a formatted teacher ID and name. 
     * @return Returns a formatted teacher ID and name
     */
    @Override
    public String toString() {
        return ("[" + id + "] " + name);
    }
    
}
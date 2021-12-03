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
public class InstrumentTeacher extends Teacher {
    
    private String instrument;
    private char availableOnline;
    private char availableInPerson;
    
    /**
     * Constructor for InstrumentTeacher objects. 
     * @param id ID of teacher
     * @param name Name of teacher
     * @param country Country of teacher
     * @param division Division of teacher 
     * @param postalCode Postal Code of teacher
     * @param address Address of teacher   
     * @param phone Phone number of teacher
     * @param instrument Instrument that is taught
     * @param username Username of teacher for login
     * @param password Password of teacher for login
     * @param availableOnline Y or N whether teaches online
     * @param availableInPerson Y or N whether teacher in person
     */
    public InstrumentTeacher(
            int id, 
            String name, 
            String country, 
            String division, 
            String postalCode, 
            String address, 
            String phone,
            String username,
            String password,
            String instrument,
            char availableOnline,
            char availableInPerson) {
        super(id, name, country, division, postalCode, address, phone, username, password);
        this.instrument = instrument;
        this.availableOnline = availableOnline;
        this.availableInPerson = availableInPerson;
    }
    
    /**
     * Method for getting a teacher instrument. 
     * @return Returns a teacher instrument
     */
    public String getInstrument() {
        return instrument;
    }  
    
    /**
     * Method for getting a y/n result of whether the teacher is available online. 
     * @return y/n result for teaching online
     */
    public char getAvailableOnline() {
        return availableOnline;
    }
    
    /**
     * Method for getting a y/n result of whether the teacher is available in person. 
     * @return y/n result for teaching online
     */
    public char getAvailableInPerson() {
        return availableInPerson;
    }
}

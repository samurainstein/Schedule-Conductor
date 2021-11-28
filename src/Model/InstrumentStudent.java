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
public class InstrumentStudent extends Student {
    
    private String instrument;
    private char availableOnline;
    private char availableInPerson;
    
    /**
     * Constructor for InstrumentStudent objects. 
     * @param id ID of student
     * @param name Name of student
     * @param countryId Country of student
     * @param divisionId Division of student 
     * @param postalCode Postal Code of student
     * @param address Address of student   
     * @param phone Phone number of student
     * @param instrument Instrument that student plays
     * @param availableOnline Y or N whether studentis available online
     * @param availableInPerson Y or N whether student is available in person
     */
    public InstrumentStudent(
            int id, 
            String name, 
            int countryId, 
            int divisionId, 
            String postalCode, 
            String address, 
            String phone,
            String instrument,
            char availableOnline,
            char availableInPerson) {
        super(id, name, countryId, divisionId, postalCode, address, phone);
        this.instrument = instrument;
        this.availableOnline = this.availableOnline;
        this.availableInPerson = this.availableInPerson;
    }
    
    /**
     * Method for getting a student instrument. 
     * @return Returns a student instrument
     */
    public String getInstrument() {
        return instrument;
    }  
    
    /**
     * Method for getting a y/n result of whether the student is available online. 
     * @return y/n result for online availability
     */
    public char getAvailableOnline() {
        return availableOnline;
    }
    
    /**
     * Method for getting a y/n result of whether the student is available in person. 
     * @return y/n result for in person availability
     */
    public char getAvailableInPerson() {
        return availableInPerson;
    }
}
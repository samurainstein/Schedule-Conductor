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
    private char teachOnline;
    private char teachInPerson;
    
    /**
     * Constructor for InstrumentTeacher objects. 
     * @param id ID of teacher
     * @param name Name of teacher
     * @param countryId Country of teacher
     * @param divisionId Division of teacher 
     * @param postalCode Postal Code of teacher
     * @param address Address of teacher   
     * @param phone Phone number of teacher
     * @param instrument Instrument that is taught
     * @param teachOnline Y or N whether teaches online
     * @param teachInPerson Y or N whether teacher in person
     */
    public InstrumentTeacher(
            int id, 
            String name, 
            int countryId, 
            int divisionId, 
            String postalCode, 
            String address, 
            String phone,
            String username,
            String password,
            String instrument,
            char teachOnline,
            char teachInPerson) {
        super(id, name, countryId, divisionId, postalCode, address, phone, username, password);
        this.instrument = instrument;
        this.teachOnline = teachOnline;
        this.teachInPerson = teachInPerson;
    }
    
    /**
     * Method for getting a teacher instrument. 
     * @return Returns a teacher instrument
     */
    public String getInstrument() {
        return instrument;
    }  
    
    /**
     * Method for getting a y/n result of whether the teacher teaches online. 
     * @return y/n result for teaching online
     */
    public char getTeachOnline() {
        return teachOnline;
    }
    
    /**
     * Method for getting a y/n result of whether the teacher teaches in person. 
     * @return y/n result for teaching online
     */
    public char getTeachInPerson() {
        return teachInPerson;
    }
}

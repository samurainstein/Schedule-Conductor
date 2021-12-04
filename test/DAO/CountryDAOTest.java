/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Country;
import Model.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Eric
 */
public class CountryDAOTest {
    
    public CountryDAOTest() {
    }

    /**
     * Test of selectCountries method, of class CountryDAO.
     */
    @Test
    public void testSelectCountries() {
        CountryDAO.selectCountries();
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        allCountries = Data.getAllCountries();
        int expected = 3;
        int actual = allCountries.size();
        Assertions.assertEquals(expected, actual);
    }
    
}

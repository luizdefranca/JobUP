package com.br.jobup.util.Validations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by luizramos on 08/04/17.
 */
public class DataValidationTest {

    Boolean dateinValid;
    Boolean dateValid;

    @Before
    public void setUp() throws Exception {
        dateinValid = DataValidation.isDateValid("30/02/2017");
        dateValid = DataValidation.isDateValid("27/02/2017");
    }

    @Test
    public void isDateValid_from_Invalid_Date() throws Exception {

        assertFalse(dateinValid);
    }

    @Test
    public void isDateValid_from_Valid_Date() throws Exception {

        assertFalse(dateValid);
    }

}
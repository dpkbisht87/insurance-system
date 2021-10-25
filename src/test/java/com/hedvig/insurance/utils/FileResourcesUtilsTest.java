package com.hedvig.insurance.utils;

import junit.framework.TestCase;

import java.util.Calendar;
import java.util.Date;

public class FileResourcesUtilsTest extends TestCase {
    
    public void testGetMonth() {
        FileResourcesUtils fileUtils = new FileResourcesUtils();
        Calendar c1 = Calendar.getInstance();
    
        c1.set(Calendar.MONTH, 9);
    
        c1.set(Calendar.DATE, 5);
    
        c1.set(Calendar.YEAR, 1996);
    
        // creating a date object with specified time.
        Date dateOne = c1.getTime();
        int month = fileUtils.getMonth(dateOne);
        assertEquals(month, 10);
    }
}
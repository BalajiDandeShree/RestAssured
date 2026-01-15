package com.w2a.base;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestSoftAllert {
    SoftAssert anAssert = new SoftAssert();

    @Test
    public void testSoftAlert(){

        int a =2;

        anAssert.assertEquals(a,2,"Number not equal to 3");
        anAssert.assertEquals(a,4,"Number not equal to 4");
        anAssert.assertEquals(a,5,"Number not equal to 5");
        anAssert.assertAll();
        System.out.println("Hi");

    }
}

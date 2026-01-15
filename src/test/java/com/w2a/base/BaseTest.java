package com.w2a.base;

import com.w2a.util.PropertyReader;
import io.cucumber.java.Before;
import io.restassured.RestAssured;


import java.io.File;

public class BaseTest {

    @Before
    public void setUp()throws Exception{
       // cleanUp();
        PropertyReader.loadProperties(PropertyReader.CONFIG_PROPERTIES_FILE);
        RestAssured.baseURI = System.getProperty("baseURI");
        String uri = RestAssured.baseURI;
        System.out.println();

    }


    public static void cleanUp () {
        File extentReports = new File("./reports");
        if (extentReports.exists())
            deleteDirectory(extentReports);
    }

    public static void deleteDirectory(File directory) {
        if (directory == null || !directory.exists()) return;

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        directory.delete();
    }


}

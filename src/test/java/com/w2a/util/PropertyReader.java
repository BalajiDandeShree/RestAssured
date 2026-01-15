package com.w2a.util;





import com.w2a.exception.AutomationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public static final String CONFIG_PROPERTIES_FILE = "src/test/resources/properties/config.properties";
    public static void loadProperties(String fileName) throws AutomationException {
        InputStream inputStream = null;
        try {
            File file = new File(fileName);
            inputStream = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(inputStream);
            if(!properties.isEmpty())
                for(Object key: properties.keySet())
                    System.setProperty(key.toString(), properties.getProperty(key.toString()));
        } catch(Exception ex) {
            throw  new AutomationException("Unable to read property file");
        } finally {
            if(inputStream!=null) {
                try {
                    inputStream.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}

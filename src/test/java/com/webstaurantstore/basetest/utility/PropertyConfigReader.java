package com.webstaurantstore.basetest.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class PropertyConfigReader {
    private static Properties properies;
    private static final String PATH = "resources/config.properties";// it was configuration directory in Bokarat

    static {//constructor
        File file = new File(PATH);

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            properies = new Properties();
            properies.load(fileInputStream);// try to load stream to property reader
        } catch (IOException e) {
            System.out.println("Can not read property file " + e.getStackTrace());
        }

    }

   public static String getBaseUrl(){
       return properies.getProperty("baseURL");

    }

    public static String getImplicitTimeOut(){
        return properies.getProperty("implicitTimeOut");
    }
    public static String getPageLoadTimeOut(){
        return properies.getProperty("pageLoadTimeOut");
    }
}

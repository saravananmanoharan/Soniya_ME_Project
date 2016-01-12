package com.soniya.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertyValuesFinder {

    private InputStreamReader inputStream;
    static Properties prop = new Properties();
    private static PropertyValuesFinder instance;

    private PropertyValuesFinder() {
        String propFileName = "resources/config.properties";
        try {
            inputStream = new InputStreamReader(new FileInputStream(new File(propFileName)));
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static PropertyValuesFinder getInstance() {
        if (instance == null) {
            instance = new PropertyValuesFinder();
        }
        return instance;
    }

    public String getStringValues(String key) throws IOException {
        String result = "Key Not Found!";
        try {
            result = prop.getProperty(key);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return result;
    }

    public int getIntValues(String key) throws IOException {
        int result = 0;
        try {
            result = Integer.parseInt(prop.getProperty(key));
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return result;
    }
}

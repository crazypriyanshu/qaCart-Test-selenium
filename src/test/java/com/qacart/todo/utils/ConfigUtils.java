package com.qacart.todo.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
    private Properties prop;
    InputStream is;

    private static ConfigUtils configUtils;
    private ConfigUtils() throws IOException {
        prop = readPropertyFile();
    }

    public static ConfigUtils getInstance() throws IOException {
        if (configUtils == null) {
            configUtils = new ConfigUtils();
        }
        return configUtils;
    }

    private Properties readPropertyFile() throws IOException {
        try {
            String env = System.getProperty("env", "PROD");
            switch (env) {
                case "PROD" -> {
                    is = new FileInputStream("src/test/resources/env/prod.properties");

                }
                case "LOCAL" -> {
                    is = new FileInputStream("src/test/resources/env/local.properties");

                }
                default -> {
                    throw new RuntimeException("Environment is not supported");
                }
            }

            prop = new Properties();
            prop.load(is);
            System.out.println(prop.get("URL"));
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return prop;

    }

    public String getBaseUrl() {
        return prop.get("URL").toString();
    }


//    public static void main(String[] args) throws IOException {
//        InputStream is = new FileInputStream("src/test/resources/env/prod.properties");
//        Properties prop = new Properties();
//        prop.load(is);
//        System.out.println(prop.get("URL"));
//    }
}

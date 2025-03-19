package org.zorba.utility;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseUtility {
    public static Connection getdbConnection() {
        Connection connection = null;
        try {
            File file = new File("src/main/resources/dbConection.properties");
            FileInputStream fileInputStream = new FileInputStream(file);
            //create a properties object
            Properties properties = new Properties();
            //load the properties file
            properties.load(fileInputStream);
            //read the content of the properties file
            String url = properties.getProperty("url");
            String user = properties.getProperty("username");
            String password = properties.getProperty("password");
            String classname = properties.getProperty("classname");


            Class.forName(classname);
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established");;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return connection;
    }
}

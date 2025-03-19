package org.zorba.fileOperations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Properties;

public class WriteToPropertiesFile {
    public static void main(String[] args) throws Exception {
        try {
            File file = new File("src/main/resources/employee.properties");
            FileInputStream fileInputStream = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInputStream);

            ArrayList<Employee> employees = new ArrayList<>();
            employees.add(new Employee(1006, "Ruby Lama", "San Diego", "3456776532", "Finance", "2021-12-23"));
            employees.add(new Employee(1007, "Danjo", "Las Vegas", "7456776532", "HR", "2022-12-21"));
            employees.add(new Employee(1008, "Jack Sophia", "Boston", "8456776532", "Science", "2021-10-25"));

            for (int i = 0; i < employees.size(); i++) {
                Employee employee = employees.get(i);
                properties.setProperty("empId" + i, String.valueOf(employee.getEmpId()));
                properties.setProperty("empName" + i, employee.getEmpName());
                properties.setProperty("empAddress" + i, employee.getEmpAddress());
                properties.setProperty("empMobile" + i, employee.getEmpMobile());
                properties.setProperty("department" + i, employee.getDepartment());
                properties.setProperty("doj" + i, employee.getDoj());

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                properties.store(fileOutputStream, "Employee Details saved in properties file");
                fileOutputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}

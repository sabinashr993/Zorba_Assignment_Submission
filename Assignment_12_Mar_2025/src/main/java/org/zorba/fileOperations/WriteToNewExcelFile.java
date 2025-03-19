package org.zorba.fileOperations;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WriteToNewExcelFile {
    public static void main(String[] args) {
        try {
            List<Employee> employeeList = new ArrayList<>();
            readFromExcel(employeeList);
            readFromPropertiesFile(employeeList);

            File file = new File("src/main/resources/new_employee.xlsx");
            FileInputStream fileInputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            var sheet = workbook.getSheetAt(0);

            for(int i = 0; i <= employeeList.size(); i++) {
                Row row = sheet.createRow(i);
                for (int j = 0; j < 6; j++) {
                    Cell cell = row.createCell(j);
                    if (i == 0) {
                        switch (j) {
                            case 0:
                                cell.setCellValue("EmpId");
                                break;
                            case 1:
                                cell.setCellValue("EmpName");
                                break;
                            case 2:
                                cell.setCellValue("EmpAddress");
                                break;
                            case 3:
                                cell.setCellValue("EmpMobile");
                                break;
                            case 4:
                                cell.setCellValue("Department");
                                break;
                            case 5:
                                cell.setCellValue("DOJ");
                                break;
                        }
                    } else {
                        Employee employee = employeeList.get(i - 1);
                        switch (j) {
                            case 0:
                                cell.setCellValue(employee.getEmpId());
                                break;
                            case 1:
                                cell.setCellValue(employee.getEmpName());
                                break;
                            case 2:
                                cell.setCellValue(employee.getEmpAddress());
                                break;
                            case 3:
                                cell.setCellValue(employee.getEmpMobile());
                                break;
                            case 4:
                                cell.setCellValue(employee.getDepartment());
                                break;
                            case 5:
                                cell.setCellValue(employee.getDoj());
                                break;
                        }
                    }
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                workbook.write(fileOutputStream);
                fileOutputStream.close();
                System.out.println("Input into the new_employee excel file...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static void readFromExcel(List<Employee> employeeList) {
        try {
            File file = new File("src/main/resources/employee.xlsx");
            FileInputStream fileInputStream = new FileInputStream(file);

            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            System.out.println("Number of sheets: " + workbook.getNumberOfSheets());

            //read from the first sheet
            var sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                var row = sheet.getRow(i);
                DataFormatter formatter = new DataFormatter();
                int empId = (int) row.getCell(0).getNumericCellValue();
                String empName = row.getCell(1).getStringCellValue();
                String empAddress = row.getCell(2).getStringCellValue();
                String empMobile = formatter.formatCellValue(row.getCell(3));
                String department = row.getCell(4).getStringCellValue();
                String doj = formatter.formatCellValue(row.getCell(5));
                Employee employee = new Employee(empId, empName, empAddress, empMobile, department, doj);
                employeeList.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static void readFromPropertiesFile(List<Employee> employeeList) {
        try {
            File file = new File("src/main/resources/employee.properties");
            FileInputStream fileInputStream = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInputStream);

            System.out.println("Number of properties: " + properties.size());

            for (int i = 0; i < 3; i++) {
                int empId = Integer.parseInt(properties.getProperty("empId" + i));
                String empName = properties.getProperty("empName" + i);
                String empAddress = properties.getProperty("empAddress" + i);
                String empMobile = properties.getProperty("empMobile" + i);
                String department = properties.getProperty("department" + i);
                String doj = properties.getProperty("doj" + i);
                Employee employee = new Employee(empId, empName, empAddress, empMobile, department, doj);
                employeeList.add(employee);
                System.out.println("Employee added from list: " + employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}

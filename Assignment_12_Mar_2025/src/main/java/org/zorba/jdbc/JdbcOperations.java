package org.zorba.jdbc;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zorba.fileOperations.Employee;
import org.zorba.utility.DatabaseUtility;

import java.io.File;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class JdbcOperations {
    public static void main(String[] args) {
        try{
            Connection connection = DatabaseUtility.getdbConnection();
            Statement statement = connection.createStatement();

            String useDatabase = "USE zorba_assignment";
            statement.executeUpdate(useDatabase);

            List<Employee> employeeList = new ArrayList<>();
            HashMap<String, String> departmentMap = new HashMap<>();

            createDatabase(statement);
            createDepartmentTable(statement);
            createEmployeeTable(statement);
            insertIntoDepartmentTable(employeeList, statement, departmentMap);

            readFromEmployeeExcelFile(employeeList);

            insertIntoEmployeeTable(employeeList, statement);
            createFunction(statement);
            createProcedure(statement);
            addSalaryColumn(statement);
            callProcedure(statement);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void createDatabase(Statement statement) {
        try {
            String createDatabase = "CREATE DATABASE IF NOT EXISTS zorba_assignment";
            statement.executeUpdate(createDatabase);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void createDepartmentTable(Statement statement) {
        try {
            String createDepartmentTable = "CREATE TABLE IF NOT EXISTS department (dept_id INT AUTO_INCREMENT PRIMARY KEY, dept_name VARCHAR(255))";
            statement.executeUpdate(createDepartmentTable);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void createEmployeeTable(Statement statement) {
        try {
            String createEmployeeTable = "CREATE TABLE IF NOT EXISTS employee (emp_id INT AUTO_INCREMENT PRIMARY KEY, emp_name VARCHAR(255), emp_address VARCHAR(255), emp_mobile VARCHAR(255), emp_doj VARCHAR(255), dept_id INT, FOREIGN KEY (dept_id) REFERENCES department(dept_id))";
            statement.executeUpdate(createEmployeeTable);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void insertIntoDepartmentTable(List<Employee> employeeList, Statement statement, HashMap<String, String> departmentMap){
        try {
            for (Employee employee : employeeList) {
                departmentMap.put(employee.getDepartment(), employee.getDepartment());
            }

            for (String department : departmentMap.keySet()) {
                String insertDepartment = "INSERT INTO department (dept_name) VALUES ('" + department + "')";
                statement.executeUpdate(insertDepartment);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void insertIntoEmployeeTable(List<Employee> employeeList, Statement statement){
        try {
            for (Employee employee : employeeList) {
                int deptId = 0;
                if (Objects.equals(employee.getDepartment(), "Finance")) {
                    deptId = 1;
                } else if (Objects.equals(employee.getDepartment(), "Engg")) {
                    deptId = 2;
                } else if (Objects.equals(employee.getDepartment(), "Science")) {
                    deptId = 3;
                } else if (Objects.equals(employee.getDepartment(), "HR")) {
                    deptId = 4;
                }
                String insertEmployee = "INSERT INTO employee (emp_id, emp_name, emp_address, emp_mobile, emp_doj, dept_id) VALUES (" + employee.getEmpId() + ", '" + employee.getEmpName() + "', '" + employee.getEmpAddress() + "', '" + employee.getEmpMobile() + "', '" + employee.getDoj() + "', " + deptId + ")";
                statement.executeUpdate(insertEmployee);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void readFromEmployeeExcelFile(List<Employee> employeeList) {
        try {
            File file = new File("src/main/resources/new_employee.xlsx");
            FileInputStream fileInputStream = new FileInputStream(file);

            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

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
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void createFunction(Statement statement){
        try {
            String createFunction = "CREATE FUNCTION get_emp_salary_fn(department VARCHAR(255)) RETURNS INT " +
                    "BEGIN " +
                    "DECLARE emp_salary INT; " +
                    "IF department = 'Finance' THEN " +
                    "SET emp_salary = 30000; " +
                    "ELSEIF department = 'HR' THEN " +
                    "SET emp_salary = 45000.34; " +
                    "ELSEIF department = 'Engg' THEN " +
                    "SET emp_salary = 50000; " +
                    "ELSEIF department = 'Science' THEN " +
                    "SET emp_salary = 70000; " +
                    "ELSE " +
                    "SET emp_salary = 0; " +
                    "END IF; " +
                    "RETURN emp_salary; " +
                    "END;";
            statement.executeUpdate(createFunction);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void addSalaryColumn(Statement statement){
        try {
            String addSalaryColumn = "ALTER TABLE employee ADD emp_salary INT";
            statement.executeUpdate(addSalaryColumn);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void createProcedure(Statement statement){
        try {
            String updateSalary = "CREATE PROCEDURE update_emp_salary(IN department VARCHAR(255)) " +
                    "BEGIN " +
                    "UPDATE employee SET emp_salary = get_emp_salary_fn(department) WHERE dept_id = (SELECT dept_id FROM department WHERE dept_name = department); " +
                    "END;";
            statement.executeUpdate(updateSalary);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void callProcedure(Statement statement){
        try {
            CallableStatement callableStatement = statement.getConnection().prepareCall("{call update_emp_salary(?)}");
            callableStatement.setString(1, "Finance");
            callableStatement.execute();
            callableStatement.setString(1, "HR");
            callableStatement.execute();
            callableStatement.setString(1, "Engg");
            callableStatement.execute();
            callableStatement.setString(1, "Science");
            callableStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
}

package org.zorba.fileOperations;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.zorba.utility.DatabaseUtility;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PdfOperations {
    public static void main(String[] args) {
        try {
            ResultSet employees = readFromDb();
            writeToPdf(employees);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static ResultSet readFromDb() {
        ResultSet resultSetWithSubQuery = null;
        try {
            Connection connection = DatabaseUtility.getdbConnection();
            Statement statement = connection.createStatement();

            String useDatabase = "USE zorba_assignment";
            statement.executeUpdate(useDatabase);

            String selectSubQuery = "SELECT e.emp_id, e.emp_name, e.emp_address, e.emp_mobile, e.emp_doj, e.emp_salary, d.dept_name FROM employee e INNER JOIN department d ON e.dept_id = d.dept_id";
            resultSetWithSubQuery = statement.executeQuery(selectSubQuery);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return resultSetWithSubQuery;
    }

    public static void writeToPdf(ResultSet employees){
        try {
            File file = new File("src/main/resources/Employee_balance_sheet.pdf");
            PDDocument pdDocument = new PDDocument();
            PDPage pdPage = new PDPage();
            pdDocument.addPage(pdPage);
            PDPageContentStream pdPageContentStream
                    = new PDPageContentStream(pdDocument, pdPage, PDPageContentStream.AppendMode.APPEND, true, false);

            // Set font and font size for the title
            pdPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
            pdPageContentStream.beginText();
            pdPageContentStream.newLineAtOffset(100, 750); // Position for the title
            pdPageContentStream.showText("Employee Balance Sheet");
            pdPageContentStream.endText();

            // Set font and font size for the content
            pdPageContentStream.setFont(PDType1Font.HELVETICA, 12);

            pdPageContentStream.beginText();
            pdPageContentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            pdPageContentStream.newLineAtOffset(10, 700);
            pdPageContentStream.endText();

            List<String> hr = new ArrayList<>();
            List<String> finance = new ArrayList<>();
            List<String> engg = new ArrayList<>();
            List<String> science = new ArrayList<>();

            while (employees.next()) {
                if(employees.getString("dept_name").equals("HR")){
                    hr.add(employees.getString("emp_name") + ", " + employees.getString("emp_doj") + ", " + employees.getString("emp_salary"));
                } else if(employees.getString("dept_name").equals("Finance")){
                    finance.add(employees.getString("emp_name") + ", " + employees.getString("emp_doj") + ", " + employees.getString("emp_salary"));
                } else if(employees.getString("dept_name").equals("Engg")){
                    engg.add(employees.getString("emp_name") + ", " + employees.getString("emp_doj") + ", " + employees.getString("emp_salary"));
                } else if(employees.getString("dept_name").equals("Science")){
                    science.add(employees.getString("emp_name") + ", " + employees.getString("emp_doj") + ", " + employees.getString("emp_salary"));
                }
            }
            int startY = 700; // Starting Y position for the first table
            startY = drawDepartmentTable(pdPageContentStream, "Finance", finance, startY);
            startY = drawDepartmentTable(pdPageContentStream, "Engg", engg, startY);
            startY = drawDepartmentTable(pdPageContentStream, "Science", science, startY);
            drawDepartmentTable(pdPageContentStream, "HR", hr, startY);

            // Close the content stream
            pdPageContentStream.close();

            // Save the document
            pdDocument.save(file);

            // Close the document
            pdDocument.close();

            System.out.println("Employee Balance Sheet created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    private static int drawDepartmentTable(PDPageContentStream pdPageContentStream, String department, List<String> data, int startY) throws IOException {
        // Set font for department heading
        pdPageContentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
        pdPageContentStream.beginText(); // Begin text block for department heading
        pdPageContentStream.newLineAtOffset(100, startY); // Position for the department heading
        pdPageContentStream.showText("Department: " + department);
        pdPageContentStream.endText(); // End text block for department heading

        // Set font for table content
        pdPageContentStream.setFont(PDType1Font.HELVETICA, 12);

        // Draw table headers
        int tableStartY = startY - 20; // Position for the table headers
        pdPageContentStream.beginText(); // Begin text block for table headers
        pdPageContentStream.newLineAtOffset(100, tableStartY);
        pdPageContentStream.showText("Employee Name    | Date of Joining | Salary");
        pdPageContentStream.endText(); // End text block for table headers

        // Draw table rows
        int rowHeight = 15; // Height of each row
        int currentY = tableStartY - rowHeight; // Starting Y position for the first row
        double totalSalary = 0; // Variable to calculate total salary

        for (String row : data) {
            // Split the row into individual fields (assuming comma-separated values)
            String[] fields = row.split(",");
            String employeeName = fields[0].trim();
            String dateOfJoining = fields[1].trim();
            String salary = fields[2].trim();

            // Draw the row
            pdPageContentStream.beginText(); // Begin text block for the row
            pdPageContentStream.newLineAtOffset(100, currentY);
            pdPageContentStream.showText(String.format("%-15s | %-14s | %-10s", employeeName, dateOfJoining, salary));
            pdPageContentStream.endText(); // End text block for the row

            currentY -= rowHeight; // Move to the next row
            totalSalary += Double.parseDouble(salary); // Add to the total salary
        }

        // Draw total salary
        pdPageContentStream.beginText(); // Begin text block for total salary
        pdPageContentStream.newLineAtOffset(100, currentY - 10); // Position for the total salary
        pdPageContentStream.showText("Total Salary: " + String.format("%.2f", totalSalary));
        pdPageContentStream.endText(); // End text block for total salary

        // Return the Y position for the next table
        return currentY - 30; // Add extra space between tables
    }
}


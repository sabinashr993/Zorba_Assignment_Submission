package org.zorba.fileOperations;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.zorba.utility.DatabaseUtility;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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

            //select all from employee table and department name from department table
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
            File file = new File("src/main/resources/employee.pdf");
            PDDocument pdDocument = new PDDocument();
            PDPage pdPage = new PDPage();
            pdDocument.addPage(pdPage);
            PDPageContentStream pdPageContentStream
                    = new PDPageContentStream(pdDocument, pdPage, PDPageContentStream.AppendMode.APPEND, true, false);

            pdPageContentStream.beginText();
            pdPageContentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            pdPageContentStream.newLineAtOffset(10, 700);


            while (employees.next()) {
                pdPageContentStream.showText("Id: " + employees.getString("emp_id") + ", ");
                pdPageContentStream.showText("Name: " + employees.getString("emp_name") + ", ");
                pdPageContentStream.showText("Address: " + employees.getString("emp_address") + ", ");
                pdPageContentStream.showText("Mobile: " + employees.getString("emp_mobile") + ", ");
                pdPageContentStream.showText("DOJ: " + employees.getString("emp_doj") + ", ");
                pdPageContentStream.showText("Salary: " + employees.getString("emp_salary") + ", ");
                pdPageContentStream.showText("Dept: " + employees.getString("dept_name"));
                pdPageContentStream.newLineAtOffset(0, -15);
                pdPageContentStream.newLine();
            }

            pdPageContentStream.endText();
            pdPageContentStream.close();

            //Save the pdf file
            pdDocument.save(file);
            pdDocument.close();
            System.out.println("pdf file generated successfully.....");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }


}

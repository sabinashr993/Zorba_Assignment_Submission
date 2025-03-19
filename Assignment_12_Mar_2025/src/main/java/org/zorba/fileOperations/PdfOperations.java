package org.zorba.fileOperations;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.zorba.utility.DatabaseUtility;

import java.io.File;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PdfOperations {
    public static void main(String[] args) {
        try {
            readFromDb();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static void readFromDb() {
        try {
            Connection connection = DatabaseUtility.getdbConnection();
            Statement statement = connection.createStatement();

            String useDatabase = "USE zorba_assignment";
            statement.executeUpdate(useDatabase);

            //select all from employee table and department name from department table
            String selectSubQuery = "SELECT e.emp_id, e.emp_name, e.emp_address, e.emp_mobile, e.emp_doj, e.emp_salary, d.dept_name FROM employee e INNER JOIN department d ON e.dept_id = d.dept_id";
            ResultSet resultSetWithSubQuery = statement.executeQuery(selectSubQuery);

            //create pdf file and write data to it
            File file = new File("src/main/resources/employee.pdf");
            PDDocument pdDocument = new PDDocument();
            PDPage pdPage = new PDPage();
            pdDocument.addPage(pdPage);
            PDPageContentStream pdPageContentStream
                    = new PDPageContentStream(pdDocument, pdPage, PDPageContentStream.AppendMode.APPEND, true, false);

            pdPageContentStream.beginText();
            while (resultSetWithSubQuery.next()) {
                pdPageContentStream.showText("Employee ID: " + resultSetWithSubQuery.getString("emp_id") + ", ");
                pdPageContentStream.showText("Employee Name: " + resultSetWithSubQuery.getString("emp_name" + ", "));
                pdPageContentStream.showText("Employee Address: " + resultSetWithSubQuery.getString("emp_address" + ", "));
                pdPageContentStream.showText("Employee Mobile: " + resultSetWithSubQuery.getString("emp_mobile" + ", "));
                pdPageContentStream.showText("Employee DOJ: " + resultSetWithSubQuery.getString("emp_doj" + ", "));
                pdPageContentStream.showText("Employee Salary: " + resultSetWithSubQuery.getString("emp_salary" + ", "));
                pdPageContentStream.showText("Employee Department: " + resultSetWithSubQuery.getString("dept_name"));
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

package bazy.postgresql;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class OdczytDoCsv {

    public static void main(String[] args) {
   	 // final String url = "jdbc:postgresql://192.168.1.2:5432/hr";
   	 final String url = "jdbc:postgresql://localhost/hr";
   	 final String user = "hr";
   	 final String password = "abc123";
   	 
   	 final String sql = "SELECT employee_id, first_name, last_name, job_title, salary, department_name, street_address, postal_code, city, country_name, phone_number, email"
   			 + " FROM employees"
   			 + " JOIN jobs USING(job_id)"
   			 + " LEFT JOIN departments USING(department_id)"
   			 + " LEFT JOIN locations USING(location_id)"
   			 + " LEFT JOIN countries USING(country_id)"
   			 + " ORDER BY employee_id";
   	 
   	 try(Connection c = DriverManager.getConnection(url,    user, password);
   		 PreparedStatement stmt = c.prepareStatement(sql)) {
   		 
   		 try(ResultSet rs = stmt.executeQuery()) {
   			 zapiszDoCSV("pracownicy.csv", rs);
   		 }
   	 } catch (SQLException e) {
   		 e.printStackTrace();
   	 }
    }

    private static void zapiszDoCSV(String nazwaPliku, ResultSet rs) throws SQLException {
   	 CSVFormat format = CSVFormat.EXCEL
   			 .withDelimiter(';')
   			 .withHeader(rs);
   	 try(PrintWriter out = new PrintWriter(nazwaPliku);
   		 CSVPrinter printer = new CSVPrinter(out, format)) {
   		 printer.printRecords(rs);
   		 System.out.println("zapisany");
   		 
   	 } catch (FileNotFoundException e) {
   		 e.printStackTrace();
   	 } catch (IOException e) {
   		 e.printStackTrace();
   	 }
    }
}

















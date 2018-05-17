package bazy.sqlite;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Odczyt5 {
	
	

    public static void main(String[] args) {
   	 final String url = "jdbc:sqlite:hr.db";
   	 
   	 final String sql = "SELECT * FROM employees";
   	 
   	 
   	 try(Connection c = DriverManager.getConnection(url);
   		 PreparedStatement stmt = c.prepareStatement(sql)) {
   		 
   		 try(ResultSet rs = stmt.executeQuery()) {
   			 while(rs.next()) {
   				 int id = rs.getInt("employee_id");
   				 String imie = rs.getString("first_name");
   				 String nazwisko = rs.getString("last_name");
   				 String job = rs.getString("job_id");
   				 BigDecimal pensja = rs.getBigDecimal("salary");
   				 double prowizja = rs.getDouble("commission_pct");
   				 String data = rs.getString("hire_date");
   	
   				 
   				 System.out.printf("%3d %-15s %-15s %-10s %8s %.2f %s s\n",
   						 id, imie, nazwisko, job, pensja, prowizja, data);
   			 }
   		 }
   	 } catch (SQLException e) {
   		 e.printStackTrace();
   	 }
    }
}

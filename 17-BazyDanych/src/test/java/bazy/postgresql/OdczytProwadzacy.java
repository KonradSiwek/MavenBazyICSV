package bazy.postgresql;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OdczytProwadzacy {

    public static void main(String[] args) {
   	 final String url = "jdbc:postgresql://localhost/hr";
   	 final String user = "hr";
   	 final String password = "abc123";
   	 
   	 final String sql = "SELECT * FROM employees join jobs using(job_id)";
   	 
   	 try(Connection c = DriverManager.getConnection(url, user, password);
   		 PreparedStatement stmt = c.prepareStatement(sql)) {
   		 
   		 try(ResultSet rs = stmt.executeQuery()) {
   			 while(rs.next()) {
   				 int id = rs.getInt("employee_id");
   				 String imie = rs.getString("first_name");
   				 String nazwisko = rs.getString("last_name");
   				 String job = rs.getString("job_id");
   				 BigDecimal pensja = rs.getBigDecimal("salary");
   				 double prowizja = rs.getDouble("commission_pct");
   				 java.sql.Date data = rs.getDate("hire_date");
   				 String praca = rs.getString("job_title");
   				 LocalDate dataNowa = data.toLocalDate();
   				 
   				 System.out.printf("%3d %-15s %-15s %-10s %8s %.2f %s %-15s\n",
   						 id, imie, nazwisko, job, pensja, prowizja, dataNowa, praca);
   			 }
   		 }
   	 } catch (SQLException e) {
   		 e.printStackTrace();
   	 }
    }
}



package bazy.postgresql;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

public class Odczyt4 {
	////Klasa PrepareStetement usprawniona o metoda getString aby uniknąć SQLInjection
	
	static String kogoSzukac =  JOptionPane.showInputDialog("Podaj id stanowiska");

    public static void main(String[] args) {
   	 final String url = "jdbc:postgresql://localhost/hr";
   	 final String user = "hr";
   	 final String password = "abc123";
   	 
   	 final String sql = "SELECT * FROM employees where job_id=?";
   	 
   	 
   	 try(Connection c = DriverManager.getConnection(url, user, password);
   		 PreparedStatement stmt = c.prepareStatement(sql)) {
   		 
   		 stmt.setString(1,kogoSzukac);
   		 
   		 
   		 try(ResultSet rs = stmt.executeQuery()) {
   			 while(rs.next()) {
   				 int id = rs.getInt("employee_id");
   				 String imie = rs.getString("first_name");
   				 String nazwisko = rs.getString("last_name");
   				 String job = rs.getString("job_id");
   				 BigDecimal pensja = rs.getBigDecimal("salary");
   				 double prowizja = rs.getDouble("commission_pct");
   				 java.sql.Date data = rs.getDate("hire_date");
   				 LocalDate dataNowa = data.toLocalDate();
   				 
   				 System.out.printf("%3d %-15s %-15s %-10s %8s %.2f %s s\n",
   						 id, imie, nazwisko, job, pensja, prowizja, dataNowa);
   			 }
   		 }
   	 } catch (SQLException e) {
   		 e.printStackTrace();
   	 }
    }
}

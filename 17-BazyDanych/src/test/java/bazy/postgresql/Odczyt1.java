package bazy.postgresql;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Odczyt1 {

	public static void main(String[] args) {
		final String url = "jdbc:postgresql://localhost/hr";
		final String zapytanie ="select* from employees";
		final String user = "hr";
		final String haslo = "abc123";
		
	
			try {
				Connection con = DriverManager.getConnection(url, user, haslo);
				
				System.out.println("Połączenie nawiązane--- con to jest : "  + con);
				Statement stmt = con.createStatement();
				ResultSet result = stmt.executeQuery(zapytanie);
				
				while ( result.next()) {
					String imie = result.getString(2);
					BigDecimal pensja = result.getBigDecimal("salary");
					
					Date data = result.getDate("hire_date");
					LocalDate localDate = data.toLocalDate();
					
					int id = result.getInt(1);
					String nazwisko = result.getString(3);
					String job = result.getString("job_id");
					
					
					
					System.out.println(imie + " " + pensja+ " " + localDate + " " + data);
					
					System.out.printf("%3d %-15s %-15s %-10s %8s %s\n",
							 id, imie, nazwisko, job, pensja,data);
				}
				
				
				PreparedStatement pstmt = con.prepareStatement(zapytanie);	// przygotowujemy zapytanie
				ResultSet resultSet = 	pstmt.executeQuery();				// zadajemy zapytanie 
				
				System.out.println("resultSet: " +   resultSet);
				
				while(resultSet.next()) {
					int id = resultSet.getInt(1);
					String name = resultSet.getString(2);
					String lastName = resultSet.getString(3);
					
					System.out.println(id+ " " + name +" "+ lastName);				
				}
				
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

}
}

package bazy.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Odczyt5 {
	////Klasa PrepareStetement usprawniona o metoda getString aby uniknąć SQLInjection
	
	static String kogoSzukac =  JOptionPane.showInputDialog("Podaj id stanowiska");
	static String podwyzka =  JOptionPane.showInputDialog("Ile zmienisz pensje");
	static int podwyzkaInt= Integer.parseInt(podwyzka);
	

    public static void main(String[] args) {
   	 final String url = "jdbc:postgresql://localhost/hr";
   	 final String user = "hr";
   	 final String password = "abc123";
   	 
   	 final String sql = "Update employees set salary = salary+? where job_id=?  ";
   	 
   	 
   	 try(Connection c = DriverManager.getConnection(url, user, password);
   			 
   		 PreparedStatement stmt = c.prepareStatement(sql)) {
   		 stmt.setInt(1, podwyzkaInt);
   		 stmt.setString(2,kogoSzukac);
   		 int row = stmt.executeUpdate();
   		 
   		if(row>0) {
   			System.out.println(row);
   		}
   	 } catch (SQLException e) {
   		 e.printStackTrace();
   	 }
    }
}

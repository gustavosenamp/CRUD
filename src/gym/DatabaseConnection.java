package gym;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DatabaseConnection {
	
	public Connection getConnection() {
 
		try {
			
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/gym", "root", "fatec");
			
		} catch (SQLException excecao){
			throw new RuntimeException(excecao);
		}
		
	}

}
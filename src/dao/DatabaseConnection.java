package dao;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DatabaseConnection {
 
	public static void main(String[] args) {
        Connection conexao = null;

        try {
            // Configurando a URL de conexão com o banco de dados
            String url = "jdbc:mysql://localhost/escola";
            String usuario = "root";
            String senha = "G$mp07052005";

            // Conectando ao banco de dados
            conexao = DriverManager.getConnection(url, usuario, senha);

            // Realizando operações no banco de dados...
            
            System.out.println("Conexão bem-sucedida!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechando a conexão no bloco finally para garantir que seja fechada, mesmo em caso de exceção.
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
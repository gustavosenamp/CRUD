package gym;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DatabaseConnection {
	
	public Connection getConnection() {
 
		try {
			
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/gym", "root", "G$mp07052005");
			
		} catch (SQLException excecao){
			throw new RuntimeException(excecao);
		}
		
	}

}

/* CREATE DATABASE IF NOT EXISTS gym;

-- Usar o banco de dados
USE gym;

-- Tabela Aluno
CREATE TABLE IF NOT EXISTS aluno (
    cpf VARCHAR(15) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento varchar(10) NOT NULL,
    peso DOUBLE NOT NULL,
    altura DOUBLE NOT NULL
);

-- Tabela HistoricoPeso
CREATE TABLE IF NOT EXISTS historico_peso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cpf_aluno VARCHAR(15),
    data DATE NOT NULL,
    peso DOUBLE NOT NULL,
    FOREIGN KEY (cpf_aluno) REFERENCES aluno(cpf)
);

select * from aluno;
select * from historico_peso;

drop table aluno;
drop table historico_peso;*/
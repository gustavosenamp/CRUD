package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import gym.DatabaseConnection;

public class AlunoDao {
	
	private static final String INSERT_ALUNO_QUERY = "INSERT INTO aluno (cpf, nome, data_nascimento, peso, altura) VALUES (?, ?, ?, ?, ?)";
	
	private Connection connection;
	   
    public AlunoDao(){ 
        this.connection = new DatabaseConnection().getConnection();
    } 

	public void cadastrarAluno(String cpf, String nome, String dataNascimento, String peso, String altura) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date dataNascimentoDate = sdf.parse(dataNascimento);

            // Preparar a instrução SQL para inserção
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ALUNO_QUERY)) {
                preparedStatement.setString(1, cpf);
                preparedStatement.setString(2, nome);
                preparedStatement.setDate(3, new java.sql.Date(dataNascimentoDate.getTime()));
                preparedStatement.setDouble(4, Double.parseDouble(peso));
                preparedStatement.setDouble(5, Double.parseDouble(altura));

                // Executar a inserção
                preparedStatement.executeUpdate();

                JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
            }

        } catch (ParseException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar aluno: " + ex.getMessage());
        }
    }
	
}

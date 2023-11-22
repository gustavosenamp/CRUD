package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import gym.DatabaseConnection;
import user.Aluno;

public class AlunoDao {

    private Connection connection;

    public AlunoDao() {
        this.connection = new DatabaseConnection().getConnection();
    }

    public void cadastrarAluno(Aluno aluno) {
        String sql = "INSERT INTO aluno(cpf, nome, data_nascimento, peso, altura) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, aluno.getCpf());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getData());
            stmt.setDouble(4, aluno.getPeso());
            stmt.setDouble(5, aluno.getAltura());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o Aluno");
            throw new RuntimeException(e);
        }
    }

    public boolean cpfExiste(String cpf) {
        String sql = "SELECT * FROM aluno WHERE cpf = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, cpf);
            ResultSet resultSet = stmt.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao verificar CPF");
            throw new RuntimeException(e);
        }
    }
}

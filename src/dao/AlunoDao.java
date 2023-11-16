package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import gym.DatabaseConnection;
import user.Aluno;


public class AlunoDao {
    
    private Connection connection;
   
    public AlunoDao(){ 
        this.connection = new DatabaseConnection().getConnection();
    } 
    
    public void adicionaAluno(Aluno aluno){ 
        String sql = "INSERT INTO cadastro(alu_nome, alu_cpf, alu_data_nascimento, alu_peso, alu_altura) VALUE(?,?,?,?,?)";
        try { 
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            //stmt.setNString(3, aluno.getData());
            stmt.setDouble(4, aluno.getPeso());
            stmt.setDouble(5, aluno.getAltura());
            stmt.execute();
            stmt.close();
        } 
        catch (SQLException u) { 
            JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o Aluno");
            throw new RuntimeException(u);
        }         
        
    } 
    
    public boolean cpfExiste(String cpf){
        String sql = "SELECT * FROM cadastro WHERE alu_cpf = ?";
        try { 
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, cpf);
            stmt.execute();
            //stmt.close();
            ResultSet resultSet = stmt.getResultSet();
            
            return resultSet.next();
        } 
        catch (SQLException u) { 
            JOptionPane.showMessageDialog(null, "CPF não cadastrado");
            return false;
            //throw new RuntimeException(u);
        } 
    }
    
}

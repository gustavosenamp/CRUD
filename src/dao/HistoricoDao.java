package dao;

import gym.DatabaseConnection;
import user.Historico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class HistoricoDao {
    private Connection connection;
   
    public HistoricoDao(){ 
        this.connection = new DatabaseConnection().getConnection();
    } 
    
    public void adicionaHistorico(Historico historico){ 
        String sql = "INSERT INTO historico_peso(cpf_aluno, data_hora, peso) VALUE(?,?,?)";
        try { 
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, historico.getAluCpf());
            stmt.setString(2, historico.getDataHora());
            stmt.setDouble(3, historico.getPeso());
            stmt.execute();
            stmt.close();
        } 
        catch (SQLException u) { 
            JOptionPane.showMessageDialog(null, "Não foi possível salvar dados no histórico");
            throw new RuntimeException(u);
        } 
        
    }
    
    
    }

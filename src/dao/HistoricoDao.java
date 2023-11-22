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
    
    public List<Historico> obterHistorico() {
        List<Historico> historicoList = new ArrayList<>();

        String sql = "SELECT * FROM historico_peso";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String aluCpf = resultSet.getString("cpf_aluno");
                double peso = resultSet.getDouble("peso");
                String dataHora = resultSet.getString("data_hora");

                Historico historico = new Historico(aluCpf, peso, dataHora);
                historicoList.add(historico);
            }

            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao obter histórico");
            throw new RuntimeException(e);
        }

        return historicoList;
    }
    
    
}

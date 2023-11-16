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
        String sql = "INSERT INTO historico(alu_cpf, his_peso, his_dataHora) VALUE(?,?,?)";
        try { 
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, historico.getAluCpf());
            stmt.setDouble(2, historico.getPeso());
            stmt.setString(3, historico.getDataHora());
            stmt.execute();
            stmt.close();
        } 
        catch (SQLException u) { 
            JOptionPane.showMessageDialog(null, "Não foi possível salvar dados no histórico");
            throw new RuntimeException(u);
        } 
        
    }
    
    public List<Historico> listarHistoricos() throws SQLException {
        List<Historico> historicos = new ArrayList<>();
        String sql = "SELECT * FROM historico";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String aluCpf = rs.getString("alu_cpf");
                    double peso = rs.getDouble("his_peso");
                    String dataHora = rs.getString("his_dataHora");

                    Historico historico = new Historico(aluCpf, peso);
                    historico.setDataHora(dataHora);

                    historicos.add(historico);
                }
            }
        }

        return historicos;
    }
}
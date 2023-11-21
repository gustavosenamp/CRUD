package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.HistoricoDao;
import user.Historico;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelaHistorico extends JFrame {

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public TelaHistorico(List<Historico> historicos) {
        // Configuraçoes da janela
        setTitle("Histórico");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicialização da tabela
        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Data e Hora");
        modeloTabela.addColumn("Aluno CPF");
        modeloTabela.addColumn("Peso");

        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);

        // Adicionando dados à tabela
        for (Historico historico : historicos) {
            modeloTabela.addRow(new Object[]{historico.getDataHora(), historico.getAluCpf(), historico.getPeso()});
        }

        // Adicionando a tabela à janela
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Exibindo a janela
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static List<Historico> obterHistoricoDoBanco() {
        List<Historico> historicos = new ArrayList<>();

        // Crie um objeto HistoricoDao para interagir com o banco de dados
        HistoricoDao historicoDao = new HistoricoDao();

        try {
            historicos = historicoDao.listarHistoricos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao obter histórico do banco de dados: " + e.getMessage());
        }

        return historicos;
    }

    public static void main(String[] args) {
        // Criando a TelaHistorico e passando os objetos de Historico do banco de dados
        SwingUtilities.invokeLater(() -> new TelaHistorico(obterHistoricoDoBanco()));
    }
}

package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.HistoricoDao;
import user.Historico;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelaHistorico extends JFrame {

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public TelaHistorico() {
        // Configurações da janela
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

        // Botão "Logar"
        JButton atualizarButton = new JButton("Atualizar Peso");
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chama o método para atualizar o histórico
            	dispose();
            	new TelaLogin();
            }
        });

        // Adicionando a tabela e o botão à janela
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(atualizarButton, BorderLayout.NORTH);
        panel.add(voltarButton, BorderLayout.SOUTH);
        getContentPane().add(panel);

        // Exibindo a janela
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    // Método para atualizar o histórico na tabela
    public void atualizarHistorico() {
        modeloTabela.setRowCount(0); // Limpa todas as linhas da tabela

        HistoricoDao historicoDao = new HistoricoDao();
        List<Historico> historicoList = historicoDao.obterHistorico();

        for (Historico historico : historicoList) {
            Object[] rowData = {historico.getDataHora(), historico.getAluCpf(), historico.getPeso()};
            modeloTabela.addRow(rowData);
        }
    }
    
   
}
    

    


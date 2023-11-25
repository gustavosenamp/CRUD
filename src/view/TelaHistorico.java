package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.HistoricoDao;
import gym.DatabaseConnection;
import user.Historico;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TelaHistorico extends JFrame {

	private JScrollPane scroll;
    private JTable tabela;
    private DefaultTableModel model;
    private JButton jButtonVoltar;
    private String cpf;

    public TelaHistorico(String cpf) {
    	 super("Histórico de Pesos");

         this.cpf = cpf;

         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         tabela = new JTable();
         scroll = new JScrollPane(tabela);

         setSize(400, 300);
         setLocationRelativeTo(null);
         setVisible(true);

         add(scroll);
         
         jButtonVoltar = new JButton("Voltar");
         jButtonVoltar.setBounds(10, 10, 10, 10);
         jButtonVoltar.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {

                 dispose();

                 new TelaOpcoes(cpf);
             }
             
         });
         add(jButtonVoltar, BorderLayout.SOUTH);

         preencherTabela();

         

     }

    private void preencherTabela() {
        Vector<Vector<Object>> data = new Vector<>();
        Vector<String> columnNames = new Vector<>();
        columnNames.add("CPF");
        columnNames.add("Peso (Kg)");
        columnNames.add("Data e Hora");

        try {
            Connection connection = new DatabaseConnection().getConnection();

            String query = "SELECT * FROM historico_peso WHERE cpf_aluno = '" + cpf + "'";
            try (PreparedStatement pstmt = connection.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    Vector<Object> row = new Vector<>();
                    row.add(rs.getString("cpf_aluno"));
                    row.add(rs.getDouble("peso"));

                    // Formatando Timestamp para uma String
                    java.sql.Timestamp dataHoraTimestamp = rs.getTimestamp("data_hora");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dataHora = dateFormat.format(dataHoraTimestamp);

                    row.add(dataHora);
                    data.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        model = new DefaultTableModel(data, columnNames);
        tabela.setModel(model);
    }
    
   
}
    

    


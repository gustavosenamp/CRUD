package view;

import javax.swing.*;

import gym.DatabaseConnection;

import dao.AlunoDao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AlunoView {

	private static final String INSERT_ALUNO_QUERY = "INSERT INTO aluno (cpf, nome, data_nascimento, peso, altura) VALUES (?, ?, ?, ?, ?)";
	
    private Connection connection;
	   
    public AlunoView(){ 
        this.connection = new DatabaseConnection().getConnection();
    } 

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Cadastro de Aluno");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        Font labelFont = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
        Font textFieldFont = new Font(Font.SANS_SERIF, Font.PLAIN, 12);

        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setFont(labelFont);
        JTextField cpfField = new JTextField(10);
        cpfField.setFont(textFieldFont);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setFont(labelFont);
        JTextField nomeField = new JTextField(10);
        nomeField.setFont(textFieldFont);

        JLabel dataNascimentoLabel = new JLabel("Data de Nascimento:");
        dataNascimentoLabel.setFont(labelFont);
        JTextField dataNascimentoField = new JTextField(10);
        dataNascimentoField.setFont(textFieldFont);

        JLabel pesoLabel = new JLabel("Peso:");
        pesoLabel.setFont(labelFont);
        JTextField pesoField = new JTextField(10);
        pesoField.setFont(textFieldFont);

        JLabel alturaLabel = new JLabel("Altura:");
        alturaLabel.setFont(labelFont);
        JTextField alturaField = new JTextField(10);
        alturaField.setFont(textFieldFont);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(e -> cadastrarAluno(cpfField.getText(), nomeField.getText(), dataNascimentoField.getText(), pesoField.getText(), alturaField.getText()));

        JButton consultarButton = new JButton("Consultar");
        consultarButton.addActionListener(e -> consultarAluno(cpfField.getText()));

        formPanel.add(cpfLabel);
        formPanel.add(cpfField);
        formPanel.add(nomeLabel);
        formPanel.add(nomeField);
        formPanel.add(dataNascimentoLabel);
        formPanel.add(dataNascimentoField);
        formPanel.add(pesoLabel);
        formPanel.add(pesoField);
        formPanel.add(alturaLabel);
        formPanel.add(alturaField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(cadastrarButton);
        buttonPanel.add(consultarButton);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.PAGE_END);

        frame.getContentPane().add(panel);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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

	private void consultarAluno(String cpf) {
        // Implementar lógica para consultar aluno
        // Você pode chamar os métodos da classe Aluno aqui
    }
}

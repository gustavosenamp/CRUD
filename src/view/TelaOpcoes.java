package view;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.AlunoDao;
import gym.DatabaseConnection;
import user.Historico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TelaOpcoes extends JFrame {

	private String aluCpf;
	private JTextField jTextPeso;
    private JLabel jLabelTitle, jLabelNovoPeso;
    private JButton buttonOption1, buttonOption2, buttonOption3, buttonOption4, buttonBack;

    public TelaOpcoes(String cpf) {
    	this.aluCpf = cpf;
        setTitle("Opções");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        jLabelTitle = new JLabel("Opções Disponíveis");
        jLabelTitle.setBounds(130, 2, 150, 30);
        add(jLabelTitle);
        
        jLabelNovoPeso = new JLabel("Informe o novo peso:");
        jLabelNovoPeso.setBounds(90, 30, 150, 20);
        add(jLabelNovoPeso);

        jTextPeso = new JTextField();
        jTextPeso.setBounds(90, 50, 200, 20);
        add(jTextPeso);

        buttonOption1 = new JButton("Salvar Peso");
        buttonOption1.setBounds(30, 100, 150, 30);
        buttonOption1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Connection connection = new DatabaseConnection().getConnection();
                Historico historico = new Historico(aluCpf, Double.parseDouble(jTextPeso.getText()));
                
                String sql = "INSERT INTO historico_peso(cpf_aluno, data_hora, peso) VALUE(?,?,?)";
                String updateCadastro = "UPDATE aluno SET peso = ? WHERE cpf = ?";
                try { 
                    PreparedStatement stmt = connection.prepareStatement(sql);

                    stmt.setString(1, historico.getAluCpf());
                    stmt.setString(2, historico.getDataHora());
                    stmt.setDouble(3, historico.getPeso());
                    stmt.execute();
                    stmt.close();
                    
                    PreparedStatement stmt2 = connection.prepareStatement(updateCadastro);
                    stmt2.setDouble(1, historico.getPeso());
                    stmt2.setString(2, historico.getAluCpf());
                    stmt2.execute();
                    stmt2.close();
                    
                    JOptionPane.showMessageDialog(null, "Peso alterado com sucesso!");
                } 
                catch (SQLException u) { 
                    JOptionPane.showMessageDialog(null, "Não foi possível registrar dados no histórico");
                    throw new RuntimeException(u);
                }  
            }
        });
        
        add(buttonOption1);

        buttonOption2 = new JButton("Ver Histórico");
        buttonOption2.setBounds(200, 100, 150, 30);
        buttonOption2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
                new TelaHistorico(cpf);
            }
        });
        
        add(buttonOption2);
        
        buttonOption3 = new JButton("Excluir cadastro");
        buttonOption3.setBounds(200, 170, 150, 30);
        buttonOption3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                AlunoDao dao = new AlunoDao();
                dao.excluirAlunoPorCpf(cpf);
                JOptionPane.showMessageDialog(null, "Aluno excluido com sucesso!");
                dispose();
                new TelaLogin();
            }
        });
        
        add(buttonOption3);

        buttonOption4 = new JButton("Calcular IMC");
        buttonOption4.setBounds(30, 170, 150, 30);
        buttonOption4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	calcularIMC(cpf);
            }
        });
        add(buttonOption4);

        buttonBack = new JButton("Voltar");
        buttonBack.setBounds(115, 220, 150, 30);
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                new TelaLogin();
            }
        });
        add(buttonBack);

        setVisible(true);
    }
    
    private static void calcularIMC(String cpf) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a file to save IMC data");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try (Connection connection = new DatabaseConnection().getConnection()) {
                String query = "SELECT nome, peso, altura, data_nascimento FROM aluno WHERE cpf = ?";

                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setString(1, cpf);

                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            String nome = rs.getString("nome");
                            double peso = rs.getDouble("peso");
                            double altura = rs.getDouble("altura");

                            // Realizar o cálculo do IMC
                            double imc = calcularIMC(peso, altura);

                            // Interpretar o resultado do IMC
                            String interpretacao = interpretarIMC(imc);

                            // Obter data e hora atual
                            LocalDateTime dataHoraAtual = LocalDateTime.now();
                            DateTimeFormatter formatterDataHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            String dataHoraFormatada = dataHoraAtual.format(formatterDataHora);

                            // Criar linha para ser gravada no arquivo
                            String linha = dataHoraFormatada + " - CPF: " + cpf + ", Nome: " + nome +
                                    ", IMC: " + String.format("%.2f", imc) + ", Interpretação: " + interpretacao;

                            // Gravar a linha no arquivo
                            gravarNoArquivo(linha, fileChooser.getSelectedFile());

                            System.out.println("Cálculo de IMC gravado com sucesso!");

                        } else {
                            System.out.println("Aluno não encontrado.");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erro ao conectar ao banco de dados.");
            }
        }
    }

    private static double calcularIMC(double peso, double altura) {
        return peso / (altura * altura);
    }

    private static String interpretarIMC(double imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 24.9) {
            return "Peso normal";
        } else {
            return "Sobrepeso";
        }
    }

    private static void gravarNoArquivo(String linha, File selectedFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile, true))) {
            writer.write(linha);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao gravar no arquivo.");
        }
    }



}

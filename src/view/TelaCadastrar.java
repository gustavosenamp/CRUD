package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import dao.AlunoDao;
import dao.HistoricoDao;
import gym.DatabaseConnection;
import user.Aluno;
import user.Historico;

public class TelaCadastrar extends JFrame implements ActionListener{
	
    public TelaCadastrar() {
        JFrame frame = new JFrame("Cadastro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 12);
        Font textFieldFont = new Font(Font.SANS_SERIF, Font.BOLD, 12);

        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setFont(labelFont);
        JTextField cpfField = new JTextField(5);
        cpfField.setFont(textFieldFont);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setFont(labelFont);
        JTextField nomeField = new JTextField(5);
        nomeField.setFont(textFieldFont);

        JLabel dataNascimentoLabel = new JLabel("Data de Nascimento:");
        dataNascimentoLabel.setFont(labelFont);
        JTextField dataNascimentoField = new JTextField(5);
        dataNascimentoField.setFont(textFieldFont);

        JLabel pesoLabel = new JLabel("Peso:");
        pesoLabel.setFont(labelFont);
        JTextField pesoField = new JTextField(5);
        pesoField.setFont(textFieldFont);

        JLabel alturaLabel = new JLabel("Altura:");
        alturaLabel.setFont(labelFont);
        JTextField alturaField = new JTextField(5);
        alturaField.setFont(textFieldFont);

        JButton cadastrarButton = new JButton("Cadastrar");
        JButton retornarButton = new JButton("Voltar");

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
        buttonPanel.add(retornarButton);
        
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfField.getText();
                String nome = nomeField.getText();
                String dataNascimento = dataNascimentoField.getText();
                String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                double peso = Double.parseDouble(pesoField.getText());
                double altura = Double.parseDouble(alturaField.getText());

                if (cpf.isEmpty() || nome.isEmpty() || dataNascimento.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Aluno aluno = new Aluno(cpf, nome, dataNascimento, peso, altura);

                AlunoDao alunoDao = new AlunoDao();
                HistoricoDao historicoDao = new HistoricoDao();

                if (alunoDao.cpfExiste(cpf)) {
                    JOptionPane.showMessageDialog(null, "CPF já cadastrado", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Cadastrar aluno na tabela aluno
                    alunoDao.cadastrarAluno(aluno);

                    // Cadastrar histórico de peso na tabela historico_peso
                    Historico historico = new Historico(cpf, peso);
                    historicoDao.adicionaHistorico(historico);

                    JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
                }
            }
        });


        retornarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
                new TelaInicial();
            }
        });

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.PAGE_END);

        frame.getContentPane().add(panel);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        setResizable(true);
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
    
	
}

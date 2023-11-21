package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import gym.DatabaseConnection;

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
        JButton voltarButton = new JButton("Voltar");

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
        buttonPanel.add(voltarButton);
        
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your logic for the register button here
                new TelaCadastrar();
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaInicial();
            }
        });

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.PAGE_END);

        frame.getContentPane().add(panel);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
    
	
}

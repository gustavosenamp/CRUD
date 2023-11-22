package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial extends JFrame {

    public TelaInicial() {
    	setTitle("Sistema de Gerenciamento da Academia");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(true);
        setLocationRelativeTo(null);
        
        // Create a title label
        JLabel titleLabel = new JLabel("Bem-vindo a Academia");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        titleLabel.setBounds(110, 50, 240, 30);
        add(titleLabel);

        // Create buttons
        JButton registerButton = new JButton("Cadastre-se");
        registerButton.setBounds(130, 120, 120, 25);
        add(registerButton);
        
        JButton loginButton = new JButton("Entrar");
        loginButton.setBounds(130, 150, 120, 25);
        add(loginButton);

        // Add action listeners to the buttons
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
                new TelaCadastrar();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
                new TelaLogin();
            }
        });

        setVisible(true);
    }
}

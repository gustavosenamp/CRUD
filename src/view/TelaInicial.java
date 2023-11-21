package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial extends JFrame {

    public TelaInicial() {
        JFrame frame = new JFrame("Gym Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create a title label
        JLabel titleLabel = new JLabel("Welcome to the Gym", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.CENTER);

        // Create buttons
        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(registerButton);
        buttonPanel.add(loginButton);

        // Add button panel to the center of the main panel
        panel.add(buttonPanel, BorderLayout.NORTH);

        // Add action listeners to the buttons
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your logic for the register button here
                new TelaCadastrar();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaLogin();
            }
        });

        // Add the main panel to the frame
        frame.getContentPane().add(panel);

        // Set the frame to be visible
        frame.setVisible(true);
    }
}

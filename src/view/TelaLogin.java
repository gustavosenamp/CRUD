package view;

import dao.AlunoDao;
import view.TelaHistorico;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class TelaLogin extends JFrame {

    private JLabel jLabelTitle, jLabelCPF; 
    private JTextField jTextCPF;
    private JButton buttonLogar, buttonCadastrar, buttonVoltar;
    private TelaLogin telaLogin;
    
    public TelaLogin(){
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(true);
        setLocationRelativeTo(null);

        jLabelTitle = new JLabel("Login");
        jLabelTitle.setBounds(170, 10, 100, 30);

        // Configurar a fonte para torná-la maior e em negrito
        Font titleFont = new Font("Arial", Font.BOLD, 24); 
        jLabelTitle.setFont(titleFont);

        add(jLabelTitle);


        jLabelCPF = new JLabel("Informe seu CPF:");
        jLabelCPF.setBounds(140, 80, 100, 20);
        add(jLabelCPF);

        jTextCPF = new JTextField();
        jTextCPF.setBounds(115, 110, 150, 20);
        add(jTextCPF);

        buttonLogar = new JButton("Entrar");
        buttonLogar.setBounds(130, 150, 120, 20);
        
        buttonCadastrar = new JButton("Cadastre-se");
        buttonCadastrar.setBounds(130, 175, 120, 20);
        
        buttonVoltar = new JButton("Voltar");
        buttonVoltar.setBounds(130, 200, 120, 20);
        
        buttonLogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	AlunoDao alunoDao = new AlunoDao();
            	
            	if (alunoDao.cpfExiste(jTextCPF.getText())) {
                    dispose();
                    TelaHistorico telaHistorico = new TelaHistorico();
                    telaHistorico.atualizarHistorico();
                } else {
                	JOptionPane.showMessageDialog(null, "CPF Inexistente, tente novamente!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        
        buttonCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {           
                dispose();
                new TelaCadastrar();
       
                }
        });
        
        buttonVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                       
            	dispose();
                new TelaInicial();
            }
            
        });
        
        add(buttonLogar);
        add(buttonCadastrar);
        add(buttonVoltar);
        setVisible(true);
        
        

    }
    
}
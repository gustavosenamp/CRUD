package view;

import dao.AlunoDao;

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
    
    public TelaLogin(){
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); 

        jLabelTitle = new JLabel("Login");
        jLabelTitle.setBounds(170, 10, 100, 30);

        // Configurar a fonte para torná-la maior e em negrito
        Font titleFont = new Font("Arial", Font.BOLD, 24); // Você pode ajustar o tamanho da fonte conforme necessário
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
               //condição de autenticação
                AlunoDao aluno = new AlunoDao();
                
                if (aluno.cpfExiste(jTextCPF.getText())) {
                    dispose();
                    //new AlterarPeso(jTextCPF.getText());
                    new TelaHistorico(null);
                }else{      
                JOptionPane.showMessageDialog(null, "CPF não cadastrado");
                }
                 throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
                }
        });
        
        buttonCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //condição de autenticação
                       
                dispose();
                new TelaCadastrar();
       
                 throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
                }
        });
        
        buttonCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //condição de autenticação
                       
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
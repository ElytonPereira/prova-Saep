package br.com.senai.saep.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import br.com.senai.saep.entity.Professor;
import br.com.senai.saep.service.ProfessorService;

@Component
@Lazy
public class ViewLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtNome;
	private JPasswordField edtSenha;
	private JButton btnEntrar;
	
	@Autowired
	private ProfessorService service;	
	
	@Autowired
	private ViewPrincipal viewPrincipal;
		
	public ViewLogin() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		edtNome = new JTextField();
		edtNome.setBounds(165, 81, 92, 20);
		contentPane.add(edtNome);
		edtNome.setColumns(10);
		
		edtSenha = new JPasswordField();
		edtSenha.setBounds(165, 150, 92, 20);
		contentPane.add(edtSenha);
		
		JLabel txtNome = new JLabel("Nome:");
		txtNome.setBounds(109, 84, 46, 14);
		contentPane.add(txtNome);
		
		JLabel txtSenha = new JLabel("Senha:");
		txtSenha.setBounds(109, 153, 46, 14);
		contentPane.add(txtSenha);
		
		JLabel txtLogin = new JLabel("LOGIN");
		txtLogin.setBounds(192, 11, 46, 14);
		contentPane.add(txtLogin);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String nome = edtNome.getText();
					String senha =new String(edtSenha.getPassword());
					
					Professor professorEncontrado = service.buscarProfessorPor(nome, senha);
					
					if (professorEncontrado != null) {
						JOptionPane.showMessageDialog(null, "Bem vindo: " + professorEncontrado.getNome());
						viewPrincipal.repassarInformacoes(professorEncontrado);						
						dispose();
						
					}else {
						JOptionPane.showMessageDialog(null, "Usuario nao encontrado");
					
					}
				} catch (Exception e2) {
					System.out.println(e2.getMessage());;
				}
			}
		});
		btnEntrar.setBounds(168, 205, 89, 23);
		contentPane.add(btnEntrar);
	}
}

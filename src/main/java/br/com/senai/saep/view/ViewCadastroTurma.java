package br.com.senai.saep.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import br.com.senai.saep.entity.Professor;
import br.com.senai.saep.entity.Turma;
import br.com.senai.saep.service.TurmaService;

@Component @Lazy
public class ViewCadastroTurma extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	@Autowired
	private Professor professor;
	
	@Autowired
	private ViewLogin viewLogin;
	
	private String nomeProfessor;
	
	private JTextField editNumero;
	private JTextField editNome;
	
	@Autowired
	private TurmaService service;
	
	public void repassarInformacoes(Professor professor) {
		Preconditions.checkNotNull(professor, "O professor não pode ser nulo");
		this.nomeProfessor = professor.getNome().toUpperCase();
		this.professor = professor;
		setTitle(nomeProfessor);		
		setVisible(true);
	}	
	
	public ViewCadastroTurma() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastrar turma");
		lblNewLabel.setBounds(158, 11, 118, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Numero");
		lblNewLabel_1.setBounds(25, 70, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		editNumero = new JTextField();
		editNumero.setBounds(77, 67, 86, 20);
		contentPane.add(editNumero);
		editNumero.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nome:");
		lblNewLabel_2.setBounds(25, 100, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		editNome = new JTextField();
		editNome.setBounds(87, 97, 311, 20);
		contentPane.add(editNome);
		editNome.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nome = editNome.getText();
				Integer numero = Integer.parseInt(editNumero.getText());
				
				Turma turma = new Turma();
				
				try {
					turma.setNome(nome);
					turma.setNumero(numero);
					turma.setProfessor(professor);
					
					turma = service.salvar(turma);
					JOptionPane.showMessageDialog(contentPane, "Turma cadastrada com sucesso");
					
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, "Algo de errado aconteceu ao cadastrar a turma. " + e2.getMessage());
				}
			}
		});
		btnSalvar.setBounds(175, 128, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnSair = new JButton("Logout");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewLogin.setVisible(true);
				dispose();
			}
		});
		btnSair.setBounds(335, 7, 89, 23);
		contentPane.add(btnSair);
	}
}

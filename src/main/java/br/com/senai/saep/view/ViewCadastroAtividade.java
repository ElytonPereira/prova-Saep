package br.com.senai.saep.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

import br.com.senai.saep.entity.Atividade;
import br.com.senai.saep.entity.Professor;
import br.com.senai.saep.entity.Turma;
import br.com.senai.saep.service.AtividadeService;
import jakarta.annotation.PostConstruct;

@Component
@Lazy
public class ViewCadastroAtividade extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtNome;
	private JComboBox<Turma> cbTurmas;
	
	@Autowired
	private ViewLogin viewLogin;
	
	
	
	private String nomeProfessor;

	private List<Turma> turmas;
	
	@Autowired
	private AtividadeService atividadeService;
	
	public void repassarInformacoes(Professor professor, List<Turma> turmas) {
		Preconditions.checkNotNull(professor, "O professor não pode ser nulo");
		this.nomeProfessor = professor.getNome().toUpperCase();
		this.turmas = turmas;
		this.carregarCombo();
		setTitle(nomeProfessor);	
		this.setVisible(true);
		
	}	
	
	@PostConstruct
	public void carregarCombo() {
		
		if (turmas != null) {
			
			for (Turma turma : turmas) {
				cbTurmas.addItem(turma);
			}
		}
	}
	
	public void limparCombo() {
		cbTurmas.removeAllItems();
	}
	
	
	public ViewCadastroAtividade() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastrar Atividade");
		lblNewLabel.setBounds(149, 11, 135, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(10, 57, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		edtNome = new JTextField();
		edtNome.setBounds(66, 54, 306, 20);
		contentPane.add(edtNome);
		edtNome.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Turma turma = (Turma) cbTurmas.getSelectedItem();
					String nome = edtNome.getText();
					
					Atividade atividade = new Atividade();
					
					if (atividade != null) {
						atividade.setNome(nome);
						atividade.setTurma(turma);
						
						atividadeService.salvar(atividade);
						
						JOptionPane.showInternalMessageDialog(null, "Atividade salva com sucesso!");
						edtNome.setText("");
						
					}					
					
				} catch (Exception e2) {
					JOptionPane.showInternalMessageDialog(null, "Erro ao tentar salvar a Atividade. " + e2.getMessage());
				}
				
			}
		});
		btnCadastrar.setBounds(176, 90, 119, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnSair = new JButton("Logout");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewLogin.setVisible(true);
				limparCombo();
				dispose();
			}
		});
		btnSair.setBounds(335, 7, 89, 23);
		contentPane.add(btnSair);
		
		cbTurmas = new JComboBox<Turma>();
		cbTurmas.setBounds(10, 90, 156, 22);
		contentPane.add(cbTurmas);
	}
}

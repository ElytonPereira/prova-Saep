package br.com.senai.saep.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
import br.com.senai.saep.view.componente.AtividadeTableModel;

@Component
@Lazy
public class ViewListagemAtividades extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	@Autowired
	private Professor professor;
	
	private String nomeProfessor;
	
	private List<Turma> turmas;	
	
	@Autowired
	private ViewLogin viewLogin;
	
	@Autowired
	private ViewCadastroAtividade viewCadastroAtividade;
	private JTable tableAtividade;
	
	private AtividadeTableModel atividadeTableModel;
	
	@Autowired
	private AtividadeService atividadeService;
	private JTextField editIdTurma;
	
	
	
	
	public void repassarInformacoes(Professor professor, List<Turma> turmas) {
		Preconditions.checkNotNull(professor, "O professor não pode ser nulo");
		this.nomeProfessor = professor.getNome().toUpperCase();
		this.professor = professor;
		this.turmas= turmas;
		setTitle(nomeProfessor);
		this.setVisible(true);
	}
	
	public ViewListagemAtividades() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadastrar = new JButton("Cadastrar atividade");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewCadastroAtividade.repassarInformacoes(professor, turmas);
				dispose();
			}
		});
		btnCadastrar.setBounds(10, 227, 186, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnSair = new JButton("Logout");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewLogin.setVisible(true);
				dispose();
			}
		});
		btnSair.setBounds(345, 11, 89, 23);
		contentPane.add(btnSair);
		
	
	
	// Configuração da tabela
    atividadeTableModel = new AtividadeTableModel();
    tableAtividade = new JTable(atividadeTableModel);
    JScrollPane scrollPane = new JScrollPane(tableAtividade);
    scrollPane.setBounds(10, 41, 323, 170);
	contentPane.add(scrollPane);
	
	JLabel lblNewLabel = new JLabel("Id Turma");
	lblNewLabel.setBounds(10, 15, 46, 14);
	contentPane.add(lblNewLabel);
	
	editIdTurma = new JTextField();
	editIdTurma.setBounds(66, 12, 86, 20);
	contentPane.add(editIdTurma);
	editIdTurma.setColumns(10);
	
	JButton btnNewButton = new JButton("Listar");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			try {
				Integer idMotorista = Integer.parseInt(editIdTurma.getText());
				atualizarTabela(idMotorista);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(contentPane, e2.getMessage());
			}					
		}
	});
	btnNewButton.setBounds(162, 11, 89, 23);
	contentPane.add(btnNewButton);
    
   
}
	
	private void atualizarTabela(Integer idMotorista) {
        // Aqui você deve obter os dados reais dos motoristas da sua aplicação, seja do banco de dados, serviço, etc.
        // Por enquanto, vou criar dados fictícios para exemplificar
		List<Atividade> atividades = atividadeService.listarPor(idMotorista);
		atividadeTableModel = new AtividadeTableModel(atividades);
        tableAtividade.setModel(atividadeTableModel);
    }
}

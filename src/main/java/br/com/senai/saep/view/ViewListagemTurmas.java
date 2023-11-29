package br.com.senai.saep.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import br.com.senai.saep.entity.Professor;
import br.com.senai.saep.entity.Turma;
import br.com.senai.saep.service.TurmaService;
import br.com.senai.saep.view.componente.TurmaTableModel;

@Component
@Lazy
public class ViewListagemTurmas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	@Autowired
	private Professor professor;
	

	private String nomeProfessor;
	
	@Autowired
	private TurmaService turmaService;
	
	@Autowired
	private ViewCadastroTurma viewCadastroTurma;
	private JTable tableTurma;
	private TurmaTableModel turmaTableModel;
	
	
	public void repassarInformacoes(Professor professor) {
		Preconditions.checkNotNull(professor, "O professor não pode ser nulo");
		this.nomeProfessor = professor.getNome().toUpperCase();
		this.professor = professor;
		setTitle(nomeProfessor);	
		this.atualizarTabela();
		setVisible(true);		
	}
	
	public ViewListagemTurmas() {		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadastrar = new JButton("Cadastrar turma");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewCadastroTurma.repassarInformacoes(professor);
			}
		});
		btnCadastrar.setBounds(10, 227, 131, 23);
		contentPane.add(btnCadastrar);
		
		
		
		tableTurma = new JTable();
		
		
		// Configuração da tabela
        turmaTableModel = new TurmaTableModel();
        tableTurma = new JTable(turmaTableModel);
        JScrollPane scrollPane = new JScrollPane(tableTurma);
        scrollPane.setBounds(10, 32, 319, 180);
        contentPane.add(scrollPane);
        
        JButton btnSair = new JButton("Logout");
        btnSair.setBounds(333, 11, 89, 23);
        contentPane.add(btnSair);
	}
	
	private void atualizarTabela() {
        // Aqui você deve obter os dados reais dos motoristas da sua aplicação, seja do banco de dados, serviço, etc.
        // Por enquanto, vou criar dados fictícios para exemplificar
		turmaTableModel = new TurmaTableModel(obterDadosFicticios());
        tableTurma.setModel(turmaTableModel);
    }

    private List<Turma> obterDadosFicticios() {
        // Crie seus próprios dados fictícios ou substitua esta lógica com a obtenção real dos dados
        List<Turma> motoristas = turmaService.listarPor(professor.getId());        

        return motoristas;
    }
}

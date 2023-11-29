package br.com.senai.saep.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import br.com.senai.saep.entity.Professor;
import br.com.senai.saep.entity.Turma;
import br.com.senai.saep.service.TurmaService;

@Component
@Lazy
public class ViewPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;	
	
	private  String nomeProfessor; 	
	
	@Autowired
	private ViewListagemTurmas viewListagemTurmas;
	
	@Autowired
	private ViewListagemAtividades viewListagemAtividades;
	
	@Autowired
	private Professor professor;
	
	@Autowired
	private TurmaService turmaService;
	
	private List<Turma> turmas = new ArrayList<Turma>();
	
	public void repassarInformacoes(Professor professor) {
		Preconditions.checkNotNull(professor, "O professor não pode ser nulo");
		this.nomeProfessor = professor.getNome().toUpperCase();
		this.professor = professor;
		setTitle(nomeProfessor);		
		setVisible(true);
	}

	public ViewPrincipal() {
		setResizable(false);
			
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Cadastros");
		menuBar.add(mnNewMenu);
		
		JMenuItem btnTurma = new JMenuItem("Turma");
		btnTurma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewListagemTurmas.repassarInformacoes(professor);				
				dispose();
			}
		});
		mnNewMenu.add(btnTurma);
		
		JMenuItem btnAtividade = new JMenuItem("Atividade");
		btnAtividade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turmas = turmaService.listarPor(professor.getId());
				System.out.println(turmas);
				viewListagemAtividades.repassarInformacoes(professor, turmas);
				dispose();
			}
		});
		mnNewMenu.add(btnAtividade);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
	}
}

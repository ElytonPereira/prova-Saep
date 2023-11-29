package br.com.senai.saep.view.componente;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.senai.saep.entity.Turma;

public class TurmaTableModel extends AbstractTableModel {
	
private static final long serialVersionUID = 1L;
	
	private final int QTDE_COLUNAS = 2;
	
	private List<Turma> turmas;

	public TurmaTableModel() {
		this.turmas = new ArrayList<>();
	}
	
	public TurmaTableModel(List<Turma> turmas) {
		this();
		if (turmas != null && !turmas.isEmpty()) {		
			this.turmas = turmas;
		}
	}

	@Override
	public int getRowCount() {	
		return turmas.size();
	}
	
	@Override
	public int getColumnCount() {
		return QTDE_COLUNAS;
	}
	
	@Override
	public String getColumnName(int column) {
		if (column == 0) {
			return "Número";
		}else if (column == 1) {
			return "Nome";
		}
		throw new IllegalArgumentException("Indíce inválido");
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return turmas.get(rowIndex).getNumero();
		}else if (columnIndex == 1) {
			return turmas.get(rowIndex).getNome();
		}
		throw new IllegalArgumentException("Índice inválido");
	}
	
	public Turma getPor(int rowIndex) {
		return turmas.get(rowIndex);
	}	
	
	public void removerPor(int rowIndex) {
		
		this.turmas.remove(rowIndex);
		
	}
	
	public boolean isVazio() {
		return turmas.isEmpty();
	}
	
	public boolean isLinhaInvalida(int id) {
		return id >= turmas.size();
	}

}

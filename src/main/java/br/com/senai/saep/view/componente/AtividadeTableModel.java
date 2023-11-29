package br.com.senai.saep.view.componente;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.senai.saep.entity.Atividade;

public class AtividadeTableModel extends AbstractTableModel {
	
private static final long serialVersionUID = 1L;
	
	private final int QTDE_COLUNAS = 2;
	
	private List<Atividade> atividades;

	public AtividadeTableModel() {
		this.atividades = new ArrayList<>();
	}
	
	public AtividadeTableModel(List<Atividade> atividades) {
		this();
		if (atividades != null && !atividades.isEmpty()) {		
			this.atividades = atividades;
		}
	}

	@Override
	public int getRowCount() {	
		return atividades.size();
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
			return atividades.get(rowIndex).getId();
		}else if (columnIndex == 1) {
			return atividades.get(rowIndex).getNome();
		}
		throw new IllegalArgumentException("Índice inválido");
	}
	
	public Atividade getPor(int rowIndex) {
		return atividades.get(rowIndex);
	}	
	
	public void removerPor(int rowIndex) {
		
		this.atividades.remove(rowIndex);
		
	}
	
	public boolean isVazio() {
		return atividades.isEmpty();
	}
	
	public boolean isLinhaInvalida(int id) {
		return id >= atividades.size();
	}

}

package br.com.senai.saep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.base.Preconditions;

import br.com.senai.saep.entity.Atividade;
import br.com.senai.saep.repository.AtividadeRepository;
import jakarta.validation.constraints.NotNull;

@Service
@Validated
public class AtividadeService {
	
	@Autowired
	private AtividadeRepository repository;
	
	public Atividade salvar(@NotNull(message = "A atividade é obrigatória") Atividade atividade) {			
		Preconditions.checkNotNull(atividade, "A atividade não pode ser nula");
		return repository.save(atividade);				
	}
	
	public List<Atividade> listarPor(@NotNull(message = "O id da turma é obrigatório para listar as atividades") Integer id){
		
		return repository.listarPor(id);
	}

}

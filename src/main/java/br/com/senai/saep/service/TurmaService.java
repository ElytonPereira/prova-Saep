package br.com.senai.saep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.base.Preconditions;

import br.com.senai.saep.entity.Turma;
import br.com.senai.saep.repository.AtividadeRepository;
import br.com.senai.saep.repository.TurmaRepository;
import jakarta.validation.constraints.NotNull;

@Service
@Validated
public class TurmaService {
	
	@Autowired
	private TurmaRepository repository;
	
	@Autowired
	private AtividadeRepository atividadeRepository;
	
	public Turma salvar(Turma turma) {
		Preconditions.checkNotNull(turma, "A turma não pode ser nula");
		
		return repository.save(turma);
	}
	
	public List<Turma> listarPor(@NotNull(message = "O id do professor é obrigatório para listar a turma") Integer id){
		
		return repository.listarPor(id);
	}
	
	public Turma excluirPor(Integer id) {
		Turma turmaEncontrada = repository.buscarPor(id);
		Long qtdeAtividadesVinculadas =atividadeRepository.contarPor(id);
		Preconditions.checkArgument(qtdeAtividadesVinculadas == 0, 
				"A turma não pode ser removida por existirem atividades vinculados ao mesmo");
		this.repository.deleteById(id);
		return turmaEncontrada;
	}
	
	

}

package br.com.senai.saep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.base.Preconditions;

import br.com.senai.saep.entity.Professor;
import br.com.senai.saep.repository.ProfessorRepository;
import jakarta.validation.constraints.NotBlank;

@Service
@Validated
public class ProfessorService {

	@Autowired
	private ProfessorRepository repository;
	
	public Professor buscarProfessorPor(
			@NotBlank(message = "Login é obrigatório")
			String login, 
			@NotBlank(message = "Senha é obrigatória")
			String senha) {
		Professor professorEncontrado = repository.buscarPor(login, senha);
		Preconditions.checkNotNull(professorEncontrado, "Usuario não encontrado");
		
		return professorEncontrado;		
	}
	
}

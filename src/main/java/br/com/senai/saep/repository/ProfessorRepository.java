package br.com.senai.saep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.saep.entity.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer>{

	@Query(value = 
			"SELECT p "
			+ "FROM Professor p "			
			+ "WHERE p.nome = :login "
			+ "and p.senha = :senha ")
	public Professor buscarPor(String login, String senha);
	
}

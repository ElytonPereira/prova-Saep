package br.com.senai.saep.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.saep.entity.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Integer>{

	@Query(value = 
			"SELECT a "
			+ "FROM Atividade a "
			+ "WHERE a.id = :id ")
	public Atividade buscarPor(Integer id);
	
	@Query(value = 
			"SELECT a "
			+ "FROM Atividade a "
			+ "WHERE a.turma.id = :id"
			)
	public List<Atividade> listarPor(Integer id);
	
	@Query(value = 
			"SELECT Count(a) "
			+ "FROM Atividade a "
			+ "WHERE a.turma.id = :idDaTurma ")
	public Long contarPor(Integer idDaTurma);
	
}

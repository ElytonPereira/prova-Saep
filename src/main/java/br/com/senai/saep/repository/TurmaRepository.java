package br.com.senai.saep.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.saep.entity.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer>{

	@Query(value = "select t "
			+ "from Turma t "
			+ "join fetch t.professor "
			+ "where t.professor.id = :id_professor "
			+ "order by t.nome "
			)			
	public List<Turma> listarPor(Integer id_professor);
	
	@Query(value = "select t "
			+ "from Turma t "
			+ "where t.id = :id "
			+ "order by t.nome")
	public Turma buscarPor(Integer id);
	
}

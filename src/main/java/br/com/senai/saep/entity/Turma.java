package br.com.senai.saep.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "Turma")
@Table(name = "turmas")
public class Turma {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Size(max = 45, message = "O nome deve possuir até 45 caracteries")
	@NotBlank(message = "O nome é não pode ser nulo")
	@Column(name = "nome")	
	private String nome;
	
	@NotNull(message = "O numero é obrigatório")
	@Column(name = "numero")
	private Integer numero;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "O professor é obrigatório")
	@JoinColumn(name = "id_professor")
	private Professor professor;

	@Override
	public String toString() {
		return nome;
	}
	
	
	
}

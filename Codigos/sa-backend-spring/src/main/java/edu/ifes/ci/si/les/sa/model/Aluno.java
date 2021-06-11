package edu.ifes.ci.si.les.sa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
public class Aluno extends Pessoa{
	private static final long serialVersionUID = 1L;

	private String matricula;

	private String rg;

	private String email;
	
	@Column(length = 20)
    @NotBlank(message = "Senha do Aluno deve ser preenchida")
    @Size(min = 6, max = 10, message = "Senha do Aluno deve ter entre 6 e 20 caracteres")
	private String senha;

	@NotNull(message = "O Curso do Aluno deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;

	@NotNull(message = "O Aluno deve possuir pelo menos um Telefone")
    @ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones = new HashSet<>();

}

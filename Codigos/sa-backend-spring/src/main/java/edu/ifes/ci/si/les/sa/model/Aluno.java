package edu.ifes.ci.si.les.sa.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
public class Aluno extends Pessoa{
	private static final long serialVersionUID = 1L;
	
	@Column(length = 50)
    @NotBlank(message = "A matricula do aluno deve ser preenchida")
    @Size(min = 2, max = 50, message = "A matricula do aluno deve ter entre 2 e 50 letras")
	private String matricula;
	
	@Column(length = 50)
    @NotBlank(message = "RG do aluno deve ser preenchido")
    @Size(min = 2, max = 50, message = "RG do aluno deve ter entre 2 e 50 letras")
    @Pattern(regexp="\\d{5}-\\d{2}", message = "O RG do aluno deve seguir o padrão NNNNN-NN")
	private String rg;
	
	@Column(length = 50)
    @NotBlank(message = "E-mail do aluno deve ser preenchido")
    @Size(min = 2, max = 50, message = "E-mail do aluno deve ter entre 2 e 50 letras")
    @Pattern(regexp="\\\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,4}\\\\b", message = "O E-mail  do aluno deve seguir o padrão NNNNN-NN")
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

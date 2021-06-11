package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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

	private Curso curso;

	private Telefone[] telefone;

}

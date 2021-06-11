package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;

public class Aluno extends Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;

	private String matricula;

	private String rg;

	private String email;

	private String senha;

	private Curso curso;

	private Telefone[] telefone;

}

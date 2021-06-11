package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;

public class Professor extends Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;

	private String titulacao;

	private String email;

	private String senha;

	private Telefone[] telefone;

}

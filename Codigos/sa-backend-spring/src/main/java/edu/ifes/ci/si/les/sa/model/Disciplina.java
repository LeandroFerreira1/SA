package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;

public class Disciplina implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private Integer cargaHoraria;

	private String nomeReduzido;

	private Curso curso;

}

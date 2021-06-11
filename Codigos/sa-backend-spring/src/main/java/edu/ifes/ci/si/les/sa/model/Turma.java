package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Turma implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private Integer qtdVaga;

	private Date dataInicioMatricula;

	private Date dataFimMatricula;

	private PeriodoLetivo periodoLetivo;

	private Aula aula;

	private AtividadeAvaliativa atividadeAvaliativa;

	private Professor professor;

	private Disciplina disciplina;

}

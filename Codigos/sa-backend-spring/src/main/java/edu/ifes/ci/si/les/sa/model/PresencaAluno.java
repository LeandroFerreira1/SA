package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;

public class PresencaAluno implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer qtdAula;

	private boolean presenca;

	private Aula aula;

	private AlunoTurma alunoTurma;

}

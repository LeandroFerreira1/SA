package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Turma implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	private String nome;

	private Integer qtdVaga;

	private Date dataInicioMatricula;

	private Date dataFimMatricula;
	
	@NotNull(message = "O Periodo Letivo da Turma deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "periodo_letivo_id")
	private PeriodoLetivo periodoLetivo;
	
	@NotNull(message = "O aula da Turma deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "aula_id")
	private Aula aula;
	
	@NotNull(message = "A atividade Avaliativa da Turma deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "atividade_avaliativa_id")
	private AtividadeAvaliativa atividadeAvaliativa;
	
	@NotNull(message = "O Professor da Turma deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "professor_id")
	private Professor professor;
	
	@NotNull(message = "A Disciplina da Turma deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;

}

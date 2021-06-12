package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

//Classe criada para representar a chave primária composta do objeto AvaliacaoAluno
@Embeddable
@Data
@EqualsAndHashCode(of = {"alunoTurma", "atividadeAvaliativa"})
public class AvaliacaoAlunoPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
    @JoinColumns({
    	@JoinColumn(name = "aluno_id", referencedColumnName = "aluno_id"),
    	@JoinColumn(name = "turma_id", referencedColumnName = "turma_id")
    })
	private AlunoTurma alunoTurma;
	
	@ManyToOne
	@JoinColumn(name = "atividadeAvaliativa_id")
	private AtividadeAvaliativa atividadeAvaliativa;
}

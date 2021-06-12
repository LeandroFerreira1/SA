package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PresencaAluno implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonIgnore
    @EmbeddedId
    private PresencaAlunoPK id = new PresencaAlunoPK();
	
	private Integer qtdAula;

	private boolean presenca;

	private Aula aula;
	
	@NotNull(message = "")
	@ManyToOne
	@JoinColumn(name = "alunoTurma_id")
	private AlunoTurma alunoTurma;

}

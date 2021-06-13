package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;

@Entity
public class PresencaAluno implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonIgnore
    @EmbeddedId
    private PresencaAlunoPK id = new PresencaAlunoPK();
	
	@NotNull(message = "A Quantidade de Aulas devem ser preenchidas")
    @Digits(integer=6, fraction=0, message = "A Quantidade de Aulas devem ser preenchida com dígitos")
	private Integer qtdAula;
	
	@NotNull(message = "O atributo presença deve ser preenchido")
	private boolean presenca;

	private Aula aula;
	
	@NotNull(message = "")
	@ManyToOne
	@JoinColumn(name = "alunoTurma_id")
	private AlunoTurma alunoTurma;
	
	@Builder
    public void PresencaAluno(Turma turma, Aluno aluno, Aula aula,  Integer qtdAula, boolean presenca) {
    	this.id.setTurma(turma);
    	this.id.setAluno(aluno);
    	this.aula = aula;
        this.qtdAula = qtdAula;
        this.presenca = presenca;
    }

	  @JsonIgnore
	    public Turma getTurma() {
	        return id.getTurma();
	    }

	    public void setTurma(Turma turma) {
	        id.setTurma(turma);
	    }

	    public Aluno getAluno() {
	        return id.getAluno();
	    }

	    public void setAluno(Aluno aluno) {
	        id.setAluno(aluno);
	    }

}

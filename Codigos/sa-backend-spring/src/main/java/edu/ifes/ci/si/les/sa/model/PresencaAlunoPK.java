package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

//Classe criada para representar a chave primária composta do objeto PresencaAluno
@Embeddable
@Data
@EqualsAndHashCode(of = {"aula", "alunoTurma"})
public class PresencaAlunoPK implements Serializable{
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "aula_id")
	private Aula aula;
	
	@ManyToOne
    @JoinColumns({
    	@JoinColumn(name = "aluno_id", referencedColumnName = "aluno_id"),
    	@JoinColumn(name = "turma_id", referencedColumnName = "turma_id")
    })

	public void setTurma(Turma turma) {
		// TODO Auto-generated method stub
		
	}

	public void setAluno(Aluno aluno) {
		// TODO Auto-generated method stub
		
	}

	public Turma getTurma() {
		// TODO Auto-generated method stub
		return null;
	}

	public Aluno getAluno() {
		// TODO Auto-generated method stub
		return null;
	}
}

package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

//Classe criada para representar a chave primária composta do objeto AlunoTurma
@Embeddable
@Data
@EqualsAndHashCode(of = {"aluno", "turma"})
public class AlunoTurmaPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

	public void setTurma(Turma turma2) {
		// TODO Auto-generated method stub
		
	}

	public Aluno getAluno() {
		// TODO Auto-generated method stub
		return null;
	}

	public Turma getTurma() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAluno(Aluno aluno2) {
		// TODO Auto-generated method stub
		
	}
    
}

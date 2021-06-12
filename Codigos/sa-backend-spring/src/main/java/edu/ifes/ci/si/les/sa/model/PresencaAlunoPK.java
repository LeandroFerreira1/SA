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
	private AlunoTurma alunoTurma = new AlunoTurma();
}

package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

//Classe criada para representar a chave primária composta do objeto ItemDeEmprestimo
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
    
}

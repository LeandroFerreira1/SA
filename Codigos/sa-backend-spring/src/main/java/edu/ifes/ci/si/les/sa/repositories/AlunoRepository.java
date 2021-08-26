package edu.ifes.ci.si.les.sa.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.sa.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer>{
	//SELECT al.nome FROM aluno al INNER JOIN aluno_turma as alt ON alt.aluno_id = al.id INNER JOIN turma as tu ON alt.turma_id = tu.id WHERE tu.id = 2
	@Transactional(readOnly = true)
	@Query(value = "SELECT aluno.* FROM aluno INNER JOIN aluno_turma ON aluno_turma.aluno_id = aluno.id INNER JOIN turma ON aluno_turma.turma_id = turma.id WHERE turma.id = ?", nativeQuery = true)
	public Collection<Aluno> findAlunoByTurma(Integer id);
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT COUNT(qtd_aula) AS qtdAulaTurma FROM presenca_aluno WHERE turma_id = ?1 AND aluno_id = ?2 AND presenca = true", nativeQuery = true)//Pensar em uma regra de select diferente 
	public Integer countPresencaAulaByTurma(Integer turmaId, Integer alunoId);
}

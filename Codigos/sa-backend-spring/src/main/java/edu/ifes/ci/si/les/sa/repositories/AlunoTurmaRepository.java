package edu.ifes.ci.si.les.sa.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.sa.model.AlunoTurma;
import edu.ifes.ci.si.les.sa.model.AlunoTurmaPK;

@Repository
public interface AlunoTurmaRepository extends JpaRepository<AlunoTurma, AlunoTurmaPK>{
	
	@Transactional(readOnly = false)
	@Modifying
	@Query(value = "UPDATE aluno_turma alut SET alut.status = REPROVADO WHERE turma_id = ?1 AND aluno_id = ?2", nativeQuery = true)
	void reprovaAluno(Integer turma_id, Integer aluno_id);
	
	
	@Transactional(readOnly = false)
	@Modifying
	@Query(value = "UPDATE aluno_turma alut SET alut.status = CURSANDO WHERE turma_id = ?1 AND aluno_id = ?2", nativeQuery = true)
	void alteraStatusAprovacaoPendente(Integer turma_id, Integer aluno_id);
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT * FROM aluno_turma WHERE turma_id = ?1", nativeQuery = true)
	public Collection<AlunoTurma> findByTurma(Integer turmaID);
}

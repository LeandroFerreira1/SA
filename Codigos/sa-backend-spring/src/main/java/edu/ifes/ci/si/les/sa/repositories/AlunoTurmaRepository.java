package edu.ifes.ci.si.les.sa.repositories;

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
	@Query(value = "UPDATE AlunoTurma alut SET alut.status = REPROVADO WHERE turma_id = ?1 AND aluno_id = ?2", nativeQuery = true)
	void reprovaAluno(Integer turma_id, Integer aluno_id);
	
	
	@Transactional(readOnly = false)
	@Modifying
	@Query(value = "UPDATE AlunoTurma alut SET alut.status = PENDENTE WHERE turma_id = ?1 AND aluno_id = ?2", nativeQuery = true)
	void alteraStatusAprovacaoPendente(Integer turma_id, Integer aluno_id);
	
}

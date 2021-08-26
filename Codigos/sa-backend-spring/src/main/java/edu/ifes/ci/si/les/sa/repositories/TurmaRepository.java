package edu.ifes.ci.si.les.sa.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.sa.model.Curso;
import edu.ifes.ci.si.les.sa.model.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer>{

	@Transactional(readOnly = true)
    public Collection<Turma> findByCurso(Curso curso);
}

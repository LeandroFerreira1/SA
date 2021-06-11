package edu.ifes.ci.si.les.sa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.sa.model.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer>{

}

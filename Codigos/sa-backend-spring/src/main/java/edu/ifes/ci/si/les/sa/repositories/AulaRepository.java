package edu.ifes.ci.si.les.sa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.sa.model.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer>{

}

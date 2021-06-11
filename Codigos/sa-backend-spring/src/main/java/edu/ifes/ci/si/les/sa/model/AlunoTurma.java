package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AlunoTurma implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
    @EmbeddedId
    private AlunoTurmaPK id = new AlunoTurmaPK();
	
	private String status;

	private Double notaFinal;

	private Integer presencaFinal;

}

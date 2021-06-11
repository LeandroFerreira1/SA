package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AlunoTurma implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private String status;

	private Double notaFinal;

	private Integer presencaFinal;

}

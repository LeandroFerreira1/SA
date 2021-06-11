package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class AlunoTurma implements Serializable{
	private static final long serialVersionUID = 1L;

	private String status;

	private Double notaFinal;

	private Integer presencaFinal;

}

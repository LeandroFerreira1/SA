package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class AtividadeAvaliativa implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private String tipo;

	private Date data;

	private Double valor;

}

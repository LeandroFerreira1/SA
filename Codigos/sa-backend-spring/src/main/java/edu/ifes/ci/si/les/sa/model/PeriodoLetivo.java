package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class PeriodoLetivo implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private Date dataInicio;

	private Date dataFim;

	private Integer qtddiaLetivo;

}

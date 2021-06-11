package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Cidade implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private Uf uf;

}

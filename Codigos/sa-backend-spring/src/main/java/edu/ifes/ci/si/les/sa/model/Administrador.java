package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Administrador extends Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;

	private String email;

	private String senha;

}

package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private Date dataNascimento;

	private String sexo;

	private String cpf;

	private String rua;

	private Integer numero;

	private Integer tipoUsuario;

	private Bairro bairro;

}

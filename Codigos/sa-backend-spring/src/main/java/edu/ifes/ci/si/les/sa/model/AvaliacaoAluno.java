package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
public class AvaliacaoAluno implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "A Nota Avaliativa deve ser preenchida")
    @Digits(integer=6, fraction=2, message = "VA Nota Avaliativa deve ser preenchida com dígitos")
	private Double nota;

}

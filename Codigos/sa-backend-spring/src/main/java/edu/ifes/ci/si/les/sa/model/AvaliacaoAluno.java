package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AvaliacaoAluno implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
    @EmbeddedId
    private AvaliacaoAlunoPK id = new AvaliacaoAlunoPK();
	
	@NotNull(message = "A Nota Avaliativa deve ser preenchida")
    @Digits(integer=6, fraction=2, message = "A Nota Avaliativa deve ser preenchida com dígitos")
	private Double nota;

}

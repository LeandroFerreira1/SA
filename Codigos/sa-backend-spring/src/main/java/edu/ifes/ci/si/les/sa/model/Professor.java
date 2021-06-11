package edu.ifes.ci.si.les.sa.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@Entity
public class Professor extends Pessoa {
	private static final long serialVersionUID = 1L;

	private String titulacao;

	private String email;
	
	@Column(length = 20)
    @NotBlank(message = "Senha do Professor deve ser preenchida")
    @Size(min = 6, max = 10, message = "Senha do Professor deve ter entre 6 e 20 caracteres")
	private String senha;

    @ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones = new HashSet<>();

}

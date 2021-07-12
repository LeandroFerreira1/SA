package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class AtividadeAvaliativa implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(length = 50)
    @NotBlank(message = "O nome da Atividade Avaliativa deve ser preenchida")
    @Size(min = 2, max = 50, message = "O nome da Atividade Avaliativa deve ter entre 2 e 50 letras")
	private String nome;
	
	@Column(length = 50)
    @NotBlank(message = "O nome da Atividade Avaliativa deve ser preenchida")
    @Size(min = 2, max = 50, message = "AO nome da Atividade Avaliativa deve ter entre 2 e 50 letras")
	private String tipo;
	
	@NotNull(message = "O Campo deve ser preenchida")
	@Column(length = 10)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 10, max = 10, message = "O campo deve ter 8 digitos")
	private String dataAvaliacao;
	
	@NotNull(message = "Valor da Atividade Avaliativa deve ser preenchida")
    @Digits(integer=6, fraction=2, message = "Valor da Atividade Avaliativa deve ser preenchida com dígitos")
	private Double valor;
	
	@NotNull(message = "A disciplina da Atividade avaliativa deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;
	

}

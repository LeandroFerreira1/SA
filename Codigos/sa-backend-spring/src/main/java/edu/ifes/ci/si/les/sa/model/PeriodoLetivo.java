package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class PeriodoLetivo implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(length = 50)
    @NotBlank(message = "O nome do Periodo Letivo deve ser preenchido")
    @Size(min = 2, max = 50, message = "O nome do Periodo Letivo deve ter entre 2 e 50 letras ou digitos")
	private String nome;
	
	@NotNull(message = "O Campo deve ser preenchida")
	@Column(length = 10)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 10, max = 10, message = "O campo deve ter 8 digitos")
	private String dataInicio;
	
	@NotNull(message = "O Campo deve ser preenchida")
	@Column(length = 10)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 10, max = 10, message = "O campo deve ter 8 digitos")
	private String dataFim;
	
    @Digits(integer=6, fraction=0, message = "A quantidade de dias letivos deve ser preenchido com dígitos")
	private Integer qtddiaLetivo;

}

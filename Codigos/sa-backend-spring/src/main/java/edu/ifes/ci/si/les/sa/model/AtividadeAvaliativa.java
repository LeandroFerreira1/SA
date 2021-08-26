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

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice.Thrown;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class AtividadeAvaliativa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 100)
	@NotBlank(message = "O nome da Atividade Avaliativa deve ser preenchida")
	@Size(min = 2, max = 100, message = "O nome da Atividade Avaliativa deve ter entre 2 e 100 letras")
	private String nome;

	@Column(length = 10)
	@NotBlank(message = "O nome da Atividade Avaliativa deve ser preenchida")
	@Size(min = 2, max = 50, message = "AO nome da Atividade Avaliativa deve ter entre 2 e 10 letras")
	private String tipo;

	@NotNull(message = "A data da Atividade Avaliativa deve  ser preenchido")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataAvaliacao;

	@Column(length = 4)
	@NotBlank(message = "O Valor da Atividade Avaliativa deve ser preenchida")
	@Size(min = 1, max = 4, message = "O valor da Atividade Avaliativa deve ter entre 1 e 4 numeros")
	private Double valor;

	@NotNull(message = "A disciplina da Atividade avaliativa deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;

	
	
	// NÃO INSERIR ATIVIDADES CUJO A SOMA DE TODAS AS ATIVIDADES AVALIATIVAS DA
	// DISCIPLINA TENHAM ULTRAPASSADO 100 PONTOS (REGRA DE NEGOCIO)
}

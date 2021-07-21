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
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class RegistroDeNotas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 4)
	@NotBlank(message = "O nome da Turma deve ser preenchido")
	@Size(min = 1, max = 3, message = "A nota deve ter entre 1 e 4 letras ou digitos")
	private float nota;

	@NotNull(message = "A Disciplina do aluno deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;

	@NotNull(message = "A atividade Avaliativa deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "atividade_avaliativa_id")
	private AtividadeAvaliativa atividadeAvaliativa;

	@NotNull(message = "O aluno deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "aluno_id")
	private Aluno aluno;

	// ---------O Valor da Nota Não pode ultrapassar o valor da AtividadeAvaliativa
}

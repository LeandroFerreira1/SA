package edu.ifes.ci.si.les.sa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

import org.hibernate.annotations.Entity;


@Entity 
@Data 
@AllArgsConstructor 
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Administrador extends Pessoa {
	
	private static final long serialVersionUID = 1L;
	
	private String email;
	
	@Column(length = 20)
    @NotBlank(message = "Senha do Administrador deve ser preenchida")
    @Size(min = 6, max = 10, message = "Senha do Administrador deve ter entre 6 e 20 caracteres")
	private String senha;

}

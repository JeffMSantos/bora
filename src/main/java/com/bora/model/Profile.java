package com.bora.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Profile {

	@Id
	@NotEmpty
	private String email;
	private String nome;
	private Boolean solteiro;
	private Boolean filho;
	private Boolean pet;
	private Boolean sairAnoite;
	private Boolean descanso;
	private Boolean aventureiro;
}

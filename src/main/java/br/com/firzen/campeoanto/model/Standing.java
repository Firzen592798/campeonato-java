package br.com.firzen.campeoanto.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Standing {
	
	public Standing() {
		participante = new Participante();
	}
	
	@Id
	@Column(name="id_standing")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Valid
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
	@JoinColumn(name="id_participante")
	private Participante participante;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="id_campeonato")
	private Campeonato campeonato;
	
	@Column
	@NotNull
	private Integer posicao;
	
	@Column
	@NotNull
	private Integer pontos;
	
	@Column
	@NotNull
	private Integer pontosFora;
}

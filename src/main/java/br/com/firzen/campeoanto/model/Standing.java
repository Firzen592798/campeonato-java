package br.com.firzen.campeoanto.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public Integer getPosicao() {
		return posicao;
	}

	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public Integer getPontosFora() {
		return pontosFora;
	}

	public void setPontosFora(Integer pontosFora) {
		this.pontosFora = pontosFora;
	}
}

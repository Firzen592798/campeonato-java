package br.com.firzen.campeoanto.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
public class Campeonato {
	@Id
	@Column(name="id_campeonato")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotNull(message = "Campo obrigatório")
	@Min(value = 1, message = "Precisa ser maior do que 1")
	private Integer temporada;
	
	@Column
	@NotNull(message = "Campo obrigatório")
	@Min(value = 1, message = "Precisa ser maior do que 1") @Max(value = 4, message = "Precisa ser menor do que 4")
	private Integer divisao;
	
	@Transient
	private Integer numParticipantes;
	
	@Valid
	@OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("posicao asc")
	private List<Standing> standings;
	
	public void addStanding(Standing standing) {
		this.standings.add(standing);
		standing.setCampeonato(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTemporada() {
		return temporada;
	}

	public void setTemporada(Integer temporada) {
		this.temporada = temporada;
	}

	public Integer getDivisao() {
		return divisao;
	}

	public void setDivisao(Integer divisao) {
		this.divisao = divisao;
	}

	public Integer getNumParticipantes() {
		return numParticipantes;
	}

	public void setNumParticipantes(Integer numParticipantes) {
		this.numParticipantes = numParticipantes;
	}

	public List<Standing> getStandings() {
		return standings;
	}

	public void setStandings(List<Standing> standings) {
		this.standings = standings;
	}
}

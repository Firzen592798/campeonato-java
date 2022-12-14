package br.com.firzen.campeoanto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.firzen.campeoanto.model.Campeonato;
import br.com.firzen.campeoanto.model.Role;
import br.com.firzen.campeoanto.model.User;
 
@Repository
public interface CampeonatoRepository extends CrudRepository<Campeonato, Long> {
	public Optional<Campeonato> findByTemporadaAndDivisao(Integer temporada, Integer divisao);

	public List<Campeonato> findByTemporadaOrderByDivisaoAsc(Integer temporada);
	
	@Query("select c from Campeonato c where c.temporada = (select max(c2.temporada) from Campeonato c2) and divisao = 1 order by temporada desc")
	public Campeonato findLastCampeonato();

}
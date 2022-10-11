package br.com.firzen.campeoanto.services;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.firzen.campeoanto.exceptions.NegocioException;
import br.com.firzen.campeoanto.model.Campeonato;
import br.com.firzen.campeoanto.model.Standing;
import br.com.firzen.campeoanto.repository.CampeonatoRepository;

@Service
public class CampeonatoService extends AbstractService<Campeonato>{

	@Override
	public Campeonato save(Campeonato campeonato) throws NegocioException{
		if(campeonato.getId() == null && ((CampeonatoRepository)repository).findByTemporadaAndDivisao(campeonato.getTemporada(), campeonato.getDivisao()).isPresent()) {
			throw new NegocioException("Esse campeonato já existe");
		}
		if(campeonato.getId()  != null) {
		    if(campeonato.getStandings() == null) {
		    	throw new NegocioException("É necessário ter um participante");
		    }
			for(Standing s: campeonato.getStandings()) {
		    	 if(campeonato.getStandings().stream().anyMatch(item -> !Objects.equals(item.getId(), s.getId()) && item.getParticipante().getId().equals(s.getParticipante().getId()))) {
		    		 throw new NegocioException("Duplicação de participantes");
		    	 }
		    }
		}
		return repository.save(campeonato);
	}
	
	public List<Campeonato> findByTemporada(Integer temporada) {
		return ((CampeonatoRepository) repository).findByTemporadaOrderByDivisaoAsc(temporada);
	}
	
	@Transactional
	public void deletarTemporada(Integer temporada) {
		List<Campeonato> campeonatos = findByTemporada(temporada);
		campeonatos.stream().forEach(this::delete);
	}
	
	public Campeonato findLastCampeonato() {
		return ((CampeonatoRepository) repository).findLastCampeonato();
	}
}

package br.com.firzen.campeoanto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.firzen.campeoanto.model.Participante;
 
@Repository
public interface ParticipanteRepository extends CrudRepository<Participante, Long> {

	boolean existsByNome(String value);
 

}
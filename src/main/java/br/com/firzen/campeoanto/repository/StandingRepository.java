package br.com.firzen.campeoanto.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.firzen.campeoanto.model.Role;
import br.com.firzen.campeoanto.model.Standing;
import br.com.firzen.campeoanto.model.User;
 
@Repository
public interface StandingRepository extends CrudRepository<Standing, Long> {
 

}
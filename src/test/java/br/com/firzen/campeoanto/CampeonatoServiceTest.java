package br.com.firzen.campeoanto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.firzen.campeoanto.exceptions.NegocioException;
import br.com.firzen.campeoanto.model.Campeonato;
import br.com.firzen.campeoanto.repository.CampeonatoRepository;
import br.com.firzen.campeoanto.services.CampeonatoService;

@ExtendWith(SpringExtension.class)
//@ComponentScan({"br.com.firzen.campeonato.services", "br.com.firzen.campeonato.repository"})
@ContextConfiguration(classes = {CampeonatoRepository.class, CampeonatoService.class})
//@SpringBootTest
//@AutoConfigureTestEntityManager
@EnableJpaRepositories(basePackages = {"br.com.firzen.campeonato.repository"})
@DataJpaTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@EntityScan("br.com.firzen.campeonato.model")
public class CampeonatoServiceTest {
	
	@Autowired
	private CampeonatoService campeonatoService;
	
	//@Autowired
//	protected TestEntityManager testEntityManager;
	
	@Test
	public void saveCampeonatoTest() throws NegocioException {
		assertNotNull(campeonatoService);
		Campeonato campeonato = new Campeonato();
		campeonato.setDivisao(1);
		campeonatoService.salvar(campeonato);
		Optional<Campeonato> response = campeonatoService.findById((long)1);
		Assertions.assertTrue(response.isPresent());
	}
}
